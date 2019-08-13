package com.mainul35.controller;

import com.mainul35.dao.StatusDao;
import com.mainul35.dao.UserDao;
import com.mainul35.entity.Status;
import com.mainul35.entity.User;
import com.mainul35.enums.Visibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class PostController {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private StatusDao statusDaoImpl;
    @Autowired
    private UserDao userDaoImpl;

    @RequestMapping(value = "/post-editor", method = {RequestMethod.GET, RequestMethod.POST})
    public String submitPost(Model model, @Valid @ModelAttribute("status") Status status,
                             BindingResult theBindingResult, HttpServletRequest request) {
        User user = userDaoImpl.getUserByUsername((String) request.getSession().getAttribute("username"));
        List<String> visibilities = new ArrayList<>();
        visibilities.add(Visibility.valueOf(Visibility.PUBLIC));
        visibilities.add(Visibility.valueOf(Visibility.PRIVATE));
        model.addAttribute("visibilities", visibilities);

        String errorMessage = "Form submission failed due to following validation errors.<br><ul>";
        if (request.getMethod().equalsIgnoreCase("POST")) {
            if (theBindingResult.hasErrors()) {
                for (ObjectError error : theBindingResult.getAllErrors()) {
                    errorMessage += "<li><h6>" + error.getDefaultMessage() + "</h6></li>";
                }
                errorMessage += "</ul>";
                model.addAttribute("template", "postEditor");
                model.addAttribute("status", status);
                model.addAttribute("errorMessage", errorMessage);
                return "index";
            } else {
                Status status1 = statusDaoImpl.getById(status.getId());
                if (status1 != null) {
                    status1.setStatus(status1.getStatus());
                    status1.setUpdatedOn(new Date());
                    status1.setVisibility(status.getVisibility());
                    status1.setLocations(status.getLocations());
                    statusDaoImpl.update(status1);
                    model.addAttribute("successMessage", messageSource.getMessage("x0.has.been.x1.successfully", new Object[]{"status", "updated"}, null));
                } else {
                    user = userDaoImpl.getUserByUsername((String) request.getSession().getAttribute("username"));
                    if (user != null) {
                        status.setOwner(user);
                        status.setCreatedOn(new Date());
                    }
                    statusDaoImpl.save(status);
                    model.addAttribute("successMessage", messageSource.getMessage("x0.has.been.x1.successfully", new Object[]{"status", "created"}, null));
                }
            }
        }
        model.addAttribute("posts", statusDaoImpl.getByOwner(user));
        model.addAttribute("status", status);
        return "home";
    }

    @RequestMapping("/edit-post")
    public String editPost(Model model, HttpServletRequest request){
        Map<String, String[]> params = request.getParameterMap();
        Long id = null;
        Status status = null;
        if (params.get("id")[0]!=null) {
            if (!params.get("id")[0].isEmpty()) {
                id = Long.parseLong(params.get("id")[0]);
                status = statusDaoImpl.getById(id);
            } else {
                status = new Status();
                status.setId(System.currentTimeMillis());
            }
        } else {
            status = new Status();
            status.setId(System.currentTimeMillis());
        }
        User user = userDaoImpl.getUserByUsername((String) request.getSession().getAttribute("username"));
        List<String> visibilities = new ArrayList<>();
        visibilities.add(Visibility.valueOf(Visibility.PUBLIC));
        visibilities.add(Visibility.valueOf(Visibility.PRIVATE));
        model.addAttribute("visibilities", visibilities);
        String errorMessage = "Form submission failed due to following validation errors.<br><ul>";
        model.addAttribute("update", true);
        model.addAttribute("posts", statusDaoImpl.getByOwner(user));
        model.addAttribute("status", status);
        return "home";
    }

    @RequestMapping("/delete-post")
    public String deletePost(Model model, HttpServletRequest request){
        Map<String, String[]> params = request.getParameterMap();
        Long id = null;
        Status status = null;
        if (params.get("id")[0]!=null) {
            if (!params.get("id")[0].isEmpty()) {
                id = Long.parseLong(params.get("id")[0]);
                statusDaoImpl.delete(id);
            }
        }
        return "redirect:/";
    }
}
