package com.tushar.swiggy.jpaBasics.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "person_address")
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "addressLine1")
    public String addressLine1;

    @Column(name = "city")
    public String city;

    @Column(name = "pincode")
    public int pincode;

    @OneToOne(mappedBy = "address") // optional to make this field, as owner table will take care of mapping
    public Person person;

    public Address(String addressLine1, String city, int pincode) {
        this.addressLine1 = addressLine1;
        this.city = city;
        this.pincode = pincode;
    }
}
