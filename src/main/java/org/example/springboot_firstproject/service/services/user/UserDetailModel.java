package org.example.springboot_firstproject.service.services.user;

import org.example.springboot_firstproject.data.access.user.GameUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserDetailModel implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailModel(GameUser user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = getAuthoritiesFromRole(user.getRole());
    }

    private Collection<? extends GrantedAuthority> getAuthoritiesFromRole(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String[] authoritiesFromRoles = role.split(",");
        for (String authority : authoritiesFromRoles) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }
        return authorities;
    }
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
