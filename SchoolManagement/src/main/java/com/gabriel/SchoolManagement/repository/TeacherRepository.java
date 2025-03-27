package com.gabriel.SchoolManagement.repository;

import com.gabriel.SchoolManagement.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import com.gabriel.SchoolManagement.entity.Teacher;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	@Query("SELECT t.mainSubject.name FROM Teacher t WHERE t.id = :teacherId")
	String findSubjectNameByTeacherId(@Param("teacherId") Long teacherId);

	@Query("SELECT t FROM Teacher t WHERE t.subject.id = :subjectId")
	List<Teacher> findTeacherByMainSubjectId(@Param("subjectId") Long subjectId);
}
