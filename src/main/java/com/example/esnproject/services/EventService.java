package com.example.esnproject.services;

import com.example.esnproject.entities.Event;
import com.example.esnproject.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long eventId, Event updatedEvent) {
        if (!eventRepository.existsById(eventId)) {
            throw new RuntimeException("Event not found with id: " + eventId);
        }
        return eventRepository.save(updatedEvent);
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }
}
