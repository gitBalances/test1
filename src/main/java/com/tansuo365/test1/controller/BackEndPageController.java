package com.tansuo365.test1.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 后端页面控制层 (需权限判定)
 */
@Controller
@RequestMapping("/admin")
public class BackEndPageController {

    @RequestMapping("/index")
    public String index(){
        return "/admin/index"; //52行跳转过来
    }

    @RequestMapping("deleteOrder")
    public String deleteOrder(){
        return "/admin/deleteOrder";
    }

    @RequestMapping("deleteProduct")
    public String deleteProduct(){
        return "/admin/deleteProduct";
    }

    @RequestMapping("listProduct")
    public String listProduct(){
        return "/admin/listProduct";
    }

    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(){
        return "/admin/login"; //对应用户登录展示
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, String name, String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);

        subject.login(token);
        Session session = subject.getSession();
        session.setAttribute("subject",subject);
        return "redirect:/admin/index";//该路径应该为后台的首页页面,为login之后的redirect路径
    }

    @RequestMapping("unauthorized")
    public String noPerms(){
        return "/admin/unauthorized";
    }
}
