package com.gabriel.SchoolManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gabriel.SchoolManagement.entity.Subject;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
	Optional<Subject> findBySubjectName(String subjectName);
	List<Subject> findBySubjectNameContaining(String keyword);
}
