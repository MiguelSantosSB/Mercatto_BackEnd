package org.mercatto.mercatto_backend.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serial;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
public class UserModel implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String number;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<RoleModel> role;


    public UserModel() {
    }

    public UserModel(Long id, String name, String email, String number, String password, Set<RoleModel> role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleModel> getRole() {
        return role;
    }

    public void setRole(Set<RoleModel> role) {
        this.role = role;
    }

    // métodos de UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.stream().map(role -> (GrantedAuthority) role::getName).collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}