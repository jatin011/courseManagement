package com.example.MsProject.feedback.Model;

import java.sql.Timestamp;

public class Feedback {

    private String participantName;
    private int feedbackId;
    private int courseId;
    private String feedbackText;
    private float rating;
    private Timestamp createdAt;

    public Feedback(){}


//    public Feedback(String participantName, int courseId, String feedbackText, float rating) {
//        this.participantName = participantName;
//        this.courseId = courseId;
//        this.feedbackText = feedbackText;
//        this.rating = rating;
//    }
//
//    public Feedback(String participantName, int feedbackId, int courseId, String feedbackText, float rating, Timestamp createdAt) {
//        this.participantName = participantName;
//        this.feedbackId = feedbackId;
//        this.courseId = courseId;
//        this.feedbackText = feedbackText;
//        this.rating = rating;
//        this.createdAt = createdAt;
//    }



    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "participantName='" + participantName + '\'' +
                ", feedbackId=" + feedbackId +
                ", courseId=" + courseId +
                ", feedbackText='" + feedbackText + '\'' +
                ", rating=" + rating +
                ", createdAt=" + createdAt +
                '}';
    }
}
