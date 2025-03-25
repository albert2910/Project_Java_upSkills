package org.example.service.impl;

import org.example.service.ImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

// use NIO to upload images
public class FileSystemImageStorageService implements ImageService {
    private static final String UPLOAD_DIR = "uploads/";

    @Override
    public String uploadImage(Path filePath) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = UUID.randomUUID().toString() + "_" + filePath.getFileName();
        Path targetPath = Paths.get(UPLOAD_DIR, fileName);

        Files.createDirectories(targetPath.getParent());
        Files.copy(filePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

        return "/images/" + fileName;
    }
}
