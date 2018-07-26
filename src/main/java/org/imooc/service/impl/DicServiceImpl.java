package org.imooc.service.impl;

import org.imooc.bean.Dic;
import org.imooc.dao.DicDao;
import org.imooc.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by RookieWangZhiWei on 2018/7/26.
 */
@Service
public class DicServiceImpl implements DicService{

    @Autowired
    private DicDao dicDao;


    public List<Dic> getListByType(String type) {
        Dic dic = new Dic();

        dic.setType(type);

        return dicDao.select(dic);
    }
}
