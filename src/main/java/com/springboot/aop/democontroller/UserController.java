package com.springboot.aop.democontroller;

import com.springboot.aop.demorepository.UserRepository;
import com.springboot.aop.demoservice.UserService;
import com.springboot.aop.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;


    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers()
    {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getAllUsers());
    }

    @GetMapping("users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") int id)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(this.userService.getUserById(id));
        }
        catch (Exception exception)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User id not found");
        }
    }


    @DeleteMapping("users/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable("id") int id)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        catch (Exception exception)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
}
