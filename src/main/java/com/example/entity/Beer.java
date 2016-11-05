package com.example.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "beer")
public class Beer implements Serializable{
    private long id;
    private String brand;
    private String origin;
    private String type;
    private Set<BeerBar> beerBar = new HashSet<>();

    public Beer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToMany(mappedBy = "beer",cascade = CascadeType.ALL)
    @JsonBackReference
    public Set<BeerBar> getBeerBar() {
        return beerBar;
    }

    public void setBeerBar(Set<BeerBar> beerBar) {
        this.beerBar = beerBar;
    }
}
