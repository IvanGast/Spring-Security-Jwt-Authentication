package com.devbridge.feedback.controller;

import com.devbridge.feedback.Application;
import com.devbridge.feedback.entity.User;
import com.devbridge.feedback.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Application.PATH)
public class UserController {

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public User saveUser(@RequestBody User user) {
        return securityService.save(user);
    }

    @RequestMapping(value = "/secured/user", method = RequestMethod.GET)
    public User getUser() {
        User user = securityService.getCurrentUser();
        user.setPassword(null);
        return user;
    }

}
