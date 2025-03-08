package org.example.entities;

import org.example.entities.base.BaseEntity;
import org.example.entities.enums.StatusPost;

import java.util.UUID;

public class Post extends BaseEntity {
    private UUID id;
    private String content;
    private StatusPost status;
    private UUID userId;

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
}
