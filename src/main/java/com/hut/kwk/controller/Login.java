package com.hut.kwk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create by Wang Heng on 2019-04-18
 *
 * @author Wang Heng
 */
@Controller
public class Login {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
