package com.esnproject.services;

import com.esnproject.entities.Feedback;
import com.esnproject.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }

    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Feedback updateFeedback(Long id, Feedback newFeedback) {
        Optional<Feedback> feedbackOpt = feedbackRepository.findById(id);

        if (feedbackOpt.isPresent()) {
            Feedback feedback = feedbackOpt.get();
            feedback.setComment(newFeedback.getComment());
            return feedbackRepository.save(feedback);
        } else {
            throw new IllegalArgumentException("Feedback not found for ID: " + id);
        }
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
