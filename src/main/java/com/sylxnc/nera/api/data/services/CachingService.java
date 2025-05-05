package com.sylxnc.nera.api.data.services;

import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class CachingService {

    private StorageService storage;
    private Jedis jedis;
    private HashMap<String,Object> localCache = new HashMap<>();


    public CachingService() {
        storage = new StorageService();
        jedis = storage.getJedis();
    }

    public void cache(String key, Object value) {
        localCache.put(key, value);
        if(value instanceof String || value instanceof Integer) {
            jedis.set(key, value.toString());
        }else {
            throw new IllegalArgumentException("Value must be a string or an integer to Cache in DB \nCached Local instead ");
        }

    }

    public Object getCache(String key) {
        if(jedis.exists(key)) {
            return jedis.get(key);
        }else if(localCache.containsKey(key)) {
            return localCache.get(key);
        }else throw new NullPointerException("Value is not Cached");
    }

    public void delCache(String key) {
        if(jedis.exists(key)) {
            jedis.del(key);
            if(localCache.containsKey(key)) {
                localCache.remove(key);
            }
        }else if(localCache.containsKey(key)) {
            localCache.remove(key);
        }
        else {
            throw new NullPointerException("Value is not Cached");
        }
    }


}
