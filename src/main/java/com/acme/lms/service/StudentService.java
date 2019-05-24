package com.acme.lms.service;

import com.acme.lms.model.Course;
import com.acme.lms.model.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentService {
  private static List<Student> students = new ArrayList<>();

  static {
    // initialize data
    Course codeOfConduct = new Course("COC101", "Code of Conduct 101", "Detailed CoC Information");
    Course exportImport = new Course("EXIM101", "Export/Import 101", "Basic rules of ExIm");
    Course ergonomics= new Course("EGRO101", "Ergonomics 101", "Office desk setup");

    Student mikeSmith = new Student("1122345", "Mike Smith");
    Student johnCatrone = new Student("1133456", "John Catrone");

    mikeSmith.getRegistrations().add(codeOfConduct);
    mikeSmith.getRegistrations().add(ergonomics);

    johnCatrone.getRegistrations().add(codeOfConduct);
    johnCatrone.getRegistrations().add(exportImport);

    students.add(mikeSmith);
    students.add(johnCatrone);
  }

  public List<Student> retrieveAllStudents() {
    return students;
  }

  public Student retrieveStudent(String studentId) {
    for (Student student: students) {
      if (student.getId().equals(studentId)) {
        return student;
      }
    }

    return null;
  }

  public List<Course> retrieveCourses(String studentId) {
    Student student = retrieveStudent(studentId);

    if (student == null) {
      return null;
    }

    return student.getRegistrations();
  }

  public Course retrieveCourse(String studentId, String courseId) {
    Student student = retrieveStudent(studentId);

    if (student == null) {
      return null;
    }

    for (Course course: student.getRegistrations()) {
      if (course.getId().equals(courseId)) {
        return course;
      }
    }

    return null;
  }

  public void addCourse(String studentId, Course course) {
    Student student = retrieveStudent(studentId);

    if (student == null) {
      return;
    }

    student.getRegistrations().add(course);
  }
}
