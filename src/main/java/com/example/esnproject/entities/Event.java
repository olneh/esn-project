package com.example.esnproject.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(name = "event_title")
    private String eventTitle;

    @Column(name = "event_date")
    private Date event_date;

    @Column(name = "attendance_type")
    private String attendanceType;

    @Column(name = "comment")
    private String comment;

    @Column(name = "helpers_needed")
    private Integer helpersNeeded;

    // Getters and setters
}