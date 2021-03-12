package com.example.MsProject.TrainingMaterial.Dao;

import com.example.MsProject.TrainingMaterial.Mapper.RowToMaterial;
import com.example.MsProject.TrainingMaterial.Model.Material;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.Exception;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class MaterialDao {


    Logger logger= LoggerFactory.getLogger(MaterialDao.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Material getMaterialById(int materialId) throws  Exception
    {
        try{
            Material material= this.jdbcTemplate.queryForObject("select * from materials where materialId="+materialId,RowToMaterial.MapRowToMaterialLambda);
            logger.info("Retrieved material with materialId="+materialId);
            return  material;
        }
        catch (Exception e)
        {
            logger.error(e.getCause()+" in this method "+Thread.currentThread().getStackTrace()[1].getMethodName());
            throw e;
        }
    }

    public int getNumberOfVersions(int courseId) throws Exception
    {
        try{
            int numberOfMaterials=this.jdbcTemplate.queryForObject("select count(*) from materials where courseId="+courseId,Integer.class);
            logger.info("Retrieved number of materials for courseID="+courseId);
            return numberOfMaterials;
        }
        catch (Exception e)
        {
            logger.error(e.getCause()+" in this method "+Thread.currentThread().getStackTrace()[1].getMethodName());
            throw  e;
        }
    }

    public boolean materialAlreadyExist(int courseId) throws Exception
    {
        try{
            boolean doesMaterialExist=this.jdbcTemplate.queryForObject("select count(*) from materials where courseId="+courseId,Integer.class)>0;
            logger.info("Retrieved if the materials for a course Exist or Not");
            return doesMaterialExist;
        }
        catch (Exception e)
        {
            logger.error(e.getCause()+" in this method "+Thread.currentThread().getStackTrace()[1].getMethodName());
            throw e;
        }
    }


    public Material addMaterial(Material newMaterial,MultipartFile file) throws Exception{

        try{

            final String addMaterialQuery="insert into materials( courseId,materialDescription,fileType,fileData,fileName ) values ( ? , ? , ? , ? ,?)";

            KeyHolder keyHolder=new GeneratedKeyHolder();
            this.jdbcTemplate.update(connection -> {
                PreparedStatement stmt=connection.prepareStatement(addMaterialQuery, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1,newMaterial.getCourseId());
                stmt.setString(2,newMaterial.getMaterialDescription());
                stmt.setString(3, newMaterial.getFileType());
                try {
                    stmt.setBinaryStream(4,file.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stmt.setString(5,newMaterial.getFileName());
                return stmt;
            },keyHolder);

            int materialId= keyHolder.getKey().intValue();
            logger.info("Successfully added the material with materialID="+materialId);
            return getMaterialById(materialId);
        }
        catch (Exception e)
        {
            logger.error(e.getCause()+" in this method "+Thread.currentThread().getStackTrace()[1].getMethodName());
            throw e;
        }
    }



    public Material updateMaterial(Material newMaterial, MultipartFile newFile,int previousMaterialId) throws Exception{

        try{

            String getLastVersion="select materialId,parentId from materials where courseId= ? and isCurrent= ? and materialId=?";
            ArrayList<Integer> lastVersion=this.jdbcTemplate.queryForObject(getLastVersion, new RowMapper<ArrayList<Integer>>() {
                @Override
                public ArrayList<Integer> mapRow(ResultSet resultSet, int i) throws SQLException {
                    return new ArrayList<>(Arrays.asList(resultSet.getInt("materialId"),resultSet.getInt("parentId")));
                }
            },newMaterial.getCourseId(),true,previousMaterialId);
            int parentId= lastVersion.get(0) ;

            logger.info("Retrieved the latest version before updating");

            String updateLastVersion="update materials set isCurrent="+false+" where materialId="+lastVersion.get(0);
            this.jdbcTemplate.update(updateLastVersion);

            logger.info("Updated the last latest versions isCurrent to false");

            String updateMaterial="insert into materials (courseId,materialDescription,fileType,fileData,parentId,fileName) values (?,?,?,?,?,?)";
            KeyHolder keyHolder=new GeneratedKeyHolder();
            this.jdbcTemplate.update(connection -> {
                PreparedStatement stmt=connection.prepareStatement(updateMaterial,Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1,newMaterial.getCourseId());
                stmt.setString(2,newMaterial.getMaterialDescription());
                stmt.setString(3, newMaterial.getFileType());
                try {
                    stmt.setBinaryStream(4,newFile.getInputStream());
                } catch (IOException e) {
                    logger.error(e.getCause()+" in this method "+Thread.currentThread().getStackTrace()[1].getMethodName());
                }
                stmt.setInt(5,parentId);
                stmt.setString(6,newMaterial.getFileName());
                return stmt;
            },keyHolder);

            int materialId=keyHolder.getKey().intValue();
            logger.info("Inserted the new latest version Training Material with materialId="+materialId);
            return getMaterialById( materialId);
        }
        catch (Exception e)
        {
            logger.error(e.getCause()+" in this method "+Thread.currentThread().getStackTrace()[1].getMethodName());
            throw e;
        }
    }


    public List<Material> getLatestMaterialByCourseId(int courseId) throws Exception
    {
        try{
            final String getLatest="select * from materials where courseId = ? and isCurrent= ? ";
            System.out.println(courseId);
            List<Material> material=this.jdbcTemplate.query(getLatest,RowToMaterial.MapRowToMaterialLambda,courseId,true);
            logger.info("Retrieved the latest material of courseID="+courseId);
            return material;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error(e.getCause()+" in this method "+Thread.currentThread().getStackTrace()[1].getMethodName());
            throw e;
        }
    }

    public List<Material> getAllMaterialsByCourseId(int courseId,int materialId) throws Exception
    {
        try{
            List<Material> materials=new ArrayList<>();
            String getLatest="select * from materials where courseId = ? and materialId= ? ";
            Material material= this.jdbcTemplate.queryForObject(getLatest,RowToMaterial.MapRowToMaterialLambda,courseId,materialId);
            logger.info("Retrieved every material version of courseId="+courseId+" and materialId"+materialId);
            materials.add(material);
            int parentId=material.getParentId();
            while(parentId!=0)
            {
                getLatest="select * from materials where courseId = ? and materialId= ? ";
                material= this.jdbcTemplate.queryForObject(getLatest,RowToMaterial.MapRowToMaterialLambda,courseId,parentId);
                logger.info("Retrieved every material version of courseId="+courseId+" and materialId="+parentId);
                materials.add(material);
                parentId=material.getParentId();
            }
            return materials;
        }
        catch (Exception e)
        {
            logger.error(e.getCause()+" in this method "+Thread.currentThread().getStackTrace()[1].getMethodName());
            throw e;
        }
    }


    public Boolean deleteLatestVersion(int materialId) throws Exception
    {
        try{
            Material material=getMaterialById(materialId);
            int parentId=material.getParentId();
            this.jdbcTemplate.update("delete from materials where materialId= ? ",materialId);

            if(parentId!=0)
            {
                return this.jdbcTemplate.update("update materials set isCurrent = ? where materialId = ?",true,parentId)==1;
            }
            return true;

//            List<Material> latestMaterial=getLatestMaterialByCourseId(courseId);
//            if(latestMaterial.get(0).getParentId()==0)
//            {
//                return deleteAllMaterial(courseId);
//            }
//            String makePreviousLatest="update materials set isCurrent=? where materialId=?";
//            this.jdbcTemplate.update(makePreviousLatest,true,latestMaterial.get(0).getParentId());
//            return this.jdbcTemplate.update("delete from materials where materialId=?",latestMaterial.get(0).getMaterialId())>=1;
        }
        catch (Exception e)
        {
            logger.error(e.getCause()+" in this method "+Thread.currentThread().getStackTrace()[1].getMethodName());
            throw e;
        }
    }


    public boolean deleteAllMaterial(int courseId) throws Exception
    {
        try
        {
            if(materialAlreadyExist(courseId))
            {
                boolean isDeleted=this.jdbcTemplate.update("delete from materials where courseId= ? ",courseId)>=1;
                logger.info("Material deleted with courseId="+courseId);
                return isDeleted;
            }
            else
            {
                logger.info("Material with courseId="+ courseId +" doesn't exist");
                return true;
            }
        }
        catch (Exception e)
        {
            logger.error(e.getCause()+" in this method "+Thread.currentThread().getStackTrace()[1].getMethodName());
            throw e;
        }
    }
}
