//package org.example.datasource;
//
//
//import org.example.entities.Post;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FilePostDatasource {
//    private static final Path DATA_FILE = Paths.get("data/posts.dat");
//
//    public FilePostDatasource() {
//        try {
//            Files.createDirectories(DATA_FILE.getParent()); // Tạo thư mục 'data' nếu chưa có
//            if (!Files.exists(DATA_FILE)) {
//                Files.createFile(DATA_FILE); // Tạo file 'posts.dat' nếu chưa tồn tại
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to initialize datasource", e);
//        }
//    }
//
//    public void savePost(Post post) {
//        try (ObjectOutputStream oos = new ObjectOutputStream(
//                new FileOutputStream(DATA_FILE.toFile(), true))) {
//            oos.writeObject(post);
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to save post to file", e);
//        }
//    }
//
//    public List<Post> loadPosts() {
//        List<Post> posts = new ArrayList<>();
//        if (Files.exists(DATA_FILE)) {
//            try (ObjectInputStream ois = new ObjectInputStream(
//                    new FileInputStream(DATA_FILE.toFile()))) {
//                while (true) {
//                    try {
//                        posts.add((Post) ois.readObject());
//                    } catch (EOFException e) {
//                        break; // Successfully read file
//                    }
//                }
//            } catch (IOException | ClassNotFoundException e) {
//                throw new RuntimeException("Failed to load posts from file", e);
//            }
//        }
//        return posts;
//    }
//}