package com.example.MsProject.Dao;

import com.example.MsProject.MsProjectApplication;
import com.example.MsProject.courses.Dao.SkillCourseMappingDao;
import com.example.MsProject.courses.Model.Skill;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MsProjectApplication.class)
public class skillCourseMappingDaoTest {


    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    SkillCourseMappingDao skillCourseMappingDao;

    @Test
    public  void addSkillinCourseTest() throws Exception {
        when(this.jdbcTemplate.update("insert into skillCourseMapping (courseId,skillId) values ( ?, ? )",1,2)).thenReturn(1);

        boolean isAdded=skillCourseMappingDao.addSKillsInCourse(1,2);
        assertEquals(true,isAdded);
    }


    @Test
    public void checkCourseExistTest() throws Exception {
        when(this.jdbcTemplate.queryForObject("select count(*) from skillCourseMapping where courseId = ? ",Integer.class,1)).thenReturn(1);

        Boolean isChecked=skillCourseMappingDao.checkCourseExist(1);

        assertEquals(true,isChecked);
    }

    @Test
    public void deleteSkillIncourseTest () throws Exception {
        when(this.jdbcTemplate.update("delete from skillCourseMapping where courseId= ? and skillId= ? ",1,2)).thenReturn(1);

        boolean isDeleted=skillCourseMappingDao.deleteSkillInCourse(1,2);
        assertEquals(true,isDeleted);
    }

    @Test
    public void deleteAllSKillsInCourseTest() throws Exception {
        when(this.jdbcTemplate.queryForObject("select count(*) from skillCourseMapping where courseId = ? ",Integer.class,1)).thenReturn(1);
        when(this.jdbcTemplate.update("delete from skillCourseMapping where courseId=?", 1)).thenReturn(1);

        boolean isDeleted=skillCourseMappingDao.deleteAllSKillsInCourse(1);
        assertEquals(true,isDeleted);
    }

    @Test
   public void getAllSkillsfromSkillTest() throws SQLException {
        List<Skill> skills=new ArrayList<>();
        skills.add(new Skill(1,"ang"));
        skills.add(new Skill(2,"ang1"));
        when(this.jdbcTemplate.query("select * from skills", SkillCourseMappingDao.userRowMapperLambda)).thenReturn(skills);

        List<Skill> allSkills=skillCourseMappingDao.getAllSkillsfromSkill();
        assertEquals(skills,allSkills);
    }


}


