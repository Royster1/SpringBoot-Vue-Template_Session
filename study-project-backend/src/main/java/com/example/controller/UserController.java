package com.example.controller;

import com.example.entity.RestBean;
import com.example.entity.user.AccountInfo;
import com.example.entity.user.AccountUser;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$";

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

    @PostMapping("/save-email")
    public RestBean<String> saveInfo(@Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email,
                                     @SessionAttribute("account") AccountUser user){
        if(userService.saveEmail(email, user.getId())) {
            user.setEmail(email);
            return RestBean.success();
        } else {
            return RestBean.failure(400, "邮件地址已被其他用户使用，无法修改");
        }
    }

    @GetMapping("/email")
    public RestBean<String> email(@SessionAttribute("account") AccountUser user){
        return RestBean.success(user.getEmail());
    }
}
