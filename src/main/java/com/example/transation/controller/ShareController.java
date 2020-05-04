package com.example.transation.controller;

import com.example.transation.common.Result;
import com.example.transation.entity.VO.SharesLogVO;
import com.example.transation.entity.bean.SharesLog;
import com.example.transation.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author : zhoutiejun@youngyedu.com, 2020/4/25 0025 下午 20:57
 * @description :
 * @modified : zhoutiejun@youngyedu.com, 2020/4/25 0025 下午 20:57
 */
@Controller
@RequestMapping("/share")
public class ShareController {
    @Autowired
    private ShareService shareService;

    @RequestMapping("/list")
    public ModelAndView listShare(String keyword){
        ModelAndView modelAndView = new ModelAndView("tables-share");
        List<SharesLogVO> sharesList =  shareService.listShare(keyword);
        modelAndView.addObject("shareLogList", sharesList);
        return modelAndView;
    }


    @RequestMapping("/history/rank")
    @ResponseBody
    public Result<List<SharesLogVO>> listRankShare(@RequestParam(value = "pageSize", required = false,defaultValue = "6")
                                              Integer pageSize){
        List<SharesLogVO> sharesLogVOList = shareService.listRankShare(pageSize);
        return  Result.success(sharesLogVOList);
    }

}
