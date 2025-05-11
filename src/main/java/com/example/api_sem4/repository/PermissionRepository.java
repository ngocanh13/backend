package com.example.api_sem4.repository;

import com.example.api_sem4.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByPermission(String permission);

}
