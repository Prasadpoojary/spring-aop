package com.springboot.aop.demoservice;

import com.springboot.aop.demorepository.UserRepository;
import com.springboot.aop.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers()
    {
        return this.userRepository.getAllUsers();
    }

    public User getUserById(int id)
    {
        return this.userRepository.getUserById(id);
    }

    public boolean deleteUserById(int id)
    {
        return this.userRepository.deleteUserById(id);
    }

}
