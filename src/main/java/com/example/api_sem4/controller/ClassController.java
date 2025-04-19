package com.example.api_sem4.controller;

import com.example.api_sem4.dto.ClassDTO;
import com.example.api_sem4.entity.Class;
import com.example.api_sem4.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/classes")

public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    // Get all classes
    @GetMapping
    public ResponseEntity<List<Class>> getAllClasses() {
        List<Class> classes = classService.getAllClass();
        return ResponseEntity.ok(classes);
    }

    // Create a new class
    @PostMapping("/add")
    public ResponseEntity<Class> addClass(@RequestBody ClassDTO classDTO) {
        Class savedClass = classService.createClass(classDTO);
        return ResponseEntity.ok(savedClass);
    }

    // Update a class
    @PutMapping("/{id}")
    public ResponseEntity<Class> updateClass(@PathVariable Long id, @RequestBody ClassDTO classDTO) {
        try {
            Class updatedClass = classService.updateClass(id, classDTO);
            return ResponseEntity.ok(updatedClass);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a class
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        if (classService.getClassById(id).isPresent()) {
            classService.deleteClass(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get a class by ID (optional)
    @GetMapping("/{id}")
    public ResponseEntity<Class> getClassById(@PathVariable Long id) {
        return classService.getClassById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
