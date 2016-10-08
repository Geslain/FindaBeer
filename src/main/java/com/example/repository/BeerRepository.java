package com.example.repository;


import com.example.entity.Beer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BeerRepository extends CrudRepository< Beer, Long>  {
    List<Beer> findByBrand(String brand);
}