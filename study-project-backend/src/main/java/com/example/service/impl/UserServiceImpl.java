package com.example.service.impl;

import com.example.entity.auth.Account;
import com.example.entity.user.AccountInfo;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public boolean saveUserInfo(AccountInfo accountInfo) {
        Account account = userMapper.findAccountByNameOrEmail(accountInfo.getUsername());
        if (account == null) {
            userMapper.updateUsername(accountInfo.getUsername(), accountInfo.getUid());
        } else if (account.getId() != accountInfo.getUid()) {
            return false;
        }
        userMapper.saveInfo(accountInfo);
        return true;
    }

    @Override
    public AccountInfo userInfo(int uid) {
        return userMapper.findInfoById(uid);
    }

    @Override
    public boolean saveEmail(String email, int uid) {
        Account account = userMapper.findAccountByNameOrEmail(email);
        if (account == null) {
            userMapper.updateEmail(email, uid);
        } else return account.getId() == uid;
        return true;
    }
}
