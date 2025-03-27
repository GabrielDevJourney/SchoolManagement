package com.gabriel.SchoolManagement.repository;

import com.gabriel.SchoolManagement.entity.Degree;
import com.gabriel.SchoolManagement.entity.DegreeSubject;
import com.gabriel.SchoolManagement.entity.Subject;
import com.gabriel.SchoolManagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for the DegreeSubject junction table.
 * Since this manages a many-to-many relationship between Degree and Subject,
 * and also the relation between Teacher and DegreeSubject,
 * custom JPQL queries are needed to retrieve related entities directly instead
 * of just returning the junction entities.
 */

public interface DegreeSubjectRepository extends JpaRepository<DegreeSubject,Long> {
	 boolean existsByDegreeIdAndSubjectId(Long degreeId, Long subjectId);
	Optional<DegreeSubject> findByDegreeIdAndSubjectId(Long degreeId, Long subjectId);

	@Query("SELECT s FROM Subject s JOIN DegreeSubject ds ON s.id = ds.subject.id JOIN Degree d ON d.id = ds.degree" +
			".id WHERE d.id = :degreeId")
	List<Subject> findSubjectsByDegreeId(@Param("degreeId") Long degreeId);

	@Query("SELECT s FROM Subject s JOIN DegreeSubject ds ON s.id = ds.subject.id JOIN Degree d ON d.id = ds.degree" +
			".id WHERE d.degreeName = :degreeName")
	List<Subject> findSubjectsByDegreeName(@Param("degreeName")String degreeName);

	@Query("SELECT d FROM Degree d JOIN DegreeSubject ds ON d.id = ds.degree.id JOIN Subject s ON s.id = ds.subject" +
			".id WHERE s.id = :subjectId")
	List<Degree> findDegreesBySubjectId(@Param("subjectId")Long subjectId);

	@Query("SELECT d FROM Degree d JOIN DegreeSubject ds ON d.id = ds.degree.id JOIN Subject s ON s.id = ds.subject.id WHERE s.subjectName = :subjectName")
	List<Degree> findDegreesBySubjectName(@Param("subjectName") String subjectName);

	@Query("SELECT ds.teacher FROM DegreeSubject ds WHERE ds.degree.id = :degreeId AND ds.subject.id = :subjectId")
	Optional<Teacher> findTeacherByDegreeIdAndSubjectId(@Param("degreeId") Long degreeId, @Param("subjectId") Long subjectId);

	@Query("SELECT DISTINCT ds.teacher FROM DegreeSubject ds WHERE ds.degree.id = :degreeId AND ds.teacher IS NOT NULL")
	List<Teacher> findTeachersByDegreeId(@Param("degreeId") Long degreeId);

	@Query("SELECT d FROM Degree d JOIN DegreeSubject ds ON d.id = ds.degree.id WHERE ds.teacher.id = :teacherId")
	List<Degree> findDegreesByTeacherId(@Param("teacherId") Long teacherId);

	boolean existsByDegreeIdAndSubjectIdAndTeacherId(Long degreeId, Long subjectId, Long teacherId);

	@Query("SELECT COUNT(DISTINCT ds.degree.id) FROM DegreeSubject ds WHERE ds.teacher.id = :teacherId")
	Long countDegreesByTeacherId(@Param("teacherId") Long teacherId);
}
