package com.example.restfull.webservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {
    @Autowired
    private UserDaoService userService;
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userService.findAll();
    };

    @GetMapping("/users/{id}")
    public User retireveUser(@PathVariable int id){

        User user =  userService.findUser(id);
        if(user==null){
            throw new UserNotFoundException("id-"+id);

        }
        return user;
    }

    @PostMapping("users")
    public void createUser(@RequestBody User user){
       User saveUser = userService.saveUser(user);
    }
}
