package com.example.MsProject.Controller;

import com.example.MsProject.TrainingMaterial.Services.MaterialService;
import com.example.MsProject.feedback.Controller.feedbackController;
import com.example.MsProject.feedback.Model.Feedback;
import com.example.MsProject.feedback.Services.FeedbackService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = feedbackController.class)
public class FeedbackControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    FeedbackService feedbackService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MaterialService materialService;

    List<Feedback> feedbacks=new ArrayList<>();

    Feedback feedback1;
    Feedback feedback2;


    @BeforeEach
    void init()
    {
        feedback1=new Feedback();
        feedback1.setFeedbackId(1);
        feedback1.setFeedbackText("Kajal is smart");
        feedback1.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        feedback1.setCourseId(1);
        feedback1.setRating(4);
        feedback1.setParticipantName("Jatin Kumar");

        feedback2=new Feedback();
        feedback2.setFeedbackId(2);
        feedback2.setFeedbackText("Kajal Pagal");
        feedback2.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        feedback2.setCourseId(2);
        feedback2.setRating(5);
        feedback2.setParticipantName("Lakshay ");

        feedbacks.add(feedback1);
        feedbacks.add(feedback2);
    }

    @Test
    public void getAllFeedbackByCourseTest() throws Exception
    {
        lenient().when(feedbackService.getFeedbackByCourseId(feedback1.getCourseId())).thenReturn(Arrays.asList(feedback1));

        mockMvc.perform(get("/feedback/all/"+feedback1.getCourseId()))
                .andDo(print())
                .andExpect(status().isOk());

        lenient().when(feedbackService.getFeedbackByCourseId(feedback1.getCourseId())).thenThrow(new Exception());
        mockMvc.perform(get("/feedback/all/"+feedback1.getCourseId()))
                .andDo(print())
                .andExpect(status().isInternalServerError());


    }

    @Test
    public void deleteFeedbackTest() throws Exception
    {
        lenient().when(feedbackService.deleteFeedback(feedback1.getFeedbackId())).thenReturn(true);

        mockMvc.perform(delete("/feedback/delete/"+feedback1.getFeedbackId()))
                .andDo(print())
                .andExpect(status().isOk());

        lenient().when(feedbackService.deleteFeedback(feedback1.getFeedbackId())).thenThrow(new Exception());
        mockMvc.perform(delete("/feedback/delete/"+feedback1.getFeedbackId()))
                .andDo(print())
                .andExpect(status().isInternalServerError());


    }

    @Test
    public void addFeedbackTest() throws Exception
    {
        lenient().when(feedbackService.addFeedback(feedback1)).thenReturn(feedback1);

        String jsonString=objectMapper.writeValueAsString(feedback1);
        mockMvc.perform(post("/feedback/addFeedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andDo(print())
                .andExpect(status().isCreated());




    }

}
