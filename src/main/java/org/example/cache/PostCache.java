package org.example.cache;

import org.example.entities.Post;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PostCache {
    private static final PostCache INSTANCE = new PostCache();
    private final Map<UUID, Post> cache = new ConcurrentHashMap<>();

    public static PostCache getInstance() {
        return INSTANCE;
    }

    public void put(Post post) {
        cache.put(post.getId(), post);
    }

    public Post get(UUID id) {
        return cache.get(id);
    }

    public void clear() {
        cache.clear();
    }

}
