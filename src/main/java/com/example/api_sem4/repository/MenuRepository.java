package com.example.api_sem4.repository;

import com.example.api_sem4.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByDayOfWeek(String dayOfWeek); // Đổi 'findByDay_of_week' thành 'findByDayOfWeek'
}
