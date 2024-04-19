package com.example.esnproject.services;

import com.example.esnproject.entities.Member;
import com.example.esnproject.entities.MemberEvent;
import com.example.esnproject.repositories.MemberEventRepository;
import com.example.esnproject.repositories.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberEventService {

    @Autowired
    private MemberEventRepository memberEventRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public MemberEvent registerMemberForEvent (Long eventId, Long memberId, String task){
        memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));

        Optional<MemberEvent> existingEvent = memberEventRepository.findByEventIdAndMemberReceiverId(eventId, memberId);
        if (existingEvent.isPresent()) {
            throw new RuntimeException("This member has already registered for this event, no double registration allowed.");
        } else {
            MemberEvent memberEvent = new MemberEvent();
            memberEvent.setEventId(eventId);
            memberEvent.setMemberReceiverId(memberId);
            memberEvent.setTask(task);
            memberEvent.setPoints(0);

            memberEventRepository.save(memberEvent);
            return memberEvent;
        }
    }

    @Transactional
    public MemberEvent addMemberEventPoints(Long eventId, Long managerId, Long receiverId, int points) {
        memberRepository.findById(managerId).orElseThrow(() -> new RuntimeException("Member not found"));
        Member receiver = memberRepository.findById(receiverId).orElseThrow(() -> new RuntimeException("Member not found"));

        Optional<MemberEvent> existingEvent = memberEventRepository.findByEventIdAndMemberReceiverId(eventId, receiverId);
        if (existingEvent.isPresent()) {
            MemberEvent memberEvent = existingEvent.get();
            if (memberEvent.getPoints() > 0) {
                throw new RuntimeException("Receiver has already received points for this event, no further modifications allowed.");
            } else {
                memberEvent.setPoints(points);

                memberEventRepository.save(memberEvent);
                receiver.setPoints(receiver.getPoints() + points);
                memberRepository.save(receiver);
                return memberEvent;
            }
        }
        throw new RuntimeException("There is no event with the id " + eventId + "to give points for");
    }
    }
