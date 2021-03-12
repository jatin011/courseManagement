package com.example.MsProject.Controller;

import com.example.MsProject.courses.Controller.CoursesController;
import com.example.MsProject.courses.Model.Course;
import com.example.MsProject.courses.Model.Skill;
import com.example.MsProject.courses.Services.CourseServices;
import com.example.MsProject.courses.Services.SkillServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CoursesController.class)
public class CourseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CourseServices courseServices;


    @Autowired
    ObjectMapper objectMapper;


    @MockBean
    SkillServices skillServices;

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
        lenient().when(courseServices.getAllCourses()).thenReturn(courses);
        mockMvc.perform(get("/courses/all")).andDo(print())
        .andExpect(status().isOk());


        lenient().when(courseServices.getAllCourses()).thenThrow(new Exception());
        mockMvc.perform(get("/courses/all")).andDo(print())
                .andExpect(status().isInternalServerError());

    }


    @Test
    public void getCourseByIdTest() throws Exception{
        lenient().when(courseServices.getCourseById(course1.getCourseId())).thenReturn(course1);

        mockMvc.perform(get("/courses/getById/"+course1.getCourseId()))
                .andDo(print()).andExpect(status().isOk());


        when(courseServices.getCourseById(course1.getCourseId())).thenThrow(new Exception());

        mockMvc.perform(get("/courses/getById/"+course1.getCourseId()))
                .andDo(print()).andExpect(status().isInternalServerError());


    }


    @Test
    public void updateCourseTest() throws Exception{
        lenient().when(courseServices.updateCourse(course1.getCourseId(),course)).thenReturn(course);

        String jsonString=objectMapper.writeValueAsString(course);
        mockMvc.perform(put("/courses/updateCourse/"+course1.getCourseId())
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andDo(print()).andExpect(status().isOk());
    }


    @Test
    public void deleteCourse() throws Exception{
        lenient().when(courseServices.deleteCourse(course1.getCourseId())).thenReturn(true);

        mockMvc.perform(delete("/courses/deleteCourse/"+course1.getCourseId())).andDo(print()).andExpect(status().isOk());
        lenient().when(courseServices.deleteCourse(course1.getCourseId())).thenThrow(new Exception());
        mockMvc.perform(delete("/courses/deleteCourse/"+course1.getCourseId())).andDo(print()).andExpect(status().isInternalServerError());

    }


    @Test
    public void addCourse() throws Exception{
        lenient().when(courseServices.addCourse(course)).thenReturn(course);

        String jsonString=objectMapper.writeValueAsString(course);
        mockMvc.perform(post("/courses/addCourse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    public void addSkillInCourse() throws Exception {
        lenient().when(skillServices.addSkillInCourses(course1.getCourseId(),1)).thenReturn(true);

        mockMvc.perform(post("/courses/addSkillInCourse")
                .param("courseId",String.valueOf(course1.getCourseId()))
                .param("skillId",String.valueOf(1)))
                .andDo(print())
                .andExpect(status().isCreated());

        lenient().when(skillServices.addSkillInCourses(course1.getCourseId(),1)).thenThrow(new Exception());

        mockMvc.perform(post("/courses/addSkillInCourse")
                .param("courseId",String.valueOf(course1.getCourseId()))
                .param("skillId",String.valueOf(1)))
                .andDo(print())
                .andExpect(status().isInternalServerError());



    }


    @Test
    public void deleteSkillInCourseTest() throws Exception{
        lenient().when(skillServices.deleteSkillInCourse(course1.getCourseId(),1)).thenReturn(true);

        mockMvc.perform(delete("/courses/deleteSkillInCourse")
                .param("courseId",String.valueOf(course1.getCourseId()))
                .param("skillId",String.valueOf(1)))
                .andDo(print())
                .andExpect(status().isOk());

        lenient().when(skillServices.deleteSkillInCourse(course1.getCourseId(),1)).thenThrow(new Exception());

        mockMvc.perform(delete("/courses/deleteSkillInCourse")
                .param("courseId",String.valueOf(course1.getCourseId()))
                .param("skillId",String.valueOf(1)))
                .andDo(print())
                .andExpect(status().isInternalServerError());


    }


    @Test
    public void getAllSkills() throws Exception{

        lenient().when(skillServices.getAllSkills(course1.getCourseId())).thenReturn(Arrays.asList("Angular","java"));

        mockMvc.perform(get("/courses/getAllSkills/"+course1.getCourseId()))
                .andDo(print())
                .andExpect(status().isOk());

        lenient().when(skillServices.getAllSkills(course1.getCourseId())).thenThrow(new Exception());

        mockMvc.perform(get("/courses/getAllSkills/"+course1.getCourseId()))
                .andDo(print())
                .andExpect(status().isInternalServerError());


    }


    @Test
    public void getSkillsTest() throws Exception{
        lenient().when(skillServices.getAllSkillsFromSkill()).thenReturn(Arrays.asList(new Skill(1,"angular"),new Skill(2,"java")));

        mockMvc.perform(get("/courses/getSkills"))
                .andDo(print())
                .andExpect(status().isOk());

        lenient().when(skillServices.getAllSkillsFromSkill()).thenThrow(new Exception());

        mockMvc.perform(get("/courses/getSkills"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }
}
