package com.example.MsProject.feedback.Services;

import com.example.MsProject.feedback.Dao.FeedbackDao;
import com.example.MsProject.feedback.Model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.Exception;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    FeedbackDao feedbackDao;

    public Feedback addFeedback(Feedback feedback) throws Exception
    {
        return feedbackDao.addFeedback(feedback);
    }

    public List<Feedback> getFeedbackByCourseId(int courseId)throws Exception
    {
        return feedbackDao.getFeedbackByCourseId(courseId);
    }

    public Boolean deleteFeedback(int feedbackId) throws Exception
    {
        return feedbackDao.deleteFeedback(feedbackId);
    }

    public  Boolean deleteFeedbackByCourse(int courseId) throws Exception
    {
        return feedbackDao.deleteFeedBackByCourse(courseId);
    }
}
