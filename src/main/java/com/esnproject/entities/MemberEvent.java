package com.esnproject.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "member_event")
public class MemberEvent extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_event_id")
    private Long id;

    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "member_receiver_id")
    private Long memberReceiverId;

    @Column(name = "member_manager_id")
    private Long memberManagerId;

    @Column(name = "task")
    private String task;

    @Column(name = "points")
    private Integer points;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id", insertable = false, updatable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "member_receiver_id", referencedColumnName = "member_id", insertable = false, updatable = false)
    private Member memberReceiver;

    @ManyToOne
    @JoinColumn(name = "member_manager_id", referencedColumnName = "member_id", insertable = false, updatable = false)
    private Member memberManager;
}