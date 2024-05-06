package com.example.mapper;

import com.example.entity.auth.Account;
import com.example.entity.user.AccountInfo;
import com.example.entity.user.AccountUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from db_account where username = #{text} or email = #{text}")
    Account findAccountByNameOrEmail(String text);  // 用户名或者邮箱登录

    @Select("select * from db_account where username = #{text} or email = #{text}")
    AccountUser findAccountUserByNameOrEmail(String text);

    @Insert("insert into db_account (email, username, password) values (#{email}, #{username}, #{password})")
    int createAccount(String username, String password, String email);

    @Update("update db_account set password = #{password} where email = #{email}")
    int resetPasswordByEmail(String password, String email);

    @Insert("""
            insert into db_account_info (uid, sex, phone, qq, wx, blog, `biography`)
             values (#{uid}, #{sex}, #{phone}, #{qq}, #{wx}, #{blog}, #{biography})
             on duplicate key update uid=#{uid}, sex=#{sex}, phone=#{phone},
             qq=#{qq}, wx=#{wx}, blog=#{blog}, `biography`=#{biography}
            """)
    void saveInfo(AccountInfo info);
}
