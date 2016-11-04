package com.example.repository;

import com.example.entity.BeerByBar;
import org.springframework.data.repository.CrudRepository;

public interface BeerByBarRepository extends CrudRepository<BeerByBar, Long>  {
}