package com.example.registrationlogindemo.controller;

import java.util.List;
import java.util.Map;

import com.example.registrationlogindemo.entity.Student;
import com.example.registrationlogindemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentControler {

    @Autowired
    StudentRepository repo;
    //get all the students
    //localhost:8080/students
    @GetMapping("/students")
    public List<Student> getAllStudents(){
        List<Student> students = repo.findAll();
        return students;
    }

    //localhost:8080/students/1
    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id) {
        Student student = repo.findById(id).get();
        return student;
    }

    @PostMapping("/student/add")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student newStudent = repo.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);

    }

//    @PutMapping("/student/update/{id}")
//    public Student updateStudents(@PathVariable int id) {
//        Student student = repo.findById(id).get();
//        student.setName("poonam");
//        student.setPercentage(92);
//        repo.save(student);
//        return student;
//    }

    @PutMapping("/student/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        Student student = repo.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id " + id));

        // Iterate through the updates map and update corresponding fields in Student entity
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();

            switch (field) {
                case "name":
                    student.setName((String) value);
                    break;
                case "percentage":
                    student.setPercentage((Integer) value);
                    break;
                case "branch":
                    student.setBranch((String) value);
                    break;
                default:
                    break;
            }
        }

        Student updatedStudent = repo.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/student/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removeStudent(@PathVariable int id) {
        Student student = repo.findById(id).get();
        repo.delete(student);
    }

}