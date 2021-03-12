package com.example.MsProject.TrainingMaterial.Mapper;

import com.example.MsProject.TrainingMaterial.Model.Material;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowToMaterial {

    public static RowMapper<Material> MapRowToMaterialLambda = ((resultSet, i) -> {
        Material material=new Material();
        material.setMaterialId(resultSet.getInt("materialId"));
        material.setCourseId(resultSet.getInt("courseId"));
        material.setMaterialDescription(resultSet.getString("materialDescription"));
        material.setCreatedAt(resultSet.getTimestamp("createdAt"));
        material.setCurrent(resultSet.getBoolean("isCurrent"));
        material.setFileType(resultSet.getString("fileType"));
        material.setLastUpdated(resultSet.getTimestamp("lastUpdated"));
        material.setFileData(resultSet.getBytes("fileData"));
        material.setParentId(resultSet.getInt("parentId"));
        material.setFileName(resultSet.getString("fileName"));
        return material;
    }) ;

}
