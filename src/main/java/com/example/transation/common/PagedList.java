package com.example.transation.common;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PagedList<T> implements Serializable {

    private static final long serialVersionUID = 1;
    //最大单页显示数量
    private static final int  MAX_PAGE_SIZE    = 100;

    private int               pageNum          = 1;  //当前页号，从1开始
    private int               pageSize         = 10; //每页数量
    private int               totalPages       = 1;  //总页数
    private long              totalCount       = 0;  //总记录数
    private List<T>           data;                  //数据集合

    private PagedList() {
    }

    public static <T> PagedList<T> newMe() {
        PagedList<T> result = new PagedList<>();
        result.data = Lists.newArrayList();
        return result;
    }

    public static <T> PagedList<T> newMe(int pageNum, int pageSize, long totalCount, List<T> data) {
        PagedList<T> result = new PagedList<>();
        if (pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize > PagedList.MAX_PAGE_SIZE) {
            pageSize = PagedList.MAX_PAGE_SIZE;
        }
        if (totalCount < 0) {
            totalCount = 0;
        }

        result.pageNum = pageNum;
        result.pageSize = pageSize;
        result.totalCount = totalCount;
        result.totalPages = (int) Math.ceil(((double) totalCount) / pageSize);
        if (data != null) {
            result.data = data;
        } else {
            result.data = Lists.newArrayList();
        }
        return result;
    }

    public int getMaxPageSize() {
        return PagedList.MAX_PAGE_SIZE;
    }
}
