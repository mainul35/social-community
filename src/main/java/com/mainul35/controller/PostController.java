package com.mainul35.controller;

import com.mainul35.dao.LocationDao;
import com.mainul35.entity.Location;
import com.mainul35.entity.Status;
import com.mainul35.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class PostController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/post-editor")
    public String postEditor(Model model){
        model.addAttribute("template", "postEditor");
        model.addAttribute("status", new Status());
        return "index";
    }

    @PostMapping("/post-editor")
    public String submitPost(Model model, @Valid @ModelAttribute("status") Status status,
                             BindingResult theBindingResult) {
        return "index";
    }
}
