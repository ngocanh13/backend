package com.example.api_sem4.controller;

import com.example.api_sem4.dto.ProgramDTO;
import com.example.api_sem4.entity.Programs;
import com.example.api_sem4.service.ProgramService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.PrimitiveIterator;

@RestController
@RequestMapping("/programs")
public class ProgramController {
    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }
    @GetMapping
    public List<ProgramDTO> getAllPrograms() {
        return programService.getAllPrograms();
    }
    @PostMapping("/add")
    public Programs createProgram(@RequestBody Programs program) {
        return programService.createProgram(program);
    }
}
