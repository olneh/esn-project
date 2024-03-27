package com.example.esnproject.services;

import com.example.esnproject.entities.Member;
import com.example.esnproject.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                                .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

//    public Member updateMember(Long memberId, Member updatedMember) {
//        if (!memberRepository.existsById(memberId)) {  // Check if the member with the given ID exists
//            throw new RuntimeException("Member not found with id: " + memberId);
//        }
//
//        updatedMember.setMemberId(memberId); // Ensure the ID is set correctly
//        return memberRepository.save(updatedMember);
//    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
