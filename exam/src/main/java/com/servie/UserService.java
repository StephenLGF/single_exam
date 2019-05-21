package com.servie;

import com.entity.User;
import com.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Configuration
public class UserService {

    @Resource
    private UserRepository userRepository;

    public User getUserNameByUserId(Long id) {
        return userRepository.findById(id);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User getUserNameByTelNum(Long telNum) {
        return userRepository.findByTelNum(telNum);
    }

    public List<User> getAllUser(){
        return  userRepository.findAll();
    }
}