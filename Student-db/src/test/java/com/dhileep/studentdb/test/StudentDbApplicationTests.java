package com.dhileep.studentdb.test;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dhileep.studentdb.entities.Student;
import com.dhileep.studentdb.repo.StudentRepository;

@SpringBootTest
class StudentDbApplicationTests {

	@Autowired
	private StudentRepository studentRepo;
	
	@Test
	void testStudentCreate()
	{
		Student student = new Student();
		student.setName("Dhileep");
		student.setCourse("Spring");
		student.setFees(1000.15d);
		studentRepo.save(student);
	}
	
	@Test
	void testStudentRead()
	{
		Optional<Student> findById = studentRepo.findById(1L);
		if(findById.isPresent())
		{
			System.out.println(findById.get());
		}	
		else
		{
			System.out.println("Table is empty");
		}
	}
	
	@Test
	void testStudentUpdate()
	{
		Student student = studentRepo.findById(1L).get();
		student.setFees(20000.15d);
		studentRepo.save(student);
	}
	
	@Test
	void testStudentDelete()
	{
		Student student = studentRepo.findById(1L).get();
		studentRepo.delete(student);
	}

}
