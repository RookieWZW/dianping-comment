package org.imooc.service;

import org.imooc.dto.AdDto;

import java.util.List;

/**
 * Created by RookieWangZhiWei on 2018/7/25.
 */
public interface AdService {

    boolean add(AdDto adDto);


    List<AdDto> searchByPage(AdDto adDto);


    boolean remove(Long id);


    AdDto getById(Long id);


    boolean modify(AdDto adDto);
}
