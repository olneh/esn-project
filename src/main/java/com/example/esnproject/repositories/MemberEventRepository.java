package com.example.esnproject.repositories;

import com.example.esnproject.entities.MemberEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberEventRepository extends JpaRepository<MemberEvent, Long> {
    // Add custom query methods if needed
}