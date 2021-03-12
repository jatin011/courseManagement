package com.example.MsProject.Dao;


import com.example.MsProject.MsProjectApplication;
import com.example.MsProject.TrainingMaterial.Model.Material;
import com.example.MsProject.TrainingMaterial.Services.MaterialService;
import com.example.MsProject.courses.Dao.CourseDao;
import com.example.MsProject.courses.Mapper.RowToCourse;
import com.example.MsProject.courses.Model.Course;
import com.example.MsProject.courses.Services.SkillServices;
import com.example.MsProject.feedback.Services.FeedbackService;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.ExceptionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MsProjectApplication.class)
public class courseDaoTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    CourseDao courseDao;

    @Mock
    SkillServices skillServices;

    @Mock
    FeedbackService feedbackService;

    @Mock
    MaterialService materialService;


    Course course=new Course();
    Course course1=new Course();
    List<Course> courses=new ArrayList<>();

    @BeforeEach
    void init()
    {
        course.setImageUrl("img");
        course.setCourseName("angular");
        course.setCourseDescription("description1");
        course.setPreRequisite("pre1");
        course.setCourseId(1);
        course.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        course.setLastUpdated(new Timestamp(System.currentTimeMillis()));


        course1.setImageUrl("img2");
        course1.setCourseName("angular2");
        course1.setCourseDescription("description2");
        course1.setPreRequisite("pre2");
        course1.setCourseId(2);
        course1.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        course1.setLastUpdated(new Timestamp(System.currentTimeMillis()));

        courses.add(course);
        courses.add(course1);
    }



    @Test
    public void getAllCourseTest() throws Exception {
        when(this.jdbcTemplate.query("select * from courses", RowToCourse.mapRowLambda)).thenReturn(courses);

        List<Course> allCourses=courseDao.getAllCourses();
        assertEquals(courses,allCourses);

    }


    @Test
    public void getCoursesByIdTest() throws Exception {
        when(this.jdbcTemplate.queryForObject("select * from courses where courseId="+course1.getCourseId(),RowToCourse.mapRowLambda)).thenReturn(course1);

        Course testCourse=courseDao.getCourseById(course1.getCourseId());
        assertEquals(course1,testCourse);
    }

    @Test
    public  void deleteCourseTest() throws Exception {
        lenient().when(this.jdbcTemplate.update("delete from courses where courseId= ? ",course.getCourseId())).thenReturn(1);

        lenient().when(skillServices.deleteAllSkills(course.getCourseId())).thenReturn(true);
        lenient().when(feedbackService.deleteFeedbackByCourse(course.getCourseId())).thenReturn(true);
        lenient().when(materialService.deleteAllMaterial(course.getCourseId())).thenReturn(true);

        boolean isDeleted=courseDao.deleteCourse(course.getCourseId());
        assertEquals(true,isDeleted);

    }

}
