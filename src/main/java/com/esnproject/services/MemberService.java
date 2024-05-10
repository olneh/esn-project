package com.esnproject.services;

import com.esnproject.entities.Member;
import com.esnproject.entities.MemberRole;
import com.esnproject.repositories.MemberRepository;
import com.esnproject.repositories.MemberRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;

    public MemberService(MemberRepository memberRepository, MemberRoleRepository memberRoleRepository) {
        this.memberRepository = memberRepository;
        this.memberRoleRepository = memberRoleRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));
    }

    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member createMember(Member member) {

        member = memberRepository.save(member);

        MemberRole memberRole = new MemberRole();
        memberRole.setMember(member);
        memberRole.setMemberLevel("member");
        memberRoleRepository.save(memberRole);

        return member;
    }

    public Member updateMember(Long memberId, Member updatedMember) {
        if (!memberRepository.existsById(memberId)) {
            throw new RuntimeException("Member not found with id: " + memberId);
        }
        return memberRepository.save(updatedMember);
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
