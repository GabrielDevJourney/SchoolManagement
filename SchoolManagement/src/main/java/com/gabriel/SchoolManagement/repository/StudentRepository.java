package com.gabriel.SchoolManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gabriel.SchoolManagement.entity.Student;

import java.util.List;
/**
 * Repository for Student entity.
 *
 * Since Student is a joining entity between User and Degree,
 * where personal data is stored in the User entity while Student only maintains
 * the degree enrollment, queries for user properties must use the User_ prefix
 * (e.g., User_firstName instead of firstName).
 *
 * This design supports the single-table inheritance pattern where each Student
 * shares its ID with a User record through @MapsId annotation in Student entity class.
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findByUser_FirstName(String firstName);
	List<Student> findByUser_LastName(String lastName);
	List<Student> findByUser_FirstNameAndLastName(String firstName, String lastName);
	List<Student> findByDegreeId(Long degreeId);
}
