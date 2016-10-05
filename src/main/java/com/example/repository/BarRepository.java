package com.example.repository;


import com.example.entity.Bar;
import org.springframework.data.repository.CrudRepository;

public interface BarRepository extends CrudRepository<Bar, Long>  {
}