package com.gabriel.SchoolManagement.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "degrees")
public class Degree {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "degree_name", nullable = false)
	private String degreeName;

	@Column(name = "enrollment_year", nullable = false)
	private Year enrollmentYear;
}
