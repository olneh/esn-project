package com.example.esnproject.controllers;

import com.example.esnproject.entities.Member;
import com.example.esnproject.repositories.MemberRepository;
import com.example.esnproject.services.MemberService;
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
    public Member saveMember(@RequestBody Member member){
        return memberService.createMember(member);
    }

    @GetMapping("/allMembers")
    public List<Member> getMembers(){
        return memberService.getMembers();
    }

//    @PostMapping("/login")
//    public Member loginMember(@RequestBody Member member){
//        return memberRepository.save(member);
//    }
//
//    @GetMapping("/allMembers")
//    public List<Member> getMembers(@RequestBody Member member){
//        return memberRepository.findAll();
//    }
}