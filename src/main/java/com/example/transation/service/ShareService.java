package com.example.transation.service;

import com.example.transation.entity.VO.SharesLogVO;

import java.util.List;

/**
 * @author : zhoutiejun@youngyedu.com, 2020/4/25 0025 下午 21:04
 * @description :
 * @modified : zhoutiejun@youngyedu.com, 2020/4/25 0025 下午 21:04
 */
public interface ShareService {
    /**
     * 列举股票
     * @param keyword 关键字
     * @return
     */
    List<SharesLogVO> listShare(String keyword);

    /**
     * @Description : 排名
     * @Author : zhoutiejun@youngyedu.com, 2020/4/25 0025  下午 21:51
     * @Modified : zhoutiejun@youngyedu.com, 2020/4/25 0025  下午 21:51
     * @Param
     * @return
     */
    List<SharesLogVO> listRankShare(Integer pageSize);
}
