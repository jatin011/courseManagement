package com.example.MsProject.courses.Services;

import com.example.MsProject.courses.Dao.CourseDao;
import com.example.MsProject.courses.Model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.Exception;
import java.util.List;

@Service
public class CourseServices {

    @Autowired
    CourseDao courseDao;
    public List<Course> getAllCourses() throws Exception
    {
       return courseDao.getAllCourses();
    }

    public Course getCourseById(int courseId) throws Exception
    {
        return courseDao.getCourseById(courseId);
    }

    public Course updateCourse(int courseId,Course updatedCourse) throws Exception
    {
        return courseDao.updateCourse(courseId,updatedCourse);
    }

    public Boolean deleteCourse(int courseId)throws Exception
    {
        return courseDao.deleteCourse(courseId);
    }

    public Course addCourse(Course course) throws Exception
    {
        return courseDao.addCourse(course);
    }

}
