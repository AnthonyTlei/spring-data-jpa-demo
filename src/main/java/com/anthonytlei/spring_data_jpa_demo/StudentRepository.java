package com.anthonytlei.spring_data_jpa_demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findByEmail(String email);
    @Query("SELECT s FROM Student s WHERE s.firstName = ?1")
    List<Student> findByFirstName(String firstName);
    @Query("SELECT s FROM Student s WHERE s.age > ?1")
    List<Student> findByAgeIsGreaterThan(Integer age);
    @Query(value = "SELECT * FROM student WHERE first_name = :firstName", nativeQuery = true)
    List<Student> findByFirstNameNative(@Param("firstName") String firstName);
    @Transactional
    @Modifying
    @Query("DELETE FROM Student u WHERE u.email = ?1")
    int deleteByEmail(String email);
}
