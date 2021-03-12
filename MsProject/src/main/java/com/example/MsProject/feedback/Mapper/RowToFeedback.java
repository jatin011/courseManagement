package com.example.MsProject.feedback.Mapper;

import com.example.MsProject.feedback.Model.Feedback;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowToFeedback  {

    public static RowMapper<Feedback> mapRowToFeedbackLambda = ((resultSet, i) -> {
        Feedback feedback=new Feedback();
        feedback.setFeedbackId(resultSet.getInt("feedbackId"));
        feedback.setFeedbackText(resultSet.getString("feedbackText"));
        feedback.setCourseId(resultSet.getInt("courseId"));
        feedback.setCreatedAt(resultSet.getTimestamp("createdAt"));
        feedback.setRating(resultSet.getFloat("rating"));
        feedback.setParticipantName(resultSet.getString("participantName"));
        return  feedback;
    }) ;

}
