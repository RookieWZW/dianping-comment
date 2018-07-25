package org.imooc.dao;

import org.imooc.bean.Ad;

import java.util.List;

/**
 * Created by RookieWangZhiWei on 2018/7/25.
 */
public interface AdDao {

    int insert(Ad ad);

    List<Ad> selectByPage(Ad ad);

    Ad selectById(Long id);

    int update(Ad ad);

    int delete(Long id);
}
