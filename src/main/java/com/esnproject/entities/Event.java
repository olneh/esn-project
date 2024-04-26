package com.esnproject.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "event")
public class Event extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(name = "event_title")
    private String eventTitle;

    @Column(name = "event_date")
    private Date eventDate;

    @Column(name = "attendance_type")
    private String attendanceType;

    @Column(name = "comment")
    private String comment;

    @Column(name = "helpers_needed")
    private Integer helpersNeeded;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbackList;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberEvent> memberEvents;
}