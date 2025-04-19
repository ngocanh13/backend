package com.example.api_sem4.controller;

import com.example.api_sem4.entity.Attendance;
import com.example.api_sem4.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // ✅ GET all hoặc theo ngày (dùng LocalDate)
    @GetMapping
    public ResponseEntity<?> getAllAttendances(@RequestParam(required = false) String date) {
        try {
            if (date != null) {
                // Parse từ String về LocalDate
                LocalDate localDate = LocalDate.parse(date); // format yyyy-MM-dd
                List<Attendance> attendances = attendanceService.getAttendancesByDate(localDate);
                return ResponseEntity.ok(attendances);
            } else {
                return ResponseEntity.ok(attendanceService.getAllAttendances());
            }
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Sai định dạng ngày! Dùng định dạng yyyy-MM-dd");
        }
    }

    // POST thêm mới điểm danh
    @PostMapping("/add")
    public ResponseEntity<?> createAttendance(@RequestBody Attendance attendance) {
        try {
            Attendance savedAttendance = attendanceService.saveAttendance(attendance);
            return ResponseEntity.ok(savedAttendance);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Lỗi khi lưu điểm danh: " + e.getMessage());
        }
    }
}
