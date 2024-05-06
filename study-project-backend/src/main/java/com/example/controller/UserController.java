package com.example.controller;

import com.example.entity.RestBean;
import com.example.entity.user.AccountInfo;
import com.example.entity.user.AccountUser;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/me")
    public RestBean<AccountUser> me(@SessionAttribute("account") AccountUser user){
        return RestBean.success(user);
    }


    @PostMapping("/save-info")
    public RestBean<Void> saveInfo(@RequestBody AccountInfo info,
                                     @SessionAttribute("account") AccountUser user){
        info.setUid(user.getId());
        userService.saveUserInfo(info);
        return RestBean.success();
    }
}
