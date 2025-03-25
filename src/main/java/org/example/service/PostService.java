package org.example.service;

import org.example.entities.Post;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface PostService {
    Post createPost(String content, List<Path> imagePaths) throws IOException;
    List<Post> getAllPosts();
}
