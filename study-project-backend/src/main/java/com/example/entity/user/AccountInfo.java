package com.example.entity.user;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AccountInfo {

    int uid;
    String username;
    String sex;
    String phone;
    String qq;
    String wx;
    String blog;
    String biography;
}
