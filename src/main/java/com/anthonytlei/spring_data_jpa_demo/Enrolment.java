package com.anthonytlei.spring_data_jpa_demo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity(name = "Enrolment")
@Table(name = "enrolment")
public class Enrolment {
    @EmbeddedId
    private EnrolmentId id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "enrolment_student_id_fk"))
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "enrolment_course_id_fk"))
    private Course course;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;

    public Enrolment() {
    }

    public Enrolment(Student student, Course course, LocalDateTime createdAt) {
        this.student = student;
        this.course = course;
        this.createdAt = createdAt;
    }

    public Enrolment(EnrolmentId id, Student student, Course course, LocalDateTime createdAt) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.createdAt = createdAt;
    }

    public EnrolmentId getId() {
        return id;
    }

    public void setId(EnrolmentId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Enrolment [id=" + id + ", student=" + student + ", course=" + course + ", createdAt=" + createdAt + "]";
    }

}
