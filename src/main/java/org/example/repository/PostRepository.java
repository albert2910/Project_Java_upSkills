package org.example.repository;

import org.example.entities.Post;

import java.util.List;

public interface PostRepository {
    List<Post> findAll();
    Post findById(Long id);
    Post save(Post post);
    void delete(Post post);
    Post update(Post post);
}
