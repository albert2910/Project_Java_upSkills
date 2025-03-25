package org.example.repository.impl;

import org.example.entities.Image;
import org.example.repository.ImageRepository;

import java.util.*;

public class ImageRepositoryImpl implements ImageRepository {
    private final Map<UUID, Image> imagesStorage = new HashMap<>();

    @Override
    public Image save(Image image) {
        if (null == image.getId()) {
            Image newImage = new Image(
                    UUID.randomUUID(),
                    image.getPostId(),
                    image.getUrl()
            );
            imagesStorage.put(newImage.getId(), newImage);
            return newImage;
        }
        imagesStorage.put(image.getId(), image);
        return image;
    }

    @Override
    public Image findById(Long id) {
        return null;
    }

    @Override
    public List<Image> findAll() {
        return new ArrayList<>(imagesStorage.values());
    }
}
