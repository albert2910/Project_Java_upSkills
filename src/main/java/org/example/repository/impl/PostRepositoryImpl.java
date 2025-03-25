package org.example.repository.impl;

import org.example.advice.ExceptionHandler;
import org.example.entities.Post;
import org.example.repository.PostRepository;

import java.util.*;

public class PostRepositoryImpl implements PostRepository {
    private final Map<UUID, Post> postStorage = new HashMap<>();

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(postStorage.values());
    }

    @Override
    public Post findById(UUID postId) {
        checkPostExists(postId);
        return postStorage.get(postId);
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == null) {
            Post newPost = new Post.Builder()
                    .id(UUID.randomUUID())
                    .content(post.getContent())
                    .build();
            postStorage.put(newPost.getId(), newPost);
            return newPost;
        }
        postStorage.put(post.getId(), post);
        return post;
    }

    @Override
    public void deletePost(UUID postId) {
        checkPostExists(postId);
        postStorage.remove(postId);
    }

    @Override
    public Post update(Post post) {
        checkPostExists(post.getId());
        return postStorage.put(post.getId(), post);
    }

    private Post checkPostExists(UUID postId) {
        if (!postStorage.containsKey(postId)) {
            throw new ExceptionHandler.PostNotFoundException("Post has id: " + postId + " not found");
        }
        return postStorage.get(postId);
    }

}
