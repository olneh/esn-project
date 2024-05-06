package com.esnproject.services;

import com.esnproject.entities.Event;
import com.esnproject.repositories.EventRepository;
import com.esnproject.repositories.MemberRepository;
import com.esnproject.entities.Member;
import com.esnproject.entities.MemberEvent;
import com.esnproject.repositories.MemberEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberEventService {

    private final MemberEventRepository memberEventRepository;
    private final MemberRepository memberRepository;
    private final EventRepository eventRepository;

    public MemberEventService(MemberEventRepository memberEventRepository, MemberRepository memberRepository, EventRepository eventRepository) {
        this.memberEventRepository = memberEventRepository;
        this.memberRepository = memberRepository;
        this.eventRepository = eventRepository;
    }

    public List<String> getMemberNamesByEventId(Long eventId) {
        return memberEventRepository.findMemberNamesByEventId(eventId);
    }

    public List<MemberEvent> getMembersByEventId(Long eventId) {
        return memberEventRepository.findByEventId(eventId);
    }

    public List<MemberEvent> getEventsForMember(Long memberReceiverId) {
        return memberEventRepository.findByMemberReceiverId(memberReceiverId);
    }

    @Transactional
    public MemberEvent registerMemberForEvent(Long eventId, Long memberId, String task) {
        validateMemberExists(memberId);

        Optional<MemberEvent> existingEvent = memberEventRepository.findByEventIdAndMemberReceiverId(eventId, memberId);
        if (existingEvent.isPresent()) {
            throw new RuntimeException("This member has already registered for this event, no double registration allowed.");
        }

        return createAndSaveMemberEvent(eventId, memberId, task);
    }

    @Transactional
    public MemberEvent addMemberEventPoints(Long eventId, Long managerId, Long receiverId, int points) {
        validateMemberExists(managerId);
        Member receiver = validateMemberExists(receiverId);

        Optional<MemberEvent> existingEvent = memberEventRepository.findByEventIdAndMemberReceiverId(eventId, receiverId);
        if (existingEvent.isPresent()) {
            MemberEvent memberEvent = existingEvent.get();
            if (memberEvent.getPoints() > 0) {
                throw new RuntimeException("Receiver has already received points for this event, no further modifications allowed.");
            }

            memberEvent.setPoints(points);
            memberEventRepository.save(memberEvent);

            receiver.setPoints(receiver.getPoints() + points);
            memberRepository.save(receiver);

            return memberEvent;
        }

        throw new RuntimeException("There is no event with the id " + eventId + " to give points for.");
    }

    private Member validateMemberExists(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + memberId));
    }

    private Event validateEventExists(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventId));
    }

    private MemberEvent createAndSaveMemberEvent(Long eventId, Long memberId, String task) {
        MemberEvent memberEvent = new MemberEvent();
        Member member = validateMemberExists(memberId);
        Event event = validateEventExists(eventId);

        memberEvent.setMemberReceiver(member);
        memberEvent.setEvent(event);
        memberEvent.setTask(task);
        memberEvent.setPoints(0);

        memberEventRepository.save(memberEvent);
        return memberEvent;
    }
}