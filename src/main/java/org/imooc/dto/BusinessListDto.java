package org.imooc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by RookieWangZhiWei on 2018/7/26.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessListDto {

    private boolean hasMore;
    private List<BusinessDto> data;


    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<BusinessDto> getData() {
        return data;
    }

    public void setData(List<BusinessDto> data) {
        this.data = data;
    }
}
