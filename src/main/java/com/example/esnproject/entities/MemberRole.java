package com.example.esnproject.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "member_role")
public class MemberRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_role_id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "member_level")
    private Integer memberLevel;

    // Getters and setters

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
    private Member member;
}
