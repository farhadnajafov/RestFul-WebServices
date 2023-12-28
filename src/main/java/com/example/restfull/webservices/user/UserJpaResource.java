package com.example.restfull.webservices.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserJpaResource {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDaoService userService;
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){

        return userRepository.findAll();
    };

    @GetMapping("/jpa/users/{id}")
    public EntityModel<Optional<User>> retireveUser(@PathVariable int id){

       Optional<User>  user =  userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("id-"+id);

        }

        EntityModel<Optional<User>> model = EntityModel.of(user);

        WebMvcLinkBuilder linkToUser = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkToUser.withRel("all-users"));

        return model;
    }
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){

     userRepository.deleteById(id);


    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid  @RequestBody User user){
        User savedUser = userService.saveUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable int id){

       Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("id-"+id);
        }
        return user.get().getPost();
    };
}
