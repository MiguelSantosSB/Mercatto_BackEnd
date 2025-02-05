package org.mercatto.mercatto_backend.model;

import jakarta.persistence.*;

import java.io.Serial;

@Entity
@Table(name = "role")
public class RoleModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    public RoleModel() {
    }

    public RoleModel(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public void setName(String roleName) {
        this.name = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String roleDescription) {
        this.description = roleDescription;
    }
}
