package org.mercatto.mercatto_backend.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String number;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_PROFILE",
            joinColumns = {
                @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<ProfileModel> profile;

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

    public Set<ProfileModel> getProfile() {
        return profile;
    }

    public void setProfile(Set<ProfileModel> profile) {
        this.profile = profile;
    }
}
