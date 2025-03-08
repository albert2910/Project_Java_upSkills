package org.example.entities;

import org.example.entities.base.BaseEntity;

import java.util.UUID;

public class Images extends BaseEntity {
    private UUID id;
    private UUID postId;
    private String url;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
