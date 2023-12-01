package br.com.unifalmg.hotel.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginForm {
    private String username;
    private String password;

}
