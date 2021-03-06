package com.acme.lms.controller;

import com.acme.lms.model.Course;
import com.acme.lms.model.Student;
import com.acme.lms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class StudentController {
  @Autowired
  private StudentService studentService;

  @PostMapping("/student")
  public ResponseEntity<Void> addStudent(@RequestBody Student student, UriComponentsBuilder builder) {
    studentService.addStudent(student);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(builder.path("/students/{studentId}").buildAndExpand(student.getId()).toUri());

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @DeleteMapping("/students/{studentId}")
  public void deleteStudent(@PathVariable String studentId) {
    studentService.deleteStudent(studentId);
  }

  @GetMapping("/students")
  public List<Student> retrieveAllStudents() {
    return studentService.retrieveAllStudents();
  }

  @GetMapping("/students/{studentId}")
  public Student retrieveStudent(@PathVariable String studentId) {
    return studentService.retrieveStudent(studentId);
  }

  @GetMapping("/students/{studentId}/courses")
  public List<Course> retrieveCourseForStudent(@PathVariable String studentId) {
    return studentService.retrieveCourses(studentId);
  }

  @GetMapping("/students/{studentId}/courses/{courseId}")
  public Course retrieveDetailsForCourse(@PathVariable String studentId, @PathVariable String courseId) {
    return studentService.retrieveCourse(studentId, courseId);
  }

  @PostMapping("/students/{studentId}/courses")
  public ResponseEntity<Void> registerStudentForCourse(@PathVariable String studentId, @RequestBody Course newCourse) {
    studentService.addCourse(studentId, newCourse);

    return ResponseEntity.noContent().build();
  }
}
