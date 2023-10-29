package com.tushar.swiggy.springSecurityBasics.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class Users {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String password;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
