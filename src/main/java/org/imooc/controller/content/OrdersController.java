package org.imooc.controller.content;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by RookieWangZhiWei on 2018/7/27.
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @RequestMapping()
    public String init() {
        return "/content/orderList";
    }
}
