package com.ldf.demo.controller;

import com.ldf.demo.pojo.User;
import com.ldf.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author: 清峰
 * @date: 2020/11/2 19:08
 * @code: 愿世间永无Bug!
 * @description:
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String login(){
        return "/login";
    }



    @PostMapping("/login")
    public String login(User user, HttpSession session, RedirectAttributes attributes){
        User userLogin = userService.login(user);
        if (userLogin==null){
            attributes.addFlashAttribute("msg","用户名密码不正确");
            return "redirect:/";
        }
        userLogin.setPassword("");
        session.setAttribute("user",userLogin);
        return "redirect:/files/fileAll";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }
}
