package com.hwyoung.concurrency.controller;

import com.hwyoung.concurrency.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * threadLocal控制器
 */
@Slf4j
@Controller
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @RequestMapping("test")
    @ResponseBody
    public Long test(){
        return RequestHolder.getId();
    }
}
