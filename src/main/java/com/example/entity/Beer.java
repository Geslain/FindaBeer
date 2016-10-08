package com.example.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "beers")
public class Beer {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String type;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "beers_bars",
            joinColumns = @JoinColumn(name = "beer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "bar_id", referencedColumnName = "id"))
    private Set<Bar> bars = new HashSet<Bar>();

    public Beer() {
    }

    public Beer(String brand) {
        this.brand = brand;
    }

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
}
