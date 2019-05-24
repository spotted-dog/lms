package com.acme.lms.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Student {
  @Id
  private String id;

  @NotNull
  private String name;

  @ManyToMany
  @JoinTable(
      name = "registration",
      joinColumns = {@JoinColumn(name = "student_id")},
      inverseJoinColumns = {@JoinColumn(name = "course_id")}
  )
  private List<Course> registrations;

  public Student() {
  }

  public Student(String id, String name) {
    this.id = id;
    this.name = name;
    this.registrations = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Course> getRegistrations() {
    return registrations;
  }

  public void setRegistrations(List<Course> registrations) {
    this.registrations = registrations;
  }

  @Override
  public String toString() {
    String student = "{ \"id\": \"" + getId() + "\"" +
        ", \"name\": \"" + getName() + "\"" +
        ", \"courses\": [ ";

    int numberOfCourses = getRegistrations().size();
    Course lastCourse = numberOfCourses > 1 ? registrations.get(numberOfCourses - 1) : null;
    StringBuilder result = new StringBuilder(student);

    for (Course course : registrations) {
      result.append(course.toString());

      if ((numberOfCourses > 1) && (course != lastCourse)) {
        result.append(", ");
      }
    }

    result.append(" ] }");

    return result.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return id.equals(student.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
