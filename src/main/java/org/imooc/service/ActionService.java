package org.imooc.service;

import org.imooc.dto.ActionDto;

/**
 * Created by RookieWangZhiWei on 2018/7/26.
 */
public interface ActionService {

    boolean add(ActionDto actionDto);

    boolean remove(Long id);

    boolean modify(ActionDto actionDto);

    ActionDto getById(Long id);

}
