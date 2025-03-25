// server/SimplePostServer.java
package org.example.server;

import org.example.entities.Post;
import org.example.service.PostService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.Arrays;

public class SimplePostServer {
    private final PostService postService;
    private final int port;

    public SimplePostServer(PostService postService, int port) {
        this.postService = postService;
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(clientSocket.getInputStream()))) {
                    String request = in.readLine();
                    if (request != null && request.startsWith("CREATE_POST")) {
                        String[] parts = request.split(";");
                        String content = parts[1];
                        Post post = postService.createPost(content, Arrays.asList(
                                Paths.get("uploads/test1.jpg"),
                                Paths.get("uploads/test2.jpg")
                        ));
                        out.println("Post created with ID: " + post.getId());
                    }
                } catch (IOException e) {
                    System.err.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to start server", e);
        }
    }
}