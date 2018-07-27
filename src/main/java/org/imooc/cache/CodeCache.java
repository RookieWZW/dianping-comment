package org.imooc.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RookieWangZhiWei on 2018/7/27.
 */
public class CodeCache {

    private static CodeCache instance;

    private Map<Long, String> codeMap;

    private CodeCache() {
        codeMap = new HashMap<Long, String>();

    }


    public static CodeCache getInstance() {
        if (instance == null) {
            synchronized (CodeCache.class) {
                if (instance == null) {
                    instance = new CodeCache();
                }
            }
        }
        return instance;
    }


    public boolean save(Long phone, String code) {
        if (codeMap.containsKey(phone)) {
            return false;
        }
        codeMap.put(phone, code);
        return true;
    }

    public String getCode(Long phone) {
        return codeMap.get(phone);
    }
}
