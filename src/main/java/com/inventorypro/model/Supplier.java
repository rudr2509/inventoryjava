package com.inventorypro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity @Table(name="suppliers")
public class Supplier {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank private String name;
    private String email;
    private String phone;
    private String address;
    public Supplier(){}
    public Long getId(){return id;} public String getName(){return name;} public String getEmail(){return email;}
    public String getPhone(){return phone;} public String getAddress(){return address;}
    public void setName(String v){name=v;} public void setEmail(String v){email=v;} public void setPhone(String v){phone=v;} public void setAddress(String v){address=v;}
}
