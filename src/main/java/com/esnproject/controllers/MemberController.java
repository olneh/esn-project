package com.esnproject.controllers;

import com.esnproject.entities.Member;
import com.esnproject.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // Endpoint to get all members
    @GetMapping("/all")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    // Endpoint to get a single member by ID
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable("id") Long id) {
        Member member = memberService.getMemberById(id);
        if (member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to create a new member
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member createdMember = memberService.createMember(member);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }

    @PostMapping("{memberId}/uploadPhoto")
    public ResponseEntity<String> uploadMemberPhoto(@PathVariable Long memberId, @RequestParam("file") MultipartFile file) {
        try {
            String photoUrl = memberService.uploadMemberPhoto(memberId, file);
            return ResponseEntity.ok().body("Photo uploaded successfully: " + photoUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload the photo: " + e.getMessage());
        }
    }

    // Endpoint to update an existing member
//    @PutMapping("/{id}")
//    public ResponseEntity<Member> updateMember(@PathVariable("id") Long id, @RequestBody Member member) {
//        Member updatedMember = memberService.updateMember(id, member);
//        if (updatedMember != null) {
//            return new ResponseEntity<>(updatedMember, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    // Endpoint to delete a member
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
    }
}
