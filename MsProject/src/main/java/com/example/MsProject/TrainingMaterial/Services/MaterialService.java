package com.example.MsProject.TrainingMaterial.Services;


import com.example.MsProject.TrainingMaterial.Dao.MaterialDao;
import com.example.MsProject.TrainingMaterial.Model.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.Exception;
import java.util.List;

@Service
public class MaterialService {

    @Autowired
    MaterialDao materialDao;

    public Material addMaterial(Material material, MultipartFile file) throws Exception
    {
        return  materialDao.addMaterial(material,file);
    }

    public Material updateMaterial(Material material,MultipartFile file,int previousMaterial) throws Exception
    {
        return materialDao.updateMaterial(material, file,previousMaterial);
    }

    public List<Material> getLatestMaterialByCourse(int courseId) throws Exception
    {
        return materialDao.getLatestMaterialByCourseId(courseId);
    }

    public List<Material> getAllMaterialByCourse(int courseId,int materialId) throws Exception
    {
        return materialDao.getAllMaterialsByCourseId(courseId,materialId);
    }


    public Boolean deleteLatestMaterial(int materialId) throws Exception
    {
        return materialDao.deleteLatestVersion(materialId);
    }

    public Boolean deleteAllMaterial(int courseId) throws Exception
    {
        return materialDao.deleteAllMaterial(courseId);
    }

}
