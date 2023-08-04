package com.rishu.rest.webservices.restfulwebservices.user;
import com.rishu.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.rishu.rest.webservices.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {

    private UserRepository userRepository;

    private PostRepository postRepository;

    public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // get/users

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {

       Optional<User> user =  userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("id:" + id);
        }

        EntityModel<User> userEntityModel =  EntityModel.of(user.get());

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        userEntityModel.add(link.withRel("all-users"));

        return  userEntityModel;
    }



    // POST/user

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser =  userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    // http://localhost:8080/jpa/users/10002/posts
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser (@PathVariable int id) {

        Optional<User> user =  userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("id:" + id);
        }

       return user.get().getPosts();
    }


    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostsForUser (@PathVariable int id, @Valid @RequestBody Post post) {

        Optional<User> user =  userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("id:" + id);
        }

        post.setUser(user.get());

       Post savedPosts = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(savedPosts.getId()).toUri();
        
        return ResponseEntity.created(location).build();
    }


}
