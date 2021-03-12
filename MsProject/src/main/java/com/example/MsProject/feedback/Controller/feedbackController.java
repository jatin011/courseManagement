package com.example.MsProject.feedback.Controller;

import com.example.MsProject.feedback.Model.Feedback;
import com.example.MsProject.feedback.Services.FeedbackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class feedbackController {


    @Autowired
    FeedbackService feedbackService;

    @GetMapping("all/{courseId}")
    @CrossOrigin("*")
    public ResponseEntity<List<Feedback>> getAllFeedbackByCourse(@PathVariable ("courseId") int courseId)
    {
        try{
            return new ResponseEntity<>(feedbackService.getFeedbackByCourseId(courseId), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("delete/{feedbackId}")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> deleteFeedback(@PathVariable ("feedbackId")int feedbackId)
    {
        try {
            return  new ResponseEntity<>(feedbackService.deleteFeedback(feedbackId),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/addFeedback")
    @CrossOrigin("*")
    public ResponseEntity<Object> addFeedback(@RequestBody Feedback feedback)
    {
        try{
            return new ResponseEntity<>(feedbackService.addFeedback(feedback),HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Invalid Request to add feedback",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
