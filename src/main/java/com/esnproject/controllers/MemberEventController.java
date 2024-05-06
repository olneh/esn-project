package com.esnproject.controllers;

import com.esnproject.entities.MemberEvent;
import com.esnproject.services.MemberEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberEvents")
public class MemberEventController {

    private final MemberEventService memberEventService;

    public MemberEventController(MemberEventService memberEventService) {
        this.memberEventService = memberEventService;
    }

    @GetMapping("/{eventId}/eventMemberNames")
    public ResponseEntity<List<String>> getMemberNamesForEvent(@PathVariable Long eventId) {
        List<String> memberNames = memberEventService.getMemberNamesByEventId(eventId);
        return new ResponseEntity<>(memberNames, HttpStatus.OK);
    }

    @GetMapping("/{eventId}/eventMembers") //backend only
    public ResponseEntity<List<MemberEvent>> getEventMembers(@PathVariable Long eventId) {
        List<MemberEvent> members = memberEventService.getMembersByEventId(eventId);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }


    @PostMapping("/registerForEvent")
    public ResponseEntity<String> registerMemberEvent(
            @RequestParam("event_id") Long eventId,
            @RequestParam("member_receiver_id") Long memberId,
            @RequestParam("task") String task) {
        try {
            MemberEvent memberEvent = memberEventService.registerMemberForEvent(eventId, memberId, task);
            return new ResponseEntity<>(memberEvent.getEvent().getId().toString(), HttpStatus.CREATED);
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
            return new ResponseEntity<>(memberEvent.getEvent().getId().toString(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{memberId}/events")
    public List<MemberEvent> getEventsForMember(@PathVariable Long memberId) {
        System.out.println(memberId.toString());
        System.out.println(memberEventService.getEventsForMember(memberId));
        return memberEventService.getEventsForMember(memberId);
    }
}
