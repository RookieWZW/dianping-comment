package org.imooc.service.impl;

import org.imooc.bean.Member;
import org.imooc.cache.CodeCache;
import org.imooc.cache.TokenCache;
import org.imooc.dao.MemberDao;
import org.imooc.service.MemberService;
import org.imooc.util.MD5Util;
import org.omg.IOP.Codec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by RookieWangZhiWei on 2018/7/27.
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;


    public final static Logger logger = LoggerFactory.getLogger(MemberService.class);


    public boolean exists(Long phone) {
        Member member = new Member();

        member.setPhone(phone);

        List<Member> list = memberDao.select(member);

        return list != null && list.size() == 1;
    }


    public boolean saveCode(Long phone, String code) {
        CodeCache codeCache = CodeCache.getInstance();
        return codeCache.save(phone, MD5Util.getMD5(code));

    }

    public boolean sendCode(Long phone, String content) {
        logger.info(phone + "|" + content);
        return true;
    }

    public String getCode(Long phone) {

        CodeCache codeCache = CodeCache.getInstance();

        return codeCache.getCode(phone);
    }

    public void saveToken(String token, Long phone) {
        TokenCache tokenCache = TokenCache.getInstance();

        tokenCache.save(token, phone);

    }

    public Long getPhone(String token) {
        TokenCache tokenCache = TokenCache.getInstance();

        return tokenCache.getPhone(token);
    }

    public Long getIdByPhone(Long phone) {
        Member member = new Member();

        member.setPhone(phone);

        List<Member> list = memberDao.select(member);

        if (list != null && list.size() == 1) {
            return list.get(0).getId();


        }

        return null;
    }
}
