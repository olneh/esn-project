package com.esnproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "member_event")
public class MemberEvent extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_event_id")
    private Long id;

    @Column(name = "task")
    private String task;

    @Column(name = "points")
    private Integer points;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member_receiver_id")
    private Member memberReceiver;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "member_manager_id")
    private Member memberManager;
}