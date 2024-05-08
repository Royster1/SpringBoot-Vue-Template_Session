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
    public RestBean<String> saveInfo(@RequestBody @Validated AccountInfo info,
                                     @SessionAttribute("account") AccountUser user){
        info.setUid(user.getId());
        if (userService.saveUserInfo(info)) {
            user.setUsername(info.getUsername());
            return RestBean.success();
        } else {
            return RestBean.failure(400, "用户名称已被其他用户使用, 无法修改");
        }
    }

    @GetMapping("/info")
    public RestBean<AccountInfo> info(@SessionAttribute("account") AccountUser user){
        return RestBean.success(userService.userInfo(user.getId()));
    }
}
