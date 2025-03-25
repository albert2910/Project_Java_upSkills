package org.example;

import org.example.cache.PostCache;
import org.example.controller.PostController;
import org.example.datasource.FilePostDatasource;
import org.example.entities.Image;
import org.example.entities.Post;
import org.example.repository.PostRepository;
import org.example.repository.impl.ImageRepositoryImpl;
import org.example.repository.impl.PostRepositoryImpl;
import org.example.server.SimplePostServer;
import org.example.service.PostService;
import org.example.service.impl.FileSystemImageStorageService;
import org.example.service.impl.PostServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        PostService postService = new PostServiceImpl(new PostRepositoryImpl(), new ImageRepositoryImpl(), new FileSystemImageStorageService());

        // Start server
        SimplePostServer server = new SimplePostServer(postService, 8080);
        new Thread(server::start).start();

        // test
        String basePath = "/Users/taphuonglinh/Desktop/Fsoft Academy/Project_Java_upSkills/uploads";
        String content = "Test post from main on " + java.time.LocalDate.now();
        List<Path> imagePaths = Arrays.asList(
                Paths.get(basePath + "/test1.jpg"),
                Paths.get(basePath + "/test2.jpg")
        );

        // fake client
        String host = "localhost";
        int port = 8080;
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Send request
            String request = "CREATE_POST;Post from client";
            out.println(request);
            System.out.println("Sent: " + request);

            // Receive response
            String response = in.readLine();
            System.out.println("Received: " + response);

        } catch (Exception e) {
            System.err.println("Client error: " + e.getMessage());
        }

        try {
            Post post = postService.createPost(content, imagePaths);

            // check storage
            System.out.println("Post ID: " + post.getId());
            System.out.println("Content: " + post.getContent());
            post.getImages().forEach(img -> System.out.println("- Image: " + img.getUrl()));

            // check cache
            Post cachedPost = PostCache.getInstance().get(post.getId());
            if (cachedPost != null) {
                System.out.println("Cache hit! Post ID: " + cachedPost.getId());
                System.out.println("Content from cache: " + cachedPost.getContent());
                System.out.println("Images from cache: " + cachedPost.getImages().size());
            } else {
                System.out.println("Cache miss!");
            }

        } catch (IOException e) {
            throw new RuntimeException("upload failed", e);
        }

        // Test FilePostDatasource
//        FilePostDatasource datasource = new FilePostDatasource();
//        testFilePostDatasource(datasource);

    }

//    private static void testFilePostDatasource(FilePostDatasource datasource) {
//        try {
//            // Xóa file cũ để test từ đầu (tuỳ chọn)
//            Path dataFile = Paths.get("data/posts.dat");
//            if (Files.exists(dataFile)) {
//                Files.delete(dataFile);
//                System.out.println("Deleted existing posts.dat for clean test");
//            }
//
//            // Test 1: Lưu bài post và kiểm tra file
//            System.out.println("\nTest 1: Saving a post...");
//            Post testPost = new Post.Builder()
//                    .id(UUID.randomUUID())
//                    .content("Test post for FilePostDatasource")
//                    .addAllImages(Arrays.asList(
//                            new Image(UUID.randomUUID(), UUID.randomUUID(), "/Users/taphuonglinh/Desktop/Fsoft Academy/Project_Java_upSkills/uploads/test1.jpg"),
//                            new Image(UUID.randomUUID(), UUID.randomUUID(), "/Users/taphuonglinh/Desktop/Fsoft Academy/Project_Java_upSkills/uploads/test2.jpg")
//                    ))
//                    .build();
//
//            long fileSizeBefore = Files.exists(dataFile) ? Files.size(dataFile) : 0;
//            datasource.savePost(testPost);
//            long fileSizeAfter = Files.size(dataFile);
//
//            System.out.println("File size before saving: " + fileSizeBefore + " bytes");
//            System.out.println("File size after saving: " + fileSizeAfter + " bytes");
//            System.out.println("Save successful: " + (fileSizeAfter > fileSizeBefore));
//
//            // Test 2: Lấy danh sách bài post từ file
//            System.out.println("\nTest 2: Loading posts from file...");
//            List<Post> loadedPosts = datasource.loadPosts();
//
//            if (loadedPosts.isEmpty()) {
//                System.out.println("No posts loaded.");
//            } else {
//                System.out.println("Loaded " + loadedPosts.size() + " posts:");
//                for (Post post : loadedPosts) {
//                    System.out.println("Post ID: " + post.getId());
//                    System.out.println("Content: " + post.getContent());
//                    System.out.println("Number of images: " + post.getImages().size());
//                    System.out.println("Images:");
//                    post.getImages().forEach(img -> System.out.println("- " + img.getUrl()));
//                    System.out.println("---");
//                }
//            }
//
//            // Test 3: Lưu thêm bài post và kiểm tra lại
//            System.out.println("\nTest 3: Saving another post...");
//            Post anotherPost = new Post.Builder()
//                    .id(UUID.randomUUID())
//                    .content("Another test post")
//                    .addAllImages(Arrays.asList(new Image(UUID.randomUUID(), UUID.randomUUID(), "/Users/taphuonglinh/Desktop/Fsoft Academy/Project_Java_upSkills/uploads/test3.jpg")))
//                    .build();
//            datasource.savePost(anotherPost);
//
//            System.out.println("Loading posts again...");
//            loadedPosts = datasource.loadPosts();
//            System.out.println("Total posts after adding another: " + loadedPosts.size());
//
//        } catch (Exception e) {
//            throw new RuntimeException("FilePostDatasource test failed", e);
//        }
//    }
}
