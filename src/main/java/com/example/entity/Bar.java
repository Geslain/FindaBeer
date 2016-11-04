package com.example.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.awt.print.Book;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bar")
public class Bar implements Serializable{
    private long id;
    private String name;
    private String address;
    private int zipCode;
    private String city;
    private String description;
    private Set<BeerBar> beerBar = new HashSet<>();

    public Bar() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "bar")
    public Set<BeerBar> getBeerBar() {
        return beerBar;
    }

    public void setBeerBar(Set<BeerBar> beerBar) {
        this.beerBar = beerBar;
    }
}
