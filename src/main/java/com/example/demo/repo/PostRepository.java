package com.example.demo.repo;

import com.example.demo.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("SELECT m FROM Post m WHERE m.namepost LIKE ?1%")
    Post findByNamepost(String namepost);

}
