package com.esnproject.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "member_role")
public class MemberRole extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_role_id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "member_level")
    private Integer memberLevel;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
    private Member member;
}
