package com.example.api_sem4.service;

import com.example.api_sem4.dto.ClassDTO;
import com.example.api_sem4.entity.Class;
import com.example.api_sem4.repository.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {
    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public List<Class> getAllClass() {
        return classRepository.findAll();
    }

    public Class createClass(ClassDTO classDTO) {
        Class aClass = new Class();
        aClass.setName(classDTO.getName());
        aClass.setGrade(classDTO.getGrade());
        return classRepository.save(aClass); // <-- Save vào DB luôn
    }

    public Class updateClass(Long id, ClassDTO classDTO) {
        Optional<Class> optionalClass = classRepository.findById(id);
        if (optionalClass.isPresent()) {
            Class existingClass = optionalClass.get();
            existingClass.setName(classDTO.getName());
            existingClass.setGrade(classDTO.getGrade());
            return classRepository.save(existingClass);
        } else {
            throw new RuntimeException("Class not found with id: " + id);
        }
    }

    public void deleteClass(Long id) {
        classRepository.deleteById(id);
    }

    public Optional<Class> getClassById(Long id) {
        return classRepository.findById(id);
    }
}
