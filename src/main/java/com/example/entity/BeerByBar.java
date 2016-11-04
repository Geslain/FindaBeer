package com.example.entity;


import javax.persistence.*;
import java.awt.print.Book;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by Trusty on 02/11/2016.
 */
@Entity
@Table(name = "beerbybar")
public class BeerByBar {

    @Id
    @GeneratedValue
    private long id;

    @JoinTable(name="Beer",
            joinColumns = @JoinColumn(name = "id",
                    referencedColumnName = "beer_id"))
    @Column(nullable = false)
    private long id_beer;

    @JoinTable(name="Bar",
            joinColumns = @JoinColumn(name = "id",
                    referencedColumnName = "bar_id"))
    @Column(nullable = false)
    private long id_bar;

    @Column(nullable = false)
    private long price;

    public BeerByBar() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId_beer() {
        return id_beer;
    }

    public void setId_beer(long id) {
        this.id_beer = id_beer;
    }

    public long getId_bar() {
        return id_bar;
    }

    public void setId_bar(long id_bar) {
        this.id_bar = id_bar;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
