package com.mainul35.controller;

import com.mainul35.entity.Status;
import com.mainul35.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class PostController {

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
