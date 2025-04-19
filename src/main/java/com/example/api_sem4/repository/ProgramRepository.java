package com.example.api_sem4.repository;

import com.example.api_sem4.entity.Programs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Programs, Long> {
}
