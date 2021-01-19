package com.mayko.ewhaplate.controller.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminApiController {


    @Value("${admin.password}")
    private String password;

    @PostMapping("/check")
    public boolean checkPassword(@RequestBody String pw){
        return password.equals(pw);
    }
}
