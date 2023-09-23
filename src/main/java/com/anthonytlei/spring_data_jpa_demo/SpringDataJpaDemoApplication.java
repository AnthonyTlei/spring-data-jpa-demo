package com.anthonytlei.spring_data_jpa_demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.github.javafaker.Faker;

@SpringBootApplication
public class SpringDataJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			generateRandomStudents(studentRepository);
			getStudentsSorted(studentRepository);
			getStudentsPaginated(studentRepository);
		};
	}

	private Page<Student> getStudentsPaginated(StudentRepository studentRepository) {
		PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("firstName").ascending());
		return studentRepository.findAll(pageRequest);
	}

	private List<Student> getStudentsSorted(StudentRepository studentRepository) {
		Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
		return studentRepository.findAll(sort);
	}

	private void generateRandomStudents(StudentRepository studentRepository) {
		Faker faker = new Faker();
		for (int i=0; i < 20; ++i) {
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@user.com", firstName, lastName);
			int age = faker.number().numberBetween(17,55);
			Student student = new Student(firstName, lastName, email, age);
			studentRepository.save(student);
		}
	}
}
