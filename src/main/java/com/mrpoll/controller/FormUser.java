package com.mrpoll.controller;

import com.mrpoll.model.Role;
import com.mrpoll.validator.FieldMatch;
import com.mrpoll.validator.UniqueEmail;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.mrpoll.validator.UniqueUsername;
import org.hibernate.validator.constraints.Email;

@UniqueUsername
@UniqueEmail
@FieldMatch(first = "password", second = "confirmPassword", message = "Passwords don't match")
public class FormUser {
    
    private Integer id;
    
    @NotNull
    @Size(min=2, max=100)
    private String name;
    
    @NotNull
    @Size(min=2, max=45)
    private String username;

    @NotNull
    @Size(min=5, max=100)
    @Email
    private String email;

    @NotNull
    @Size(min=2, max=60)
    private String password;

    @NotNull
    @Size(min=2, max=60)
    private String confirmPassword;
    
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
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
}
