package com.hellcow.testBook.entity.dto;

import lombok.Data;

@Data
public class UserDTO extends BaseTimeDTO {

	private String userId;
	private String name;
}
