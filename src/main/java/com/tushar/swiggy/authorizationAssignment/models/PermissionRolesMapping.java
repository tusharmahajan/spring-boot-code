package com.tushar.swiggy.authorizationAssignment.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "permission_roles_mapping")
public class PermissionRolesMapping {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "id")
    private Permissions permissions;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Roles roles;

    @Column(name = "status")
    private String status;

    public PermissionRolesMapping(Permissions permissions, Roles roles, String status) {
        this.permissions = permissions;
        this.roles = roles;
        this.status = status;
    }
}
