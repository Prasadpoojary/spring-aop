package com.springboot.aop.demorepository;

import com.springboot.aop.dto.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository
{
    private final List<User> allUsers;

//    Demonstration purpose I have created dummy values here
    public  UserRepository()
    {
        this.allUsers=new ArrayList<>();
        this.allUsers.add(new User(1,"Prasad","prasad@pra.com","7788665544","123@321"));
        this.allUsers.add(new User(2,"Mahesh","Mahesh@pra.com","8899665533","345@543"));
        this.allUsers.add(new User(3,"Eshwar","Eshwar@pra.com","9975433221","567@765"));
    }


    public List<User> getAllUsers()
    {
        return this.allUsers;
    }

    public User getUserById(int id)
    {
        return this.allUsers.stream().filter(user->user.getUser_id()==id).findFirst().orElseThrow();
    }

    public boolean deleteUserById(int id)
    {
        return this.allUsers.removeIf(user -> user.getUser_id()==id);
    }

}
