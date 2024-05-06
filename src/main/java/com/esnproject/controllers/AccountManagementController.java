package com.esnproject.controllers;

import com.esnproject.entities.Member;
import com.esnproject.services.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountManagementController {

    private final MemberService memberService;

    public AccountManagementController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        String hashedPassword = PasswordHasher.hashPassword(member.getPassword());
        member.setPassword(hashedPassword);
        Member createdMember = memberService.createMember(member);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody Member member) {
        Member existingMember = memberService.getMemberByEmail(member.getEmail());
        if (existingMember != null && PasswordHasher.checkPass(member.getPassword(), existingMember.getPassword())) {
            return ResponseEntity.ok("User logged in successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}