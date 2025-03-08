package org.example.entities;

import org.example.entities.base.BaseEntity;

import java.util.UUID;

public class PostLikes extends BaseEntity {
    private UUID id;
    private UUID postId;
    private UUID userId;

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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
