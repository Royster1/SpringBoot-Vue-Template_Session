package com.example.service.impl;

import com.example.entity.auth.Account;
import com.example.entity.user.AccountInfo;
import com.example.entity.user.AccountPrivacy;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

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

    @Override
    public boolean changePassword(String old, String _new, int uid) {
        Account account = userMapper.findAccountById(uid);
        if (encoder.matches(old, account.getPassword())) {
            String encode = encoder.encode(_new);
            userMapper.updatePassword(encode, uid);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void saveUserPrivacy(AccountPrivacy privacy) {
        userMapper.savePrivacy(privacy);
    }

    @Override
    public AccountPrivacy userPrivacy(int uid) {
        return userMapper.findPrivacyById(uid);
    }
}
