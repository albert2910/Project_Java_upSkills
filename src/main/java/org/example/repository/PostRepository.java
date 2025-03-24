package org.example.repository;

import org.example.entities.Post;

import java.util.List;
import java.util.UUID;

public interface PostRepository {
    List<Post> findAll();
    Post findById(UUID postId);
    Post save(Post post);
    void deletePost(UUID postId);
    Post update(Post post);
}
