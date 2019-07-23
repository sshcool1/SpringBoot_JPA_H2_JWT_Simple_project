package com.hellcow.testBook.entity;

import com.hellcow.testBook.repo.UserRepository;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTimeEntityTest extends TestCase {

	@Autowired
	UserRepository userRepository;

	@After
	public void cleanUp() {
		userRepository.deleteAll();
	}

	@Test
	public void 사용자_생성후_생성일_수정일_체크() {
		LocalDateTime now = LocalDateTime.now();
		userRepository.save(UserEntity.builder().name("성세현").password("1234").build());

		List<UserEntity> userList = userRepository.findAll();
		UserEntity user = userList.get(0);

		assertTrue(user.getCreatedDate().isAfter(now));
		assertTrue(user.getModifiedDate().isAfter(now));
	}

	@Test
	public void 사용자_변경시_수정일만변경되는지_체크() throws InterruptedException {
		UserEntity user = userRepository.save(UserEntity.builder().name("성세현").password("1234").build());
		LocalDateTime old_createdDate = user.getCreatedDate();
		LocalDateTime old_modifiedDate = user.getModifiedDate();

		Thread.sleep(1000);
		user.setName("성세현2");
		UserEntity updateUser = userRepository.save(user);
		LocalDateTime createdDate = updateUser.getCreatedDate();
		LocalDateTime modifiedDate = updateUser.getModifiedDate();

		assertTrue(old_createdDate.equals(createdDate));
		assertTrue(modifiedDate.isAfter(old_modifiedDate));
	}
}
