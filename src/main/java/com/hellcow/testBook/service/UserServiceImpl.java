package com.hellcow.testBook.service;

import com.hellcow.testBook.api.exception.ApiException;
import com.hellcow.testBook.api.model.ApiData;
import com.hellcow.testBook.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyList;

@Service
public class UserServiceImpl implements UserDetailsService {

    private UserService userService;
    public UserServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Map data = new HashMap();
        data.put("userId", userId);
        UserEntity user = null;
        try {
            user = userService.findByUserId(new ApiData(data));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        if (user == null) {
            throw new UsernameNotFoundException(userId);
        }
        return new User(user.getName(), user.getPassword(), emptyList());
    }
}
