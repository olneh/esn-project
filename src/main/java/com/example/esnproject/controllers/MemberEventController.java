package com.example.esnproject.controllers;

import com.example.esnproject.entities.Event;
import com.example.esnproject.entities.MemberEvent;
import com.example.esnproject.services.EventService;
import com.example.esnproject.services.MemberEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberEvents")
public class MemberEventController {

    @Autowired
    private MemberEventService memberEventService;

    @PostMapping("/registerForEvent")
    public ResponseEntity<String> registerMemberEvent(
            @RequestParam("event_id") Long eventId,
            @RequestParam("member_receiver_id") Long memberId,
            @RequestParam("task") String task) {
        MemberEvent memberEvent = memberEventService.registerMemberForEvent(eventId, memberId, task);
        return new ResponseEntity<>(memberEvent.getEventId().toString(), HttpStatus.CREATED);
    }

    @PostMapping("/addPoints")
    public ResponseEntity<String> addMemberEvent(
            @RequestParam("event_id") Long eventId,
            @RequestParam("member_manager_id") Long memberManagerId,
            @RequestParam("member_receiver_id") Long memberReceiverId,
            @RequestParam("points") Integer points,
            @RequestParam("task") String task) {
        MemberEvent memberEvent = memberEventService.addMemberEventPoints(eventId, memberManagerId, memberReceiverId, points);
        return new ResponseEntity<>(memberEvent.getEventId().toString(), HttpStatus.CREATED);
    }
}
