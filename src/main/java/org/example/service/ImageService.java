package org.example.service;

import java.io.IOException;
import java.nio.file.Path;

public interface ImageService {
    String uploadImage(Path filePath) throws IOException;
}
