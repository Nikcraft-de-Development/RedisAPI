package de.einnik.redisapi.db;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;

@SuppressWarnings("unused")
public final class RedisCore {

    private JedisPool pool;

    public RedisCore(boolean local){
        if (local) connect("127.0.0.1", 63790, "");
    }

    public void connect(String host, int port, String password){
        pool = new JedisPool(host, port);
        try (Jedis jedis = getConnection()){
            jedis.auth(password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Jedis getConnection(){
        return pool.getResource();
    }

    public void close(){
        pool.close();
    }

    public void insert(String identifier, Object value){
        try (Jedis jedis = getConnection()){
            jedis.set(identifier, String.valueOf(value));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object get(String identifier){
        Object value;

        try (Jedis jedis = getConnection()){
            value = jedis.get(identifier);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return value;
    }

    public void delete(String identifier){
        try (Jedis jedis = getConnection()){
            jedis.del(identifier);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void add(String identifier, int value){
        try (Jedis jedis = getConnection()){
            jedis.incrBy(identifier, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(String identifier, int value){
        try (Jedis jedis = getConnection()){

            int amount = Integer.parseInt(jedis.get(identifier));
            int newAmount = Math.max(amount - value, 0);
            jedis.set(identifier, String.valueOf(newAmount));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean exists(String identifier){
        try (Jedis jedis = getConnection()){
            return jedis.exists(identifier);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTable(String table){
        try (Jedis jedis = getConnection()){
            jedis.del(table);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsTable(String table, String identifier){
        try (Jedis jedis = getConnection()){
            return jedis.hexists(table, identifier);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTable(String table, String identifier, HashMap<String, Object> map){
        try (Jedis jedis = getConnection()){
            jedis.hset(table, identifier, String.valueOf(map));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<String, Object> getTableHashMap(String table, String identifier){
        HashMap<String, Object> values = new HashMap<>();
        Object object;

        try (Jedis jedis = getConnection()){
            object = jedis.hget(table, identifier);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String value = String.valueOf(object);

        value = value.substring(1, value.length() - 1);
        String[] pairs = value.split(", ");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String key = keyValue[0].trim();
            String worth = keyValue[1].trim();

            values.put(key, worth);
        }

        return values;
    }

    public void updateTable(String table, String identifier, Object value){
        try (Jedis jedis = getConnection()){
            jedis.hset(table, identifier, String.valueOf(value));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object getFromTable(String table, String identifier){
        Object value;

        try (Jedis jedis = getConnection()){
            value = jedis.hget(table, identifier);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return value;
    }
}