package org.imooc.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RookieWangZhiWei on 2018/7/27.
 */
public class TokenCache {


    private static TokenCache instance;

    private Map<String, Long> tokenMap;

    private TokenCache() {
        tokenMap = new HashMap<String, Long>();
    }


    public static TokenCache getInstance() {
        if (instance == null) {
            synchronized (TokenCache.class) {
                if (instance == null) {
                    instance = new TokenCache();
                }
            }
        }
        return instance;
    }

    public void save(String token, Long phone) {
        tokenMap.put(token, phone);
    }

    public Long getPhone(String token) {
        return tokenMap.get(token);
    }

}
