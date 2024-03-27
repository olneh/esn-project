package com.example.esnproject.repositories;

import com.example.esnproject.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    // Add custom query methods if needed
}