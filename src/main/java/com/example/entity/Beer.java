package com.example.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

/**
 * Created by Gess on 28/09/2016.
 */

@Entity
public class Beer {

    @Id
    @GeneratedValue
    protected long id;

    @Column(nullable = false)
    private String name;
}
