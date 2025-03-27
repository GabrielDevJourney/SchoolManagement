package com.gabriel.SchoolManagement.repository;

import com.gabriel.SchoolManagement.entity.Student;
import com.gabriel.SchoolManagement.entity.StudentSubject;
import com.gabriel.SchoolManagement.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Long> {

	@Query ("SELECT ss.mark FROM StudentSubject ss WHERE ss.student.id = :studentId AND ss.subject.id = :subjectId")
	Double findMarkByStudentIdAndSubjectId(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId);

	@Query("SELECT s FROM Student s JOIN StudentSubject ss ON s.id = ss.student.id WHERE ss.subject.name = :subjectName AND ss.mark >= :minMark")
	List<Student> findStudentsBySubjectNameAndMarkGreaterThanEqual(String subjectName, Double minMark);

	@Query("SELECT s FROM Student s JOIN StudentSubject ss ON s.id = ss.student.id WHERE ss.subject.name = " +
			":subjectName")
	List<Student> findStudentsBySubjectName(@Param("subjectName") String subjectName);

	@Query("SELECT ss.comments FROM StudentSubject ss WHERE ss.student.id = :studentId AND ss.subject.id = :subjectId")
	String findCommentsByStudentIdAndSubjectId(@Param("studentId") Long studentId, @Param("subjectId" )Long subjectId);

	@Query("SELECT s FROM Subject s JOIN StudentSubject ss ON s.id = ss.subject.id WHERE ss.student.id = :studentId")
	List<Subject> findSubjectsByStudentId(@Param("studentId")Long studentId);

	@Query("SELECT ss.dateRegistered FROM StudentSubject ss WHERE ss.student.id = :studentId AND ss.subject.id = " +
			":subjectId")
	LocalDate findDateRegisteredByStudentIdAndSubjectId(@Param("studentId") Long studentId, @Param("subjectId" )Long subjectId);

	@Query("SELECT s FROM Student s JOIN StudentSubject ss ON s.id = ss.student.id WHERE ss.mark IS NULL")
	List<Student> findStudentsByMarkIsNull();

	@Query("SELECT s FROM Student s JOIN StudentSubject ss ON s.id = ss.student.id WHERE ss.mark >= :minMark")
	List<Student> findStudentsByMarkGreaterThanEqual(@Param("minMark") Double minMark);

	@Query("SELECT s FROM Student s JOIN StudentSubject ss ON s.id = ss.student.id WHERE ss.dateRegistered = :date")
	List<Student> findStudentsByDateRegistered(@Param("date") LocalDate date);

	@Query("SELECT s FROM Student s JOIN StudentSubject ss ON s.id = ss.student.id WHERE ss.subject.id = :subjectId AND ss.comments IS NULL")
	List<Student> findStudentsBySubjectIdAndCommentsIsNull(@Param("subjectId") Long subjectId);

	@Query("SELECT COUNT(ss.student.id) FROM StudentSubject ss WHERE ss.subject.name = :subjectName")
	Long countStudentsBySubjectName(@Param("subjectName") String subjectName);

	@Query("SELECT COUNT(ss.student.id) FROM StudentSubject ss WHERE ss.subject.id = :subjectId")
	Long countStudentsBySubjectId(@Param("subjectId") Long subjectId);
}
