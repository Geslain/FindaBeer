package com.example.contoller;

import com.example.entity.Bar;
import com.example.entity.Beer;
import com.example.entity.BeerByBar;
import com.example.repository.BarRepository;
import com.example.repository.BeerByBarRepository;
import com.example.repository.BeerRepository;
import javassist.NotFoundException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/beerbybar")
public class BeerByBarController {

    @Autowired
    private BarRepository barRepository;

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private BeerByBarRepository beerbybarRepository;

    // Logger
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static String getNotFoundMessage(String entity) {
        return "{\"code\": 404, \"message\" : \""+entity+" not found\" }";
    }
}
