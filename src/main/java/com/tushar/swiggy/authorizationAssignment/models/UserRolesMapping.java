package com.tushar.swiggy.authorizationAssignment.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_roles_mapping")
public class UserRolesMapping {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Roles roles;

    public UserRolesMapping(UserInfo userInfo, Roles roles) {
        this.userInfo = userInfo;
        this.roles = roles;
    }
}
