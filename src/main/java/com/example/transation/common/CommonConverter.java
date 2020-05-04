/*
 * Copyright 2016 uncle5.com All right reserved. This software is the
 * confidential and proprietary information of uncle5.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with uncle5.com .
 */
package com.example.transation.common;

import com.example.transation.util.TimeUtil;
import com.google.common.collect.Lists;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author: xzh
 * @className: CommonConverter
 * @description: 拷贝类
 * @createTime: 2018/6/26 下午7:42
 **/
public class CommonConverter {

    private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    private static MapperFacade mapperFacade = null;

    static {
        CommonConverter.mapperFactory.getConverterFactory()
                .registerConverter("DateConvert", new BidirectionalConverter<Date, String>() {
                    @Override
                    public String convertTo(Date date, Type<String> type, MappingContext mappingContext) {
                        return TimeUtil.getFormatTimeStr(date, "yyyy-MM-dd");
                    }

                    @Override
                    public Date convertFrom(String dateStr, Type<Date> type, MappingContext mappingContext) {
                        return TimeUtil.getDateByString(dateStr, "yyyy-MM-dd");
                    }
                });
        CommonConverter.mapperFactory.getConverterFactory()
                .registerConverter("DateSimpleConvert", new BidirectionalConverter<Date, String>() {
                    @Override
                    public String convertTo(Date date, Type<String> type, MappingContext mappingContext) {
                        return TimeUtil.dateToSimpleString(date);
                    }

                    @Override
                    public Date convertFrom(String dateStr, Type<Date> type, MappingContext mappingContext) {
                        return TimeUtil.getDateByString(dateStr);
                    }
                });
        CommonConverter.mapperFactory.getConverterFactory()
                .registerConverter("DateSimpleCNConvert", new BidirectionalConverter<Date, String>() {
                    @Override
                    public String convertTo(Date date, Type<String> type, MappingContext mappingContext) {
                        return TimeUtil.dateToSimpleCNString(date);
                    }

                    @Override
                    public Date convertFrom(String dateStr, Type<Date> type, MappingContext mappingContext) {
                        return TimeUtil.getDateByString(dateStr);
                    }
                });

        CommonConverter.mapperFacade = CommonConverter.mapperFactory.getMapperFacade();
    }

    private CommonConverter() {
    }

    public static MapperFacade getMapperFacade() {
        return CommonConverter.mapperFacade;
    }

    public static <T> T map(Object sourceObject, Class<T> targetClass) {
        if (sourceObject == null) {
            return null;
        }
        return CommonConverter.mapperFacade.map(sourceObject, targetClass);
    }

    public static <T> List<T> mapList(Collection<?> sourceObjects, Class<T> targetClass) {
        List<T> result = Lists.newArrayList();
        if (sourceObjects != null && !sourceObjects.isEmpty()) {
            for (Object sourceObject : sourceObjects) {
                result.add(CommonConverter.map(sourceObject, targetClass));
            }
        }
        return result;
    }

    public static <T> PagedList<T> mapPagedList(PagedList<?> sourcePagedList, Class<T> targetDataItemClass) {
        if (sourcePagedList == null) {
            return PagedList.newMe();
        } else if (CollectionUtils.isEmpty(sourcePagedList.getData())) {
            return PagedList.newMe(sourcePagedList.getPageNum(), sourcePagedList.getPageSize(),
                    sourcePagedList.getTotalCount(), new ArrayList<>());
        }

        List<T> mappedData = CommonConverter.mapList(sourcePagedList.getData(), targetDataItemClass);
        return PagedList.newMe(sourcePagedList.getPageNum(), sourcePagedList.getPageSize(),
                sourcePagedList.getTotalCount(), mappedData);
    }
}
