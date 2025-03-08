package org.example.entities;

import org.example.entities.base.BaseEntity;
import org.example.entities.enums.StatusFriendship;

import java.util.UUID;

public class Friendship extends BaseEntity {
    private UUID id;
    private UUID userId;
    private UUID userFriendId;
    private StatusFriendship status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserFriendId() {
        return userFriendId;
    }

    public void setUserFriendId(UUID userFriendId) {
        this.userFriendId = userFriendId;
    }

    public StatusFriendship getStatus() {
        return status;
    }

    public void setStatus(StatusFriendship status) {
        this.status = status;
    }
}
