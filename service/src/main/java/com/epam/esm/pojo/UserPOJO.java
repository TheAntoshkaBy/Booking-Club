package com.epam.esm.pojo;

import com.epam.esm.entity.Role;
import com.epam.esm.entity.User;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPOJO {

    private Long user_id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private List<Role> roles;

    public UserPOJO(User user) {
        this.user_id = user.getUser_id();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }
}

