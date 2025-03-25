package org.example.service.impl;

import org.example.cache.PostCache;
import org.example.entities.Image;
import org.example.entities.Post;
import org.example.repository.ImageRepository;
import org.example.repository.PostRepository;
import org.example.service.ImageService;
import org.example.service.PostService;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;
    private final PostCache postCache;

    public PostServiceImpl(PostRepository postRepository, ImageRepository imageRepository, ImageService imageService) {
        this.postRepository = postRepository;
        this.imageRepository = imageRepository;
        this.imageService = imageService;
        this.postCache = PostCache.getInstance();
    }

    @Override
    public Post createPost(String content, List<Path> imagePaths) throws IOException {
        // Save post first
        Post post = new Post.Builder()
                .content(content)
                .build();

        Post savedPost = postRepository.save(post);

        // Upload and save images
        List<Image> images = new ArrayList<>();
        for (Path path : imagePaths) {
            String imageUrl = imageService.uploadImage(path);
            Image image = new Image(null, savedPost.getId(), imageUrl);
            Image savedImage = imageRepository.save(image);
            images.add(savedImage);
        }

        // Update post with images
        Post finalPost = new Post.Builder()
                .id(savedPost.getId())
                .content(savedPost.getContent())
                .addAllImages(images)
                .build();

        Post savedPost1 = postRepository.save(finalPost);
        postCache.put(savedPost1); // save to cache
        return savedPost1;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
