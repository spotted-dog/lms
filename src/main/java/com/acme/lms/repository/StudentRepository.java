package com.acme.lms.repository;

import com.acme.lms.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
}
