package com.acme.lms.controller;

import com.acme.lms.model.Course;
import com.acme.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class CourseController {
  @Autowired
  private CourseService courseService;

  @PostMapping("/course")
  public ResponseEntity<Void> addCourse(@RequestBody Course course, UriComponentsBuilder builder) {
    courseService.addCourse(course);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(builder.path("/courses/{courseId}").buildAndExpand(course.getId()).toUri());

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @DeleteMapping("/courses/{courseId}")
  public void deleteCourse(@PathVariable String courseId) {
    courseService.deleteCourse(courseId);
  }

  @GetMapping("/courses")
  public List<Course> retrieveAllCourse() {
    return courseService.retrieveAllCourses();
  }

  @GetMapping("/courses/{courseId}")
  public Course retrieveCourse(@PathVariable String courseId) {
    return courseService.retrieveCourse(courseId);
  }
}
