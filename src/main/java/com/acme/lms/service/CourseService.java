package com.acme.lms.service;

import com.acme.lms.model.Course;
import com.acme.lms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
  @Autowired
  private CourseRepository courseRepository;

  public synchronized void addCourse(Course course) {
    Optional<Course> courseById = courseRepository.findById(course.getId());

    if (courseById.isPresent()) {
      return;
    }

    courseRepository.save(course);
  }

  public void deleteCourse(String courseId) {
    Optional<Course> courseById = courseRepository.findById(courseId);

    if (courseById.isPresent()) {
      courseRepository.deleteById(courseId);
    }
  }

  public List<Course> retrieveAllCourses() {
    Iterable<Course> all = courseRepository.findAll();
    List<Course> courses = new ArrayList<>();

    all.forEach(course -> courses.add(course));

    return courses;
  }

  public Course retrieveCourse(String courseId) {
    Optional<Course> courseById = courseRepository.findById(courseId);

    return courseById.isPresent() ? courseById.get() : null;
  }
}
