package com.mainul35.controller;

import com.mainul35.config.security.CustomAuthSuccessHandler;
import com.mainul35.dao.UserDao;
import com.mainul35.entity.Status;
import com.mainul35.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    private MessageSource messageSource;
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
    public String login(Model model, @Valid @ModelAttribute("user") User user,
                        BindingResult theBindingResult, HttpSession session){
        if (session.getAttribute("username") != null) {
            if (!((String)session.getAttribute("username")).isEmpty()){
                return "redirect:/profile";
            }
        }
        String errorMessage = "Form submission failed due to following validation errors.<br><ul>";
        if (theBindingResult.hasErrors()) {
            for (ObjectError error: theBindingResult.getAllErrors()) {
                errorMessage += "<li><h6>" + error.getDefaultMessage() + "</h6></li>";
            }
            errorMessage += "</ul>";
            model.addAttribute("template", "loginForm");
            model.addAttribute("user", user);
            model.addAttribute("errorMessage", errorMessage);
            return "index";
        } else {
            model.addAttribute("template", "loginForm");
            model.addAttribute("user", new User());
            return "index";
        }
    }

    @RequestMapping("/profile")
    public String profile(Model model){
        model.addAttribute("status", new Status());
        return "home";
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
    public String register(Model model, @Valid @ModelAttribute("user") User user,
                           BindingResult theBindingResult, HttpServletRequest request){
        if (request.getSession().getAttribute("username") != null) {
            if (!((String)request.getSession().getAttribute("username")).isEmpty()) {
                return "redirect:/profile";
            }
        }
        String errorMessage = "Form submission failed due to following validation errors.<br><ul>";
        if (user == null) {
            user = new User();
            user.setId(System.currentTimeMillis());
        } else if (user.getId() == null) {
            user.setId(System.currentTimeMillis());
        }
        if (request.getMethod().equalsIgnoreCase("POST")) {
            if (theBindingResult.hasErrors()) {
                for (ObjectError error: theBindingResult.getAllErrors()) {
                    errorMessage += "<li><h6>" + error.getDefaultMessage() + "</h6></li>";
                }
                errorMessage += "</ul>";
                model.addAttribute("template", "registrationForm");
                model.addAttribute("user", user);
                model.addAttribute("errorMessage", errorMessage);
                return "index";
            } else {
                if (userDaoImpl.loadUserByUsername(user.getEmail()) instanceof User) {
                    errorMessage += "<li><h6>" + messageSource.getMessage("this.x0.is.already.registered.in.database,please.select.another.one", new Object[]{"location"}, null) + "</h6></li></ul>";
                    model.addAttribute("errorMessage", errorMessage);
                } else {
                    userDaoImpl.addUser(user);
                }
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("template", "registrationForm");
        return "index";
    }
}
