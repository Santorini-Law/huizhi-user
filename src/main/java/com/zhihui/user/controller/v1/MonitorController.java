package com.zhihui.user.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1
 *
 * @author LDZ
 * @date 2020-03-04 15:43
 */
@RestController
@RequestMapping(value = "interest")
public class MonitorController {

    @ResponseBody
    @GetMapping("/public/user")
    public String hello() {
        return "Server is OJBK";
    }
}
