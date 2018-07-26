package org.imooc.service;

import org.imooc.bean.Dic;

import java.util.List;

/**
 * Created by RookieWangZhiWei on 2018/7/26.
 */
public interface DicService {

    public List<Dic> getListByType(String type);
}
