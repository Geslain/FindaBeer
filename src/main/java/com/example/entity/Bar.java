package com.example.entity;


import javax.persistence.*;
import java.awt.print.Book;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bars")
public class Bar {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private short zipCode;

    @Column(nullable = false)
    private String city;

    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "beers_bars",
            joinColumns = @JoinColumn(name = "bar_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "beer_id", referencedColumnName = "id"))
    private Set<Beer> beers = new HashSet<Beer>();

    public Bar() {
    }

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

    public short getZipCode() {
        return zipCode;
    }

    public void setZipCode(short zipCode) {
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

    public void addBeer(Beer beer) {
        this.beers.add(beer);
    }

    @Override
    public String toString() {
        String result = String.format(
                "Bar [id=%d, name='%s']%n",
                id, name);
        if (beers != null) {
            for(Beer beer : beers) {
                result += String.format(
                        "Beer [id=%d, brand='%s']%n",
                        beer.getId(), beer.getBrand());
            }
        }

        return result;
    }
}
