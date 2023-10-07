package com.anthonytlei.spring_data_jpa_demo;

import java.time.LocalDateTime;
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
	CommandLineRunner commandLineRunner(StudentRepository studentRepository,
			StudentIdCardRepository studentIdCardRepository) {
		return args -> {
			Faker faker = new Faker();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@user.com", firstName, lastName);
			int age = faker.number().numberBetween(17, 55);
			Student student = new Student(firstName, lastName, email, age);

			StudentIdCard studentIdCard = new StudentIdCard("123456789", student);

			student.setStudentIdCard(studentIdCard);

			student.addBook(new Book("Book 1", LocalDateTime.now().minusDays(4L)));
			student.addBook(new Book("Book 2", LocalDateTime.now().minusDays(10L)));
			student.addBook(new Book("Book 3", LocalDateTime.now().minusDays(15L)));

			student.addCourse(new Course("Course 1", "D1"));
			student.addCourse(new Course("Course 2", "D1"));
			student.addCourse(new Course("Course 3", "D3"));

			studentRepository.save(student);

			studentRepository.findById(1L).ifPresent(System.out::println);

			studentRepository.findById(1L).ifPresent(s -> {
				System.out.println("Fetching student data...");
				System.out.println(student.getStudentIdCard());
				student.getBooks().forEach(book -> {
					System.out.println(book);
				});
				student.getCourses().forEach(course -> {
					System.out.println(course);
				});
			});
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
		for (int i = 0; i < 20; ++i) {
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@user.com", firstName, lastName);
			int age = faker.number().numberBetween(17, 55);
			Student student = new Student(firstName, lastName, email, age);
			studentRepository.save(student);
		}
	}
}
