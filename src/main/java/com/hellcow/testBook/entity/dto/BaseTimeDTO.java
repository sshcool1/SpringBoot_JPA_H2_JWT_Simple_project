package com.hellcow.testBook.entity.dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public abstract class BaseTimeDTO {
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
}
