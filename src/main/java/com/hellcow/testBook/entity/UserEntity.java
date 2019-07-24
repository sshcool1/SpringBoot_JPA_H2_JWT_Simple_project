package com.hellcow.testBook.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
public class UserEntity extends BaseTimeEntity {

	@Id
	@Column(length = 20, nullable = false)
	private String userId;

	@Column(length = 20, nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	List<HistoryEntity> historyList;

	@Builder
	public UserEntity(String userId, String name, String password){
		this.userId = userId;
		this.name = name;
		this.password = password;
	}
}
