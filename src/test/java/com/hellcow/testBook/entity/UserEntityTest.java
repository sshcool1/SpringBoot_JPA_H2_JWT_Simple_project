package com.hellcow.testBook.entity;

import com.hellcow.testBook.repo.UserRepository;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityTest extends TestCase {

	@Autowired
	UserRepository userRepository;

	@After
	public void clean() {
		userRepository.deleteAll();
	}

	@Test
	public void 사용자_생성() {
		UserEntity user = userRepository.save(UserEntity.builder().name("성세현").password("1234").build());
		assertEquals("성세현", user.getName());
		assertEquals("1234", user.getPassword());
	}

	@Test
	public void 사용자_수정() {
		UserEntity user = userRepository.save(UserEntity.builder().name("성세현").password("1234").build());
		assertEquals("성세현", user.getName());
		assertEquals("1234", user.getPassword());

		user.setName("성세현2");
		user.setPassword("123456");

		UserEntity updateUser = userRepository.save(user);
		assertEquals("성세현2", updateUser.getName());
		assertEquals("123456", updateUser.getPassword());
	}
}
