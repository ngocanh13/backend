package com.example.api_sem4.service;

import com.example.api_sem4.dto.ProgramDTO;
import com.example.api_sem4.entity.Programs;
import com.example.api_sem4.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }
    public List<ProgramDTO> getAllPrograms() {
        List<Programs> programs = programRepository.findAll();
        return programs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProgramDTO convertToDTO(Programs program) {
        ProgramDTO dto = new ProgramDTO();
        dto.setId(program.getId());
        dto.setProgramName(program.getProgramName());
        dto.setProgramDescription(program.getProgramDescription());
        dto.setTeacherId(program.getTeacherId());
        dto.setMaxStudents(program.getMaxStudents());
        dto.setTotalHours(program.getTotalHours());
        dto.setTotalSessions(program.getTotalSessions());
        dto.setTuition(program.getTuition());
        dto.setCreatedAt(program.getCreatedAt());
        return dto;
    }

    public Programs createProgram(Programs program) {
        program.setCreatedAt(LocalDateTime.now()); // tự động set ngày giờ hiện tại
        return programRepository.save(program);
    }

}
