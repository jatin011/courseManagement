package com.example.MsProject.TrainingMaterial.Model;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

public class Material {
    private  int materialId;
    private int courseId;
    private  int parentId;
    private boolean isCurrent;
    private Timestamp createdAt;
    private Timestamp lastUpdated;
    private  String materialDescription;
    private String fileName;

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    private byte[] fileData;


    public Material(){}


//
//    public Material(int materialId, int courseId, int parentId, boolean isCurrent, Timestamp createdAt, Timestamp lastUpdated, String materialDescription, String fileType) {
//        this.materialId = materialId;
//        this.courseId = courseId;
//        this.parentId = parentId;
//        this.isCurrent = isCurrent;
//        this.createdAt = createdAt;
//        this.lastUpdated = lastUpdated;
//        this.materialDescription = materialDescription;
//        this.fileType = fileType;
//    }
//
//    public Material(int courseId, String materialDescription, String fileType) {
//        this.courseId = courseId;
//        this.materialDescription = materialDescription;
//        this.fileType = fileType;
//    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    private String fileType;



    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    @Override
    public String toString() {
        return "Material{" +
                "materialId=" + materialId +
                ", courseId=" + courseId +
                ", parentId=" + parentId +
                ", isCurrent=" + isCurrent +
                ", createdAt=" + createdAt +
                ", lastUpdated=" + lastUpdated +
                ", materialDescription='" + materialDescription + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
