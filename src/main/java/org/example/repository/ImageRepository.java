package org.example.repository;

import org.example.entities.Image;

import java.util.List;

public interface ImageRepository {
    Image save(Image image);
    Image findById(Long id);
    List<Image> findAll();
}
