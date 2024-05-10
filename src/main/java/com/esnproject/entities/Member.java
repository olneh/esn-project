package com.esnproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "points")
    private Integer points = 0;

    @Column(name = "password")
    private String password;

    @Column(name = "photo_url")
    private String photoUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "memberReceiver")
    private List<MemberEvent> memberEvents;

    @JsonIgnore
    @OneToMany(mappedBy = "memberManager")
    private List<MemberEvent> managedMemberEvents;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<MemberRole> memberRoles;
}
