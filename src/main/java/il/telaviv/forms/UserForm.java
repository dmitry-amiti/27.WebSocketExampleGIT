package il.telaviv.forms;

import lombok.Data;

@Data
public class UserForm {
    private String name;
    private String lastname;
    private String login;
    private String password;
    private String role;
}
