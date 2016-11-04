package com.example.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "beer_bar")
public class BeerBar implements Serializable {

    private Beer beer;
    private Bar bar;
    private double price;

    @Id
    @ManyToOne
    @JoinColumn(name="bar_id")
    @JsonBackReference
    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    @Id
    @ManyToOne
    @JoinColumn(name="beer_id")
    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int hashCode() {
        return (int)bar.getId() + (int)beer.getId();
    }
}
