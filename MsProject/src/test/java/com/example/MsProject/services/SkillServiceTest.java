package com.example.MsProject.services;

import com.example.MsProject.courses.Dao.SkillCourseMappingDao;
import com.example.MsProject.courses.Model.Skill;
import com.example.MsProject.courses.Services.SkillServices;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SkillServiceTest {

    @Mock
    SkillCourseMappingDao skillCourseMappingDao;

    @InjectMocks
    SkillServices skillServices;

    @Test
    public void addSkillInCourseTest() throws Exception{
        when(skillCourseMappingDao.addSKillsInCourse(1,2)).thenReturn(true);

        boolean isAdded=skillServices.addSkillInCourses(1,2);
        assertEquals(true,isAdded);
    }


    @Test
    public void  deleteSkillInCourseTest() throws Exception
    {
        when(skillCourseMappingDao.deleteSkillInCourse(1,2)).thenReturn(true);

        boolean isDeleted=skillServices.deleteSkillInCourse(1,2);
        assertEquals(true,isDeleted);
    }
    
    @Test
    public void deleteAlSkillsTest() throws Exception
    {
        when(skillCourseMappingDao.deleteAllSKillsInCourse(1)).thenReturn(true);

        boolean isAllDeleted=skillServices.deleteAllSkills(1);
        assertEquals(true,isAllDeleted);
    }

    @Test
    public void getAllSkillsTest() throws Exception
    {
        when(skillCourseMappingDao.getAllSkills(1)).thenReturn(Arrays.asList("angular","java"));

        List<String> skills=skillServices.getAllSkills(1);
        assertEquals(Arrays.asList("angular","java"),skills);
    }

    @Test
    public void getAllSkillFromSkill() throws Exception{
        Skill skill1=new Skill(1,"angular");
        Skill skill2=new Skill(2,"Java");

        when(skillCourseMappingDao.getAllSkillsfromSkill()).thenReturn(Arrays.asList(skill1,skill2));

        List<Skill> allSkills=skillServices.getAllSkillsFromSkill();
        assertEquals(Arrays.asList(skill1,skill2),allSkills);
    }

}
