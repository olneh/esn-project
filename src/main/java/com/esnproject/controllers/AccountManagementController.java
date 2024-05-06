package com.esnproject.controllers;

import com.esnproject.entities.Member;
import com.esnproject.services.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountManagementController {

    private final MemberService memberService;

    public AccountManagementController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createMember(@RequestBody Member member) {
        if (member == null || member.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid member data");
        }
        Member optionalMember = memberService.getMemberByEmail(member.getEmail());
        if (optionalMember != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with same email already exists");
        }
        String hashedPassword = PasswordHasher.hashPassword(member.getPassword());
        member.setPassword(hashedPassword);
        Member createdMember = memberService.createMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody Member member) {
        Member memberByEmail = memberService.getMemberByEmail(member.getEmail());
        if (memberByEmail != null) {
            if (PasswordHasher.checkPass(member.getPassword(), memberByEmail.getPassword())) {
                return ResponseEntity.ok("User logged in successfully");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}