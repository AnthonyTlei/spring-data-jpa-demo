package com.anthonytlei.spring_data_jpa_demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			Student student1 = new Student("student1", "student1", "student1@user.com", 21);
			Student student2 = new Student("student2", "student2", "student2@user.com", 22);
			List<Student> students = new ArrayList<Student>();
			students.add(student1);
			students.add(student2);
			studentRepository.saveAll(students);
			// Optional<Student> studentOptional = studentRepository.findByEmail("student1@user.com");
			// if (studentOptional.isPresent()) {
			// 	System.out.println("Found student");
			// }
			// studentRepository.findByFirstName("student1").forEach(System.out::println);
			// studentRepository.findByFirstNameNative("student1").forEach(System.out::println);
			// studentRepository.findByAgeIsGreaterThan(20).forEach(System.out::println);
			// studentRepository.deleteByEmail("student1@user.com");
			// studentRepository.findAll().forEach(System.out::println);
		};
	}

}
