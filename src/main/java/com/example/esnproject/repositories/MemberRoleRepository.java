package com.example.esnproject.repositories;

import com.example.esnproject.entities.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {
    // Add custom query methods if needed
}