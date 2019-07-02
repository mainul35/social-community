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
import java.util.Map;

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
        model.addAttribute("locationList", locationDaoImpl.getAllLocations());
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
                    Location location1 = locationDaoImpl.getLocationById(location.getId());
                    if (location1 != null) {
                        location1.setLocationName(location.getLocationName());
                        locationDaoImpl.updateLocation(location1);
                        model.addAttribute("successMessage", messageSource.getMessage("x0.has.been.x1.successfully", new Object[]{"location", "updated"}, null));
                    } else {
                        location1.setLocationName(location.getLocationName());
                        locationDaoImpl.addLocation(location1);
                        model.addAttribute("successMessage", messageSource.getMessage("x0.has.been.x1.successfully", new Object[]{"location", "created"}, null));
                    }
                }
            }
        }
        model.addAttribute("location", location);
        return "locationView";
    }

    @RequestMapping(value = "/update-location", method = {RequestMethod.GET, RequestMethod.POST})
    public String updateLocation(Model model, HttpServletRequest request){
        model.addAttribute("locationList", locationDaoImpl.getAllLocations());
        Map<String, String[]> params = request.getParameterMap();
        Long id = null;
        Location location = null;
        if (params.get("id")[0]!=null) {
            if (!params.get("id")[0].isEmpty()) {
                id = Long.parseLong(params.get("id")[0]);
                location = locationDaoImpl.getLocationById(id);
            } else {
                location = new Location();
                location.setId(System.currentTimeMillis());
            }
        } else {
            location = new Location();
            location.setId(System.currentTimeMillis());
        }
        model.addAttribute("update", true);
        model.addAttribute("location", location);
        return "locationView";
    }
}