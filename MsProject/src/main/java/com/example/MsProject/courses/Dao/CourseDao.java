package com.example.MsProject.courses.Dao;

import com.example.MsProject.feedback.Services.FeedbackService;
import com.example.MsProject.TrainingMaterial.Services.MaterialService;
import com.example.MsProject.courses.Mapper.RowToCourse;
import com.example.MsProject.courses.Model.Course;
import com.example.MsProject.courses.Services.SkillServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class CourseDao {

    Logger logger= LoggerFactory.getLogger(CourseDao.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SkillServices skillServices;

    @Autowired
    MaterialService materialService;

    @Autowired
    FeedbackService feedbackService;

    public List<Course> getAllCourses() throws  Exception
    {
        try{
            List<Course> courses= this.jdbcTemplate.query("select * from courses",RowToCourse.mapRowLambda);
            logger.info("Retrieved all the courses");
            return courses;
        }
        catch (Exception e)
        {
            logger.error(e.getCause()+" in this method "+Thread.currentThread().getStackTrace()[1].getMethodName());
            throw e;
        }
    }


    public Course getCourseById(int courseId) throws  Exception
    {
        try{
            Course course=this.jdbcTemplate.queryForObject("select * from courses where courseId="+courseId,RowToCourse.mapRowLambda);
            logger.info("Retrieved the course with courseId="+courseId);
            return course;
        }
        catch (Exception e)
        {
            logger.error(e.getCause()+" in this method "+Thread.currentThread().getStackTrace()[1].getMethodName());
            throw e;
        }
    }

    public boolean isSame(int courseId,Course updatedCourse) throws Exception
    {
        Course course=getCourseById(courseId);
        return  course.getCourseName().equals(updatedCourse.getCourseName()) &&
                course.getCourseDescription().equals(updatedCourse.getCourseDescription()) &&
                course.getPreRequisite().equals(updatedCourse.getPreRequisite());

    }

    public Course updateCourse(int courseId,Course updatedCourse) throws Exception
    {
        try
        {
            if(isSame(courseId , updatedCourse ))
            {
                return updatedCourse;
            }
            else
            {
                System.out.println(updatedCourse.toString());

                final String updateCourse="update courses set courseName = ? , courseDescription = ? , preRequisite= ? , lastUpdated = ?  where courseId= ?";
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                this.jdbcTemplate.update(connection -> {
                    PreparedStatement stmt = connection.prepareStatement(updateCourse);
                    stmt.setString(1, updatedCourse.getCourseName());
                    stmt.setString(2, updatedCourse.getCourseDescription());
                    stmt.setString(3,updatedCourse.getPreRequisite());
                    stmt.setTimestamp(4,currentTime);
                    stmt.setInt(5,courseId);
                    return stmt;
                });
                logger.info("updated the course with courseId="+courseId);
                return getCourseById(courseId);
            }
        }
        catch (Exception e)
        {
            logger.error(e.getCause()+" in this method "+Thread.currentThread().getStackTrace()[1].getMethodName());
            throw e;
        }
    }


    public boolean deleteCourse(int courseId) throws Exception

    {
        /*
            Return
                1 - if success
                0 - if fails
         */
        try
        {
            final String deleteCourse="delete from courses where courseId= ? ";
            boolean isSkillsDeleted= skillServices.deleteAllSkills(courseId);
            boolean isMaterialDeleted= materialService.deleteAllMaterial(courseId);
            boolean isFeedBacksDeleted= feedbackService.deleteFeedbackByCourse(courseId);
            boolean isDeleted=false;
            if(isMaterialDeleted && isSkillsDeleted && isFeedBacksDeleted)
            {
//                System.out.println(this.jdbcTemplate.update(deleteCourse,courseId));
                isDeleted=this.jdbcTemplate.update(deleteCourse,courseId) == 1;

                logger.info("Deleted the course with courseId="+courseId);
            }
            return isDeleted && isMaterialDeleted && isSkillsDeleted;
        }
        catch (Exception e)
        {
            logger.error(e.getCause()+" in this method "+Thread.currentThread().getStackTrace()[1].getMethodName());
            throw e;
        }
    }


    public Course addCourse(Course newCourse) throws Exception
    {
        try{
            final String addNewCourse="insert into courses (courseName,courseDescription,preRequisite,userId,imageUrl)  values ( ? , ? , ? ,? , ?)  ";
            KeyHolder keyHolder=new GeneratedKeyHolder();
            this.jdbcTemplate.update(connection -> {
                PreparedStatement stmt=connection.prepareStatement(addNewCourse, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1,newCourse.getCourseName());
                stmt.setString(2,newCourse.getCourseDescription());
                stmt.setString(3,newCourse.getPreRequisite());
                stmt.setInt(4,newCourse.getUserId());
                stmt.setString(5,newCourse.getImageUrl());
                return stmt;
            },keyHolder);
            logger.info("Added the new Course ");
            int courseId=keyHolder.getKey().intValue();
            Course course= getCourseById(courseId);
            return course;
        }
        catch (Exception e)
        {
            logger.error(e.getCause()+" in this method "+Thread.currentThread().getStackTrace()[1].getMethodName());
            throw e;
        }
    }

}
