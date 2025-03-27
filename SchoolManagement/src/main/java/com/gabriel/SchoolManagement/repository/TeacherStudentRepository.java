package com.gabriel.SchoolManagement.repository;

import com.gabriel.SchoolManagement.entity.Student;
import com.gabriel.SchoolManagement.entity.Teacher;
import com.gabriel.SchoolManagement.entity.TeacherStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherStudentRepository extends JpaRepository<TeacherStudent,Long> {
	//find all students of one teacher
	@Query("SELECT ts.student FROM TeacherStudent ts WHERE ts.teacher.id = :teacherId")
	List<Student> findStudentsByTeacherId(@Param("teacherId") Long teacherId);

}
