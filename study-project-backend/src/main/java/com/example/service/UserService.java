package com.example.service;

import com.example.entity.user.AccountInfo;
import com.example.entity.user.AccountPrivacy;

public interface UserService {
    boolean saveUserInfo(AccountInfo accountInfo);

    AccountInfo userInfo(int uid);

    boolean saveEmail(String email, int id);

    boolean changePassword(String old, String _new, int uid);

    void saveUserPrivacy(AccountPrivacy privacy);

    AccountPrivacy userPrivacy(int id);
}
