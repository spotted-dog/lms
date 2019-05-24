package com.acme.lms.service;

import com.acme.lms.model.Course;
import com.acme.lms.model.Student;
import com.acme.lms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
  @Autowired
  private StudentRepository studentRepository;

  public List<Student> retrieveAllStudents() {
    Iterable<Student> all = studentRepository.findAll();
    List<Student> students = new ArrayList<>();

    all.forEach(student -> students.add(student));

    return students;
  }

  public Student retrieveStudent(String studentId) {
    Optional<Student> studentById = studentRepository.findById(studentId);

    return studentById.isPresent() ? studentById.get() : null;
  }

  public List<Course> retrieveCourses(String studentId) {
    Optional<Student> studentById = studentRepository.findById(studentId);

    if (studentById.isPresent()) {
      Student student = studentById.get();
      List<Course> courses = student.getRegistrations();

      return courses;
    }

    return null;
  }

  public Course retrieveCourse(String studentId, String courseId) {
    Optional<Student> studentById = studentRepository.findById(studentId);

    if (studentById.isPresent()) {
      Student student = studentById.get();
      List<Course> courses = student.getRegistrations();

      for (Course course : courses) {
        if (course.getId().equals(courseId)) {
          return course;
        }
      }
    }

    return null;
  }

  public void addCourse(String studentId, Course course) {
    Optional<Student> studentById = studentRepository.findById(studentId);

    if (studentById.isPresent()) {
      Student student = studentById.get();

      student.getRegistrations().add(course);
    }
  }

  public void deleteStudent(String studentId) {
    studentRepository.deleteById(studentId);
  }

  public synchronized void addStudent(Student student) {
    Optional<Student> studentById = studentRepository.findById(student.getId());

    if (studentById.isPresent()) {
      return;
    }

    studentRepository.save(student);
  }
}
