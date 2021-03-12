package com.example.MsProject.courses.Model;

import java.sql.Timestamp;

public class Course {
    private int courseId;
    private String courseName;
    private String courseDescription;
    private String preRequisite;
    private int userId;
    private Timestamp createdAt;
    private Timestamp lastUpdated;
    private String imageUrl;



    public Course(){}

//    public Course(int courseId, String courseName, String courseDescription, String preRequisite, int userId,String imageUrl) {
//        this.courseId = courseId;
//        this.courseName = courseName;
//        this.courseDescription = courseDescription;
//        this.preRequisite = preRequisite;
//        this.userId = userId;
//        this.imageUrl=imageUrl;
//    }
//
//    public Course(int courseId, String courseName, String courseDescription, String preRequisite, int userId, Timestamp createdAt, Timestamp lastUpdated) {
//        this.courseId = courseId;
//        this.courseName = courseName;
//        this.courseDescription = courseDescription;
//        this.preRequisite = preRequisite;
//        this.userId = userId;
//        this.createdAt = createdAt;
//        this.lastUpdated = lastUpdated;
//    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getPreRequisite() {
        return preRequisite;
    }

    public void setPreRequisite(String preRequisite) {
        this.preRequisite = preRequisite;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", preRequisite='" + preRequisite + '\'' +
                ", userId=" + userId +
                '}';
    }
}
