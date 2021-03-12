package com.example.MsProject.courses.Controller;

import com.example.MsProject.courses.Model.Course;
import com.example.MsProject.courses.Model.Skill;
import com.example.MsProject.courses.Services.CourseServices;
import com.example.MsProject.courses.Services.SkillServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.Exception;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    @Autowired
    CourseServices courseServices;

    @Autowired
    SkillServices skillServices;

    @GetMapping("/all")
    @CrossOrigin("*")
    ResponseEntity<List<Course>> getAllCourses()
    {
        try {
            return new ResponseEntity<>(courseServices.getAllCourses(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getById/{Id}")
    @CrossOrigin("*")
    ResponseEntity<Course> getCourseById(@PathVariable ("Id") int courseId)
    {
        try{
            return new ResponseEntity<>(courseServices.getCourseById(courseId),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("updateCourse/{courseId}")
    @CrossOrigin("*")
    ResponseEntity<Course> updateCourse(@PathVariable ("courseId") int courseId,@RequestBody Course updatedCourse)
    {
        try
        {
            return new ResponseEntity<>(courseServices.updateCourse(courseId , updatedCourse),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("deleteCourse/{courseId}")
    @CrossOrigin("*")
    ResponseEntity<Boolean> deleteCourse(@PathVariable ("courseId") int courseId)
    {
        try{
            courseServices.deleteCourse(2);
            return new ResponseEntity<>(courseServices.deleteCourse(courseId),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addCourse")
    @CrossOrigin("*")
    ResponseEntity<Course> addCourse(@RequestBody Course newCourse)
    {
        try {
            System.out.println(String.valueOf(courseServices.addCourse(newCourse)));
            return new ResponseEntity<>(courseServices.addCourse(newCourse),HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("addSkillInCourse")
    @CrossOrigin("*")
    ResponseEntity<Boolean> addSKillInCourse(@RequestParam("courseId") int courseId,@RequestParam("skillId") int skillId)
    {
        try{
            return new ResponseEntity<>(skillServices.addSkillInCourses(courseId,skillId),HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("deleteSkillInCourse")
    @CrossOrigin("*")
    ResponseEntity<Boolean> deleteSkillInCourse(@RequestParam("courseId") int courseId,@RequestParam("skillId") int skillId)
    {
        try {
            return new ResponseEntity<>(skillServices.deleteSkillInCourse(courseId, skillId),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getAllSkills/{courseId}")
    @CrossOrigin("*")
    ResponseEntity<List<String>> getAllSkills(@PathVariable ("courseId") int courseId)
    {
        try{
            return  new ResponseEntity<>(skillServices.getAllSkills(courseId),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("getSkills")
    @CrossOrigin("*")
    ResponseEntity<List<Skill>> getAllSkillsFromSKill(){
        try{
            return new ResponseEntity<>(skillServices.getAllSkillsFromSkill(),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
