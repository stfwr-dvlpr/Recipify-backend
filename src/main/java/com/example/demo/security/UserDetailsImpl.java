package com.example.demo.security;

import java.util.Collection;
import java.util.Collections;
import com.example.demo.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
    private Long id;
    private String username;
    private String email;
    private String password;

    public UserDetailsImpl(Long id, String username, String email, String password) {
        this.id = id; this.username = username; this.email = email; this.password = password;
    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // add roles if needed
    }
    @Override public String getPassword() { return password; }
    @Override public String getUsername() { return email; } // we use email as principal
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}