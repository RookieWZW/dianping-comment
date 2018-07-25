package org.imooc.util;

import org.imooc.constant.SessionKeyConst;
import org.imooc.dto.ActionDto;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * Created by RookieWangZhiWei on 2018/7/25.
 */
public class CommonUtil {

    public static boolean isEmpty(String str){
        if (str == null || "".equals(str.trim())){
            return true;
        }
        return false;
    }



    public static int random(int number){
        return (int) ((Math.random()*9 + 1 )*Math.pow(10,number-1));
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }


    public static boolean contains(HttpSession session,String url,String method) {
        Object obj = session.getAttribute(SessionKeyConst.ACTION_INFO);
        if(obj != null) {
            @SuppressWarnings("unchecked")
            List<ActionDto> dtoList = (List<ActionDto>)obj;
            for(ActionDto actionDto : dtoList) {
                if(!isEmpty(actionDto.getMethod()) && !actionDto.getMethod().equals(method)) {
                    continue;
                }
                if(!url.matches(actionDto.getUrl())) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }




}
