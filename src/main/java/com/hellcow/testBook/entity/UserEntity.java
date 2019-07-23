package com.hellcow.testBook.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
public class UserEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 20, nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	@Builder
	public UserEntity(String name, String password){
		this.name = name;
		this.password = password;
	}
}
