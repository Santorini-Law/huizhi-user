package com.zhihui.user.controller.v1;

import com.zhihui.user.service.api.GrayService;
import com.zhihui.user.service.api.IUserService;
import com.zhihui.user.vo.UserLoginRequestVO;
import com.zhihui.user.vo.UserLoginResponseVO;
import com.zhihui.utils.api.annotation.ApiResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
@Slf4j
public class UserController {

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

    @ApiResponseBody
    @GetMapping("/hello")
    public String hello() {

        System.out.println(masterUrl);

        return masterUrl;
    }


    @ApiResponseBody
    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    @CrossOrigin
    public UserLoginResponseVO login(@RequestBody UserLoginRequestVO userLoginRequestVO) {
        log.info("login request = {}", userLoginRequestVO.toString());
        UserLoginResponseVO userLoginResponseVO = new UserLoginResponseVO();
        userLoginResponseVO.setToken(userLoginRequestVO.getCode() + userLoginRequestVO.getSource());
        return userLoginResponseVO;
    }


}
