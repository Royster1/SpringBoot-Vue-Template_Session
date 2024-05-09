package com.example.entity.user;

import lombok.Data;

@Data
public class AccountPrivacy {
    int uid;
    boolean email;
    boolean sex;
    boolean phone;
    boolean qq;
    boolean wx;
    boolean blog;
}