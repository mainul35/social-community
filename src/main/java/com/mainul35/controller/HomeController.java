package com.mainul35.controller;

import com.mainul35.config.security.CustomAuthSuccessHandler;
import com.mainul35.dao.LocationDao;
import com.mainul35.dao.StatusDao;
import com.mainul35.dao.UserDao;
import com.mainul35.entity.Location;
import com.mainul35.entity.Status;
import com.mainul35.entity.User;
import com.mainul35.enums.Visibility;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private LocationDao locationDaoImpl;
    @Autowired
    private StatusDao statusDaoImpl;

    @RequestMapping("/")
    public String index(Model model, HttpSession session){
        try {
            initializeDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = null;
        if (session.getAttribute("username") != null) {
            if (!((String) session.getAttribute("username")).isEmpty()) {
                user = userDaoImpl.getUserByUsername((String) session.getAttribute("username"));
            }
        }
        if (user != null) {
            model.addAttribute("posts", statusDaoImpl.getAllByVisibility(null, user));
        } else {
            model.addAttribute("posts", statusDaoImpl.getAllByVisibility(Visibility.PUBLIC, null));
        }
        return "landing";
    }

    @RequestMapping("/login")
    public String login(Model model, @Valid @ModelAttribute("user") User user,
                        BindingResult theBindingResult, HttpSession session){
        if (session.getAttribute("username") != null) {
            if (!((String)session.getAttribute("username")).isEmpty()){
                return profile(model, session);
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
    public String profile(Model model, HttpSession session){
        User user = userDaoImpl.getUserByUsername((String) session.getAttribute("username"));
        Status status = new Status();
        status.setId(System.currentTimeMillis());
        model.addAttribute("status", status);
        model.addAttribute("posts", statusDaoImpl.getByOwner(user));
        List<String> locationNames = new ArrayList<>();
        for (Location l: locationDaoImpl.getAllLocations()) {
            locationNames.add(l.getLocationName());
        }
        model.addAttribute("locationList", locationNames);
        List<String> visibilities = new ArrayList<>();
        visibilities.add(Visibility.valueOf(Visibility.PUBLIC));
        visibilities.add(Visibility.valueOf(Visibility.PRIVATE));
        model.addAttribute("visibilities", visibilities);
        return "home";
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
    public String register(Model model, @Valid @ModelAttribute("user") User user,
                           BindingResult theBindingResult, HttpServletRequest request){
        List<Location> locations = locationDaoImpl.getAllLocations();
        List<String> locationNames = new ArrayList<>();
        for (Location l: locationDaoImpl.getAllLocations()) {
            locationNames.add(l.getLocationName());
        }
        model.addAttribute("locationList", locationNames);
        if (request.getSession().getAttribute("username") != null) {
            if (!((String)request.getSession().getAttribute("username")).isEmpty()){
                return profile(model, request.getSession());
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
                    errorMessage += "<li><h6>" + messageSource.getMessage("this.x0.is.already.registered.in.database,please.select.another.one", new Object[]{"user"}, null) + "</h6></li></ul>";
                    model.addAttribute("errorMessage", errorMessage);
                } else {
                    User user1 = userDaoImpl.getUserById(user.getId());
                    if (user1 != null) {
                        user1.setEmail(user.getEmail());
                        user1.setMyLocation(user.getMyLocation());
                        user1.setPassword(user.getPassword());
                        user1.setUsername(user.getUsername());
                        user1.setRole(user.getRole());
                        user1.setUpdatedOn(new Date());
                        userDaoImpl.update(user1);
                        model.addAttribute("successMessage", messageSource.getMessage("x0.has.been.x1.successfully", new Object[]{"user", "updated"}, null));
                    } else {
                        userDaoImpl.addUser(user);
                        model.addAttribute("successMessage", messageSource.getMessage("x0.has.been.x1.successfully", new Object[]{"user", "registered"}, null));
                    }
                }
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("template", "registrationForm");
        return "index";
    }

    public void initializeDB(){
        if (locationDaoImpl.getAllLocations().size() < 1) {
            createLocation("Dhaka");
            createLocation("Khulna");
            createLocation("Chattogram");
            createLocation("Jessore");

            User user = new User();
            user.setEmail("mainuls18@gmail.com");
            user.setUsername("mainul35");
            user.setPassword(passwordEncoder.encode("secret"));
            user.setName("Syed Mainul Hasan");
            user.setCreatedOn(new Date());
            user.setMyLocation("Dhaka");
            user.setUpdatedOn(new Date());
            user.setId(System.currentTimeMillis());
            userDaoImpl.addUser(user);

            Status status = new Status();
            status.setOwner(user);
            List<String> location = new ArrayList<>();
            location.add("Dhaka");
            status.setLocations(location);
            status.setId(System.currentTimeMillis());
            status.setVisibility(Visibility.valueOf(Visibility.PUBLIC));
            status.setTitle("Seed data");
            status.setStatus("Lorem Ipsum Dolor");
            status.setCreatedOn(new Date());
            statusDaoImpl.save(status);
        }
    }

    private void createLocation(String name) {
        Location location = new Location();
        location.setLocationName(name);
        location.setId(System.currentTimeMillis());
        locationDaoImpl.addLocation(location);
    }
}
