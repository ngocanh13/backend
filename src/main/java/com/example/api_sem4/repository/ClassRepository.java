package com.example.api_sem4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api_sem4.entity.Class;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
}
