package com.mainul35.controller;

import com.mainul35.dao.UserDao;
import com.mainul35.impl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;


@Controller
public class HomeController {
    @Autowired
    private Properties jdbcProperties;
    @Autowired
    private UserDao userDaoImpl;
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("name", "Mainul");
        model.addAttribute("template", "loginForm");
//        ProcessBuilder processBuilder = new ProcessBuilder(
//                "mysql",
//                "-u",
//                jdbcProperties.getProperty("springframework.jdbc.username"),
//                "-p"+jdbcProperties.getProperty("springframework.jdbc.password"),
//                "-e",
//                "DROP DATABASE IF EXISTS " + jdbcProperties.getProperty("springframework.jdbc.dbName") + ";",
//                "CREATE DATABASE IF NOT EXISTS " + jdbcProperties.getProperty("springframework.jdbc.dbName") + ";"
//        );
//        try {
//            processBuilder.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        userDaoImpl.addUser("Syed Mainul Hasan");
        System.out.println(Arrays.toString(userDaoImpl.list().toArray()));
        return "index";
    }
}
