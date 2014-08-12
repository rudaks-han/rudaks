package kr.co.rudaks.web.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginForm implements Serializable
{
    private String userId;
    private String password;
    private String username;
    private String email;
    private String retUrl;
}
