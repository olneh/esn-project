package com.example.esnproject.controllers;

import com.example.esnproject.entities.MemberEvent;
import com.example.esnproject.services.MemberEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/memberEvents")
public class MemberEventController {

    private final MemberEventService memberEventService;

    public MemberEventController(MemberEventService memberEventService) {
        this.memberEventService = memberEventService;
    }

    @PostMapping("/registerForEvent")
    public ResponseEntity<String> registerMemberEvent(
            @RequestParam("event_id") Long eventId,
            @RequestParam("member_receiver_id") Long memberId,
            @RequestParam("task") String task) {
        try {
            MemberEvent memberEvent = memberEventService.registerMemberForEvent(eventId, memberId, task);
            return new ResponseEntity<>(memberEvent.getEventId().toString(), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/addPoints")
    public ResponseEntity<String> addMemberEvent(
            @RequestParam("event_id") Long eventId,
            @RequestParam("member_manager_id") Long memberManagerId,
            @RequestParam("member_receiver_id") Long memberReceiverId,
            @RequestParam("points") Integer points) {
        try {
            MemberEvent memberEvent = memberEventService.addMemberEventPoints(eventId, memberManagerId, memberReceiverId, points);
            return new ResponseEntity<>(memberEvent.getEventId().toString(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
