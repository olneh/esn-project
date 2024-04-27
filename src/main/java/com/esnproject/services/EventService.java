package com.esnproject.services;

import com.esnproject.entities.Event;
import com.esnproject.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Event event = eventRepository.findById(eventId).get();
        event.setEventTitle(updatedEvent.getEventTitle());
        event.setEventDate(updatedEvent.getEventDate());
        event.setAttendanceType(updatedEvent.getAttendanceType());
        event.setComment(updatedEvent.getComment());
        event.setHelpersNeeded(updatedEvent.getHelpersNeeded());
        return eventRepository.save(event);
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }
}
