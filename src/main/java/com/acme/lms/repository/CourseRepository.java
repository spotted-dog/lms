package com.acme.lms.repository;

import com.acme.lms.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CourseRepository extends CrudRepository<Course, String> {
}
