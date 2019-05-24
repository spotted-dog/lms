package com.acme.lms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Course {
  @Id
  private String id;

  @NotNull
  private String name;

  @NotNull
  private String description;

  @ManyToMany(mappedBy = "registrations")
  private List<Student> registrations;

  public Course() {
  }

  public Course(String id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Student> getRegistrations() {
    return registrations;
  }

  public void setRegistrations(List<Student> registrations) {
    this.registrations = registrations;
  }

  @Override
  public String toString() {
    String course = "{ \"id\": \"" + getId() + "\"" +
        ", \"name\": \"" + getName() + "\"" +
        ", \"description\": \"" + getDescription() + "\"" +
        ", \"students\": [ ";

    int numberOfStudents = getRegistrations().size();
    Student lastStudent = numberOfStudents > 1 ? registrations.get(numberOfStudents - 1) : null;
    StringBuilder result = new StringBuilder(course);

    for (Student student : registrations) {
      result.append(student.toString());

      if ((numberOfStudents > 1) && (student != lastStudent)) {
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
    Course course = (Course) o;
    return id.equals(course.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
