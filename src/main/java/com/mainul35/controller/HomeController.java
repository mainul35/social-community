package com.mainul35.controller;

import com.mainul35.config.security.CustomAuthSuccessHandler;
import com.mainul35.dao.UserDao;
import com.mainul35.entity.User;
import com.mainul35.impl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;


@Controller
public class HomeController {
    @Autowired
    private UserDao userDaoImpl;
    @Autowired
    CustomAuthSuccessHandler successHandler;
    @Autowired
    PasswordEncoder passwordEncoder;
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("template", "posts");
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("template", "loginForm");
        return "index";
    }

    @RequestMapping("/profile")
    public String profile(Model model){
        model.addAttribute("template", "posts");
        return "index";
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
    public String register(Model model, HttpServletRequest request){
        if (request.getMethod().equalsIgnoreCase("POST")) {
            Map<String, String[]> params = request.getParameterMap();
            userDaoImpl.addUser(params);
        }
        model.addAttribute(
                "template", "registrationForm");
        return "index";
    }
}
