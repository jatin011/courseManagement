package com.example.MsProject.auth.controller;


import com.example.MsProject.auth.Model.User;
import com.example.MsProject.auth.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("getAll")
    @CrossOrigin("*")
    ResponseEntity<List<User>> getAllUser()
    {
        return new ResponseEntity<>(loginService.getAllUser(),HttpStatus.OK);
    }

    @PostMapping("/googleLogin")
    @CrossOrigin("*")
    public ResponseEntity<User> loginByGoogle (@RequestBody User user)
    {
        try
        {
            return new ResponseEntity<>(loginService.loginByGoogle(user),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
