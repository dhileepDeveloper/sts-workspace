package com.dhileep.studentdb.repo;

import org.springframework.data.repository.CrudRepository;

import com.dhileep.studentdb.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
