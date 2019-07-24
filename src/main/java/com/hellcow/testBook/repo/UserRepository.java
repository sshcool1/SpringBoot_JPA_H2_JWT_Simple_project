package com.hellcow.testBook.repo;

import com.hellcow.testBook.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
