package com.tushar.swiggy.jpaBasics;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Restaurant")
public class Restaurant {

    private int id;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId(){
        return this.id;
    }

    @Column(name = "name")
    public String name;

    @Column(name = "pincode")
    public int pincode;

    @Column(name = "rating")
    public int rating;

    @Column(name = "cuisine")
    public String cuisine;
}
