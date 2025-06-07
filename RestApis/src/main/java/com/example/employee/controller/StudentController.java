package com.example.employee.controller;



import com.example.employee.model.Student;
import com.example.employee.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/students", "/students/"})
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // ✅ PostConstruct debug method
    @PostConstruct
    public void init() {
        System.out.println("✅ StudentController LOADED");
    }



    // GET all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // POST a new student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // GET a student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // PUT update student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setCourse(updatedStudent.getCourse());
        return studentRepository.save(student);
    }

    // DELETE a student
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        System.out.println("✅ DELETE called for student ID: " + id);
        studentRepository.deleteById(id);
    }
}

