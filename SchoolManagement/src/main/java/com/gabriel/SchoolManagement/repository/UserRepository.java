package com.gabriel.SchoolManagement.repository;

import com.gabriel.SchoolManagement.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gabriel.SchoolManagement.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findUserByFirstNameAndLastName(String firstName, String LastName);
	Optional<User> findUserByEmail(String email);
	//todo might need to be a list
	List<User> findUserByBirthDate(LocalDate birthDate);
	List<User> findUsersByRoleType(RoleType roleType);
}
