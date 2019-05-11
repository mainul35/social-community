package com.mainul35.controller;

import com.mainul35.dao.LocationDao;
import com.mainul35.entity.Location;
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
public class LocationController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocationDao locationDaoImpl;

    @RequestMapping(value = "/add-location", method = {RequestMethod.GET, RequestMethod.POST})
    public String addLocation(Model model, @Valid @ModelAttribute("location") Location location,
                              BindingResult theBindingResult, HttpServletRequest request) {
        String errorMessage = "Form submission failed due to following validation errors.<br><ul>";
        if (location == null) {
            location = new Location();
            location.setId(System.currentTimeMillis());
        } else {
            if (location.getId() == null) {
                location.setId(System.currentTimeMillis());
            }
        }
        if (request.getMethod().equalsIgnoreCase("POST")) {
            if (theBindingResult.hasErrors()) {
                for (ObjectError error : theBindingResult.getAllErrors()) {
                    errorMessage += "<li><h6>" + error.getDefaultMessage() + "</h6></li>";
                }
                errorMessage += "</ul>";
                model.addAttribute("template", "locationForm");
                model.addAttribute("location", location);
                model.addAttribute("errorMessage", errorMessage);
                return "index";
            } else {
                if (locationDaoImpl.getLocationByName(location.getLocationName()) instanceof Location) {
                    errorMessage += "<li><h6>" + messageSource.getMessage("this.x0.is.already.registered.in.database,please.select.another.one", new Object[]{"location"}, null) + "</h6></li></ul>";
                    model.addAttribute("errorMessage", errorMessage);
                } else {
                    locationDaoImpl.addLocation(location);
                }
            }
        }
        model.addAttribute("location", location);
        model.addAttribute("template", "locationForm");
        return "index";
    }
}