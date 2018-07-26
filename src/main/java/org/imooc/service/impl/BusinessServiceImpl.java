package org.imooc.service.impl;

import org.imooc.bean.Business;
import org.imooc.bean.Page;
import org.imooc.constant.CategoryConst;
import org.imooc.dao.BusinessDao;
import org.imooc.dto.BusinessDto;
import org.imooc.dto.BusinessListDto;
import org.imooc.service.BusinessService;
import org.imooc.util.CommonUtil;
import org.imooc.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RookieWangZhiWei on 2018/7/26.
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private BusinessDao businessDao;

    @Value("${businessImage.savePath}")
    private String savePath;

    @Value("${businessImage.url}")
    private String url;


    public boolean add(BusinessDto businessDto) {
        Business business = new Business();
        BeanUtils.copyProperties(businessDto, business);

        if (businessDto.getImgFile() != null && businessDto.getImgFile().getSize() > 0) {
            try {
                String fileName = FileUtil.save(businessDto.getImgFile(), savePath);

                business.setImgFileName(fileName);

                business.setNumber(0);

                business.setCommentTotalNum(0L);

                business.setStarTotalNum(0L);

                businessDao.insert(business);

                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }

    }

    public BusinessDto getById(Long id) {
        BusinessDto result = new BusinessDto();

        Business business = businessDao.selectById(id);

        BeanUtils.copyProperties(business, result);

        result.setImg(url + business.getImgFileName());

        result.setStar(this.getStar(business));

        return result;
    }

    public List<BusinessDto> searchByPage(BusinessDto businessDto) {
        List<BusinessDto> result = new ArrayList<BusinessDto>();

        Business businessForSelect = new Business();

        BeanUtils.copyProperties(businessDto, businessForSelect);

        List<Business> list = businessDao.selectByPage(businessForSelect);
        for (Business business :
                list) {
            BusinessDto businessDtoTemp = new BusinessDto();
            result.add(businessDtoTemp);
            BeanUtils.copyProperties(business, businessDtoTemp);

            businessDtoTemp.setImg(url + business.getImgFileName());

            businessDto.setStar(this.getStar(business));
        }

        return result;
    }

    public BusinessListDto searchByPageForApi(BusinessDto businessDto) {
        BusinessListDto result = new BusinessListDto();

        Business businessForSelect = new Business();

        BeanUtils.copyProperties(businessDto, businessForSelect);

        if (!CommonUtil.isEmpty(businessDto.getKeyword())) {
            businessForSelect.setTitle(businessDto.getKeyword());
            businessForSelect.setSubtitle(businessDto.getKeyword());
            businessForSelect.setDesc(businessDto.getKeyword());
        }

        if (businessDto.getCategory() != null && CategoryConst.ALL.equals(businessDto.getCategory())) {
            businessForSelect.setCategory(null);
        }

        int currentPage = businessForSelect.getPage().getCurrentPage();
        businessForSelect.getPage().setCurrentPage(currentPage + 1);

        List<Business> list = businessDao.selectLikeByPage(businessForSelect);


        Page page = businessForSelect.getPage();

        result.setHasMore(page.getCurrentPage() < page.getTotalPage());


        for (Business business :
                list) {

            BusinessDto businessDtoTemp = new BusinessDto();

            result.getData().add(businessDtoTemp);
            BeanUtils.copyProperties(business, businessDtoTemp);

            businessDtoTemp.setImg(url + business.getImgFileName());

            businessDtoTemp.setMumber(business.getNumber());

            businessDtoTemp.setStar(this.getStar(business));
        }

        return result;
    }


    private int getStar(Business business) {
        if (business.getStarTotalNum() != null && business.getCommentTotalNum() != null && business.getCommentTotalNum() != 0) {
            return (int) (business.getStarTotalNum() / business.getCommentTotalNum());
        } else {
            return 0;
        }
    }
}
