package com.esnproject.services;

import com.esnproject.entities.Member;
import com.esnproject.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Member updateMember(Long memberId, Member updatedMember) {
        if (!memberRepository.existsById(memberId)) {  // Check if the member with the given ID exists
            throw new RuntimeException("Member not found with id: " + memberId);
        }

//        updatedMember.setMemberId(memberId); // Ensure the ID is set correctly
        return memberRepository.save(updatedMember);
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    //members
    public List<Member> getMembers() {
       return memberRepository.findAll();
    }


//todo check logic
    public String uploadMemberPhoto(Long memberId, MultipartFile file) throws IOException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uploadDir = "member-photos/" + memberId;

        // Save the file to the file system
        saveFile(uploadDir, fileName, file);

        // Update the member's photo URL
        String photoUrl = "/member-photos/" + memberId + "/" + fileName;
        member.setPhotoUrl(photoUrl);
        memberRepository.save(member);

        return photoUrl;
    }

    private void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}
