package com.gabriel.SchoolManagement.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {

	@Id
	@Column(name = "id")
	private Long id;

	// when creating from user it will use the same id to create student as well
	@OneToOne
	@MapsId
	@JoinColumn(name = "id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "degree_id", nullable = false)
	private Degree degree;
}
