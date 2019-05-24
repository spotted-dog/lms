package com.acme.lms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Course {
  @Id
  private String id;

  @NotNull
  private String name;

  @NotNull
  private String description;

  public Course() {
  }

  public Course(String id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;

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

  @Override
  public String toString() {
    return String.format("{ \"id\": \"%s\", \"name\": \"%s\", \"description\": \"%s\" }");
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
