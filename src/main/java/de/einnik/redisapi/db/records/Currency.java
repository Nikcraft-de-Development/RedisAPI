package de.einnik.redisapi.db.records;

import de.einnik.redisapi.RedisAPI;
import de.einnik.redisapi.db.RedisCore;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;

public record Currency(String prefix) {

    private final static RedisCore redis = RedisAPI.getRedisCore();

    private String prefix(UUID uuid){
        return prefix + ":" + uuid.toString();
    }

    public void set(UUID uuid, int amount){
        redis.insert(prefix(uuid), amount);
    }

    public int get(UUID uuid){
        Object object = redis.get(prefix(uuid));

        return Integer.parseInt(String.valueOf(object));
    }

    public void add(UUID uuid, int amount){
        redis.add(prefix(uuid), amount);
    }

    public void remove(UUID uuid, int amount){
        redis.remove(prefix(uuid), amount);
    }

    public void delete(UUID uuid){
        redis.delete(prefix(uuid));
    }

    public boolean exists(UUID uuid){
        return redis.exists(prefix(uuid));
    }

    public String format(UUID uuid){
        if (!exists(uuid)){
            return "0";
        }

        int value = get(uuid);
        NumberFormat format = NumberFormat.getNumberInstance(Locale.GERMANY);
        return format.format(value);
    }
}