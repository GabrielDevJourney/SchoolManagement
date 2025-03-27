package com.gabriel.SchoolManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gabriel.SchoolManagement.entity.Degree;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {
	List<Degree> findByDegreeName(String degreeName);
	Optional<Degree> findDegreeByDegreeNameAndEnrollmentYear(String degreeName, Year enrollmentYear);
}
