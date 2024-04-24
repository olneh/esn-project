package com.esnproject.repositories;

import com.esnproject.entities.MemberEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberEventRepository extends JpaRepository<MemberEvent, Long> {
    Optional<MemberEvent> findByEventIdAndMemberReceiverId(Long eventId, Long receiverId);

    List<MemberEvent> findByEventId(Long eventId);

    @Query("SELECT m.firstName || ' ' || m.lastName AS name " +
            "FROM MemberEvent me JOIN me.memberReceiver m " +
            "WHERE me.event.id = :eventId")
    List<String> findMemberNamesByEventId(Long eventId);
}