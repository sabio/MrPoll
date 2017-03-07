package com.mrpoll.controller;

import com.mrpoll.model.Role;
import com.mrpoll.validator.FieldMatch;
import com.mrpoll.validator.Username;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch(first = "password", second = "confirmPassword", message = "Passwords don't match")
public class FormUser {
    
    private Integer id;
    
    @NotNull
    @Size(min=2, max=45)
    @Username
    private String username;

    @NotNull
    @Size(min=5, max=100)
    private String email;

    @NotNull
    @Size(min=2, max=60)
    private String password;

    @NotNull
    private String confirmPassword;
    
    private String currentPassword;
    
    @NotNull
    private Boolean enabled;
    
    @NotNull
    @Size(min=1)
    private List<Role> roles;
    
    
    public boolean isNew() {
        return (this.id == null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }
    
    
}
