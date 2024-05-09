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

    @GetMapping("/{eventId}/eventMemberIds") //backend only
    public ResponseEntity<List<Long>> getEventMembers(@PathVariable Long eventId) {
        List<MemberEvent> members = memberEventService.getMembersByEventId(eventId);
        List<Long> memberIds = members.stream()
                .map(memberEvent -> memberEvent.getMemberReceiver().getId())
                .toList();

        return new ResponseEntity<>(memberIds, HttpStatus.OK);
    }

    @PostMapping("/registerForEvent")
    public ResponseEntity<String> registerMemberEvent(@RequestBody MemberEvent memberEvent) {
        try {
            memberEventService.saveMemberEvent(memberEvent);
            return new ResponseEntity<>(memberEvent.toString(), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/addPoints")
    public ResponseEntity<String> addMemberEvent(@RequestBody MemberEvent memberEvent) {
        try {
            MemberEvent memberEvent1 = memberEventService.addMemberEventPoints(memberEvent.getEvent().getId(), memberEvent.getMemberManager().getId(), memberEvent.getMemberReceiver().getId(), memberEvent.getPoints());
            return new ResponseEntity<>(memberEvent1.getEvent().getId().toString(), HttpStatus.OK);
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
