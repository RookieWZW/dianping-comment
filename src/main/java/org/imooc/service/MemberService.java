package org.imooc.service;

/**
 * Created by RookieWangZhiWei on 2018/7/27.
 */
public interface MemberService {


    boolean exists(Long phone);

    boolean saveCode(Long phone,String code);


    boolean sendCode(Long phone,String content);

    String getCode(Long phone);

    void saveToken(String token,Long phone);


    Long getPhone(String token);


    Long getIdByPhone(Long phone);

}
