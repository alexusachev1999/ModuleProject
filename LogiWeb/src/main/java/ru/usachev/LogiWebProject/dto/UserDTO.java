package ru.usachev.LogiWebProject.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class UserDTO {

    @NotBlank(message = "Логин не должен быть пустым")
    private String username;

    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;

    private boolean enabled;

    @NotBlank
    private String userRole;

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return enabled == userDTO.enabled && Objects.equals(username, userDTO.username) && Objects.equals(password, userDTO.password) && Objects.equals(userRole, userDTO.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, enabled, userRole);
    }
}
