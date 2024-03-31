package com.example.esnproject.controllers;

import com.example.esnproject.entities.Member;
import com.example.esnproject.repositories.MemberRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final MemberRepository dao;

    public RegistrationController(MemberRepository dao) {
        this.dao = dao;
    }

    @PostMapping("api/v1/identity/account/register")
    public Member save(@RequestBody Member member){
        return dao.save(member);
    }

}
