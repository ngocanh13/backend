package com.example.api_sem4.dto;

import com.example.api_sem4.entity.AttendanceEnum;

import java.sql.Time;
import java.time.LocalDate;


public class AttendanceDTO {
    private Long id;
    private Long student;
    private AttendanceEnum status;  // Enum cho trạng thái
    private LocalDate date;
    private Time time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudent() {
        return student;
    }

    public void setStudent(Long student) {
        this.student = student;
    }

    public AttendanceEnum getStatus() {
        return status;
    }

    public void setStatus(AttendanceEnum status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
