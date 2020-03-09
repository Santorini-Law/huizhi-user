package com.zhihui.user.controller.v1;

import com.google.common.collect.Maps;
import com.zhihui.user.service.api.GrayService;
import com.zhihui.user.service.api.IUserService;
import com.zhihui.user.vo.GrayExecuteRequestVO;
import com.zhihui.user.vo.GrayExecuteResponseVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * article controller
 *
 * @author LDZ
 * @date 2019-09-10 12:23
 */
@RestController
@RequestMapping(value = "/user/v1")
public class Article {

    @Value("${content}")
    private String content;

    @Value("${spring.datasource.other.url}")
    private String url;

    @Value("${spring.datasource.hikari.master.url}")
    private String masterUrl;

    @Bean
    private Map<String, Integer> integer1() {
        return new HashMap<String, Integer>() {{
            put("1", 1);
        }};
    }

    @Resource
    Map<String, Integer> integer1;

    @Resource
    IUserService userService;

    @Resource
    GrayService grayService;

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
//
//
//        GrayExecuteRequestVO grayExecuteRequestVO = new GrayExecuteRequestVO();
//        grayExecuteRequestVO.setObject(userService);
//        grayExecuteRequestVO.setOldLogicMethod("getUserNameById");
//        long uid = 1L;
//        grayExecuteRequestVO.setOldParams(new Object[]{uid});
//        grayExecuteRequestVO.setNewLogicMethod("getUserNameByBB");
//        grayExecuteRequestVO.setNewParams(new Object[]{1, "aa"});
//        grayExecuteRequestVO.setGrayId(200L);
//        grayExecuteRequestVO.setUid(202012199L);
//
//        GrayExecuteResponseVO grayExecuteResponseVO = grayService.executeGrayLogic(grayExecuteRequestVO);
//
//        Boolean grayResult = grayExecuteResponseVO.getGrayResult();
//
//        String result = (String) grayExecuteResponseVO.getResult();

        System.out.println(masterUrl);

        return masterUrl;
    }
}
