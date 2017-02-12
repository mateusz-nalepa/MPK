package com.mpk.helpers;

import com.mpk.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserHelper {
	private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String role;
    private Boolean active;

    public UserHelper(){

    }
    public UserHelper(User user){
        id = user.getId();
        login = user.getUsername();
        password = user.getPassword();
        name = user.getFirstName();
        surname = user.getLastName();
        email = user.getMail();
        phone = user.getPhone();
        role = user.getRole();
        active = user.getActive();
    }

}