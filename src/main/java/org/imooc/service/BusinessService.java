package org.imooc.service;

import org.imooc.dto.BusinessDto;
import org.imooc.dto.BusinessListDto;

import java.util.List;

/**
 * Created by RookieWangZhiWei on 2018/7/26.
 */
public interface BusinessService {


    boolean add(BusinessDto businessDto);

    BusinessDto getById(Long id);

    List<BusinessDto> searchByPage(BusinessDto businessDto);

    BusinessListDto searchByPageForApi(BusinessDto businessDto);
}
