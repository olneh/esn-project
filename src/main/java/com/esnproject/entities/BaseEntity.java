package com.esnproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created;

    @Column(name = "last_modified", nullable = false)
    private LocalDateTime lastModified;


    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.created = now;
        this.lastModified = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModified = LocalDateTime.now();
    }
}