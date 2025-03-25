package org.example.entities;

import org.example.entities.base.BaseEntity;
import org.example.entities.enums.StatusPost;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Post extends BaseEntity {
    private UUID id;
    private String content;
    private StatusPost status;
    private UUID userId;
    private List<Image> images;

    public Post(UUID id, String content, StatusPost status, UUID userId, List<Image> images) {
        this.id = id;
        this.content = content;
        this.status = status;
        this.userId = userId;
        this.images = images;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public StatusPost getStatus() {
        return status;
    }

    public void setStatus(StatusPost status) {
        this.status = status;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    // Builder Pattern
    public static class Builder {
        private UUID id;
        private String content;
        private StatusPost status;
        private UUID userId;
        private List<Image> images = new ArrayList<>();

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder addAllImages(List<Image> images) {
            this.images.addAll(images);
            return this;
        }

        public Post build() {
            return new Post(id, content, status, userId, images);
        }

    }
}
