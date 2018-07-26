package org.imooc.controller.content;

import org.imooc.bean.Business;
import org.imooc.constant.DicTypeConst;
import org.imooc.constant.PageCodeEnum;
import org.imooc.dto.BusinessDto;
import org.imooc.service.BusinessService;
import org.imooc.service.DicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * Created by RookieWangZhiWei on 2018/7/26.
 */
@Controller
@RequestMapping("/businesses")
public class BusinessController {

    @Resource
    private BusinessService businessService;


    @Resource
    private DicService dicService;


    @RequestMapping(method = RequestMethod.GET)
    public String search(Model model, BusinessDto businessDto) {
        model.addAttribute("list", businessService.searchByPage(businessDto));
        model.addAttribute("searcgParam", businessDto);

        return "/content/businessList";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String remove(@PathVariable("id") Long id) {

        return "redirect:/businesses";
    }


    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addInit(Model model) {
        model.addAttribute("cityList", dicService.getListByType(DicTypeConst.CITY));
        model.addAttribute("categoryList", dicService.getListByType(DicTypeConst.CATEGORY));

        return "/content/businessAdd";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String add(BusinessDto businessDto, RedirectAttributes attr) {
        if (businessService.add(businessDto)) {
            attr.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
            return "redirect:/businesses";
        } else {
            return "redirect:/businesses/addPage";
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String modifyInit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("cityList", dicService.getListByType(DicTypeConst.CITY));
        model.addAttribute("categoryList", dicService.getListByType(DicTypeConst.CATEGORY));
        model.addAttribute("modifyObj", businessService.getById(id));

        return "/content/businessModify";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String modify(@PathVariable("id") Long id, BusinessDto businessDto) {

        return "/content/businessModify";
    }
}
