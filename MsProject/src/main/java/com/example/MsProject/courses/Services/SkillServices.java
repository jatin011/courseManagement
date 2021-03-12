package com.example.MsProject.courses.Services;

import com.example.MsProject.courses.Model.Skill;
import com.example.MsProject.courses.Dao.SkillCourseMappingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.Exception;
import java.util.List;

@Service
public class SkillServices
{
    @Autowired
    SkillCourseMappingDao skillCourseMappingDao;

    public boolean addSkillInCourses(int courseId,int skillId) throws Exception
    {
        return skillCourseMappingDao.addSKillsInCourse(courseId,skillId);
    }

    public boolean deleteSkillInCourse(int courseId,int skillId) throws Exception
    {
        return skillCourseMappingDao.deleteSkillInCourse(courseId, skillId);
    }

    public List<String> getAllSkills(int courseId) throws Exception
    {
        return skillCourseMappingDao.getAllSkills(courseId);
    }

    public Boolean deleteAllSkills(int courseId) throws Exception
    {
        return skillCourseMappingDao.deleteAllSKillsInCourse(courseId);
    }

    public List<Skill> getAllSkillsFromSkill() throws  Exception
    {
        return skillCourseMappingDao.getAllSkillsfromSkill();
    }
}
