package kr.co.rudaks.web.bean;

import lombok.Data;

@Data
public class LoginForm
{
    private String userId;
    private String password;
    private String username;
    private String email;
    private String retUrl;
}
