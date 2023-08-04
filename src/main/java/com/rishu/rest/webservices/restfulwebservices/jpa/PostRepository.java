package com.rishu.rest.webservices.restfulwebservices.jpa;

import com.rishu.rest.webservices.restfulwebservices.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    
}
