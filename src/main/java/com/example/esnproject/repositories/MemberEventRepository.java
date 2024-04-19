package com.example.esnproject.repositories;

import com.example.esnproject.entities.MemberEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberEventRepository extends JpaRepository<MemberEvent, Long> {
    Optional<MemberEvent> findByEventIdAndMemberReceiverId(Long eventId, Long receiverId);
}