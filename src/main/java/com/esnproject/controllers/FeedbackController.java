package com.esnproject.controllers;

import com.esnproject.entities.Event;
import com.esnproject.entities.Feedback;
import com.esnproject.services.EventService;
import com.esnproject.services.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedback/")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final EventService eventService;

    public FeedbackController(FeedbackService feedbackService, EventService eventService) {
        this.feedbackService = feedbackService;
        this.eventService = eventService;
    }

    @GetMapping
    public List<Feedback> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

    @GetMapping("{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        Optional<Feedback> feedback = feedbackService.getFeedbackById(id);

        if (feedback.isPresent()) {
            return ResponseEntity.ok(feedback.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback, @RequestParam Long eventId) {
        Event event = eventService.getEventById(eventId);
        if (event == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        feedback.setEvent(event);
        Feedback createdFeedback = feedbackService.createFeedback(feedback);
        return ResponseEntity.ok(createdFeedback);
    }

    @PutMapping("{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long id, @RequestBody Feedback newFeedback) {
        try {
            Feedback updatedFeedback = feedbackService.updateFeedback(id, newFeedback);
            return ResponseEntity.ok(updatedFeedback);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        try {
            feedbackService.deleteFeedback(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
