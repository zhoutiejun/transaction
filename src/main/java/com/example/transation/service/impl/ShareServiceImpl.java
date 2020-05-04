package com.example.transation.service.impl;

import com.example.transation.entity.VO.SharesLogVO;
import com.example.transation.entity.bean.Shares;
import com.example.transation.entity.bean.SharesLog;
import com.example.transation.entity.bean.SharesLogExample;
import com.example.transation.mapper.SharesLogMapper;
import com.example.transation.mapper.SharesMapper;
import com.example.transation.service.ShareService;
import com.example.transation.util.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : zhoutiejun@youngyedu.com, 2020/4/25 0025 下午 21:05
 * @description :
 * @modified : zhoutiejun@youngyedu.com, 2020/4/25 0025 下午 21:05
 */
@Service("shareService")
public class ShareServiceImpl implements ShareService {

    @Autowired
    private SharesLogMapper sharesLogMapper;

    @Autowired
    private SharesMapper sharesMapper;

    @Override
    public List<SharesLogVO> listShare(String keyword) {
        SharesLogExample sharesLogExample = new SharesLogExample();
        sharesLogExample.setOrderByClause(" create_time desc ");
        if(!StringUtils.isEmpty(keyword)){
            sharesLogExample.createCriteria().andShareNumLike("%"+keyword+"%");
        }
        List<SharesLog> sharesLogVOList = sharesLogMapper.selectByExample(sharesLogExample);
        return transfer(sharesLogVOList);
    }

    @Override
    public List<SharesLogVO> listRankShare(Integer pageSize) {
        SharesLogExample sharesLogExample = new SharesLogExample();
        sharesLogExample.setOrderByClause(" price desc ");
        sharesLogExample.createCriteria().andCreateTimeGreaterThanOrEqualTo(TimeUtil.getZeroTime(new Date()));

        List<SharesLog> sharesLogVOList = sharesLogMapper.selectByExample(sharesLogExample);
        sharesLogVOList = sharesLogVOList.stream().limit(pageSize).collect(Collectors.toList());

        return transfer(sharesLogVOList);
    }

    private List<SharesLogVO> transfer(List<SharesLog> sharesLogList) {
        List<Shares> sharesList = sharesMapper.selectByExample(null);
        Map<String, String> shareMap = sharesList.stream()
                .collect(Collectors.toMap(Shares::getNum, Shares::getName));

        List<SharesLogVO> sharesLogVOList = new ArrayList<>();
        for(SharesLog sharesLog : sharesLogList){
            SharesLogVO sharesLogVO = new SharesLogVO();
            BeanUtils.copyProperties(sharesLog, sharesLogVO);

            sharesLogVO.setCreateTime(TimeUtil.getFormatTimeStr(sharesLog.getCreateTime(), TimeUtil.DEFAULT_FORMAT));
            sharesLogVO.setName(shareMap.getOrDefault(sharesLog.getShareNum(),""));

            sharesLogVOList.add(sharesLogVO);
        }
        return sharesLogVOList;
    }
}
