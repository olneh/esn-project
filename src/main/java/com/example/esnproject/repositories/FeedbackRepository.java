package com.example.esnproject.repositories;

import com.example.esnproject.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // Add custom query methods if needed
}