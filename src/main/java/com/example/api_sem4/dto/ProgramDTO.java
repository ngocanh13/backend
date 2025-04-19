package com.example.api_sem4.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProgramDTO {

    private Long id;
    private String programName;
    private String programDescription;
    private Integer teacherId;
    private Integer maxStudents;
    private Integer totalHours;
    private Integer totalSessions;
    private BigDecimal tuition;
    private LocalDateTime createdAt;

    // Constructors
    public ProgramDTO() {
    }

    public ProgramDTO(Long id, String programName, String programDescription, Integer teacherId,
                       Integer maxStudents, Integer totalHours, Integer totalSessions,
                       BigDecimal tuition, LocalDateTime createdAt) {
        this.id = id;
        this.programName = programName;
        this.programDescription = programDescription;
        this.teacherId = teacherId;
        this.maxStudents = maxStudents;
        this.totalHours = totalHours;
        this.totalSessions = totalSessions;
        this.tuition = tuition;
        this.createdAt = createdAt;
    }

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(Integer maxStudents) {
        this.maxStudents = maxStudents;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public Integer getTotalSessions() {
        return totalSessions;
    }

    public void setTotalSessions(Integer totalSessions) {
        this.totalSessions = totalSessions;
    }

    public BigDecimal getTuition() {
        return tuition;
    }

    public void setTuition(BigDecimal tuition) {
        this.tuition = tuition;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}