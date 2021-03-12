package com.example.MsProject.courses.Mapper;

import com.example.MsProject.courses.Model.Course;

import org.springframework.jdbc.core.RowMapper;

public class RowToCourse  {

    public static  RowMapper<Course> mapRowLambda = ((resultSet, i) -> {
        Course course=new Course();
        course.setCourseId(resultSet.getInt("courseId"));
        course.setCourseName(resultSet.getString("courseName"));
        course.setCourseDescription(resultSet.getString("courseDescription"));
        course.setUserId(resultSet.getInt("userId"));
        course.setCreatedAt(resultSet.getTimestamp("createdAt"));
        course.setPreRequisite(resultSet.getString("preRequisite"));
        course.setLastUpdated(resultSet.getTimestamp("lastUpdated"));
        course.setImageUrl(resultSet.getString("imageUrl"));
        return course;
    });

}
