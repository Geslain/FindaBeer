package com.example.contoller;

import com.example.entity.Beer;
import com.example.repository.BeerRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/beer")
public class BeerController {

    @Autowired
    private BeerRepository repository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Beer> getAllBeer(HttpServletRequest request) {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.GET)
    public Beer getBeerAction(@PathVariable("id") long id) {
        return repository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Beer createBeerAction(@RequestBody Beer beer) {
        return repository.save(beer);
    }


    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.PUT)
    public Beer updateBeerAction(@PathVariable(value = "id") long id ,@RequestBody Beer beer) {
        beer.setId(id);
        return repository.save(beer);
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.DELETE,produces = "application/json")
    public @ResponseBody String deleteBeerAction(@PathVariable("id") long id) {
        if(repository.findOne(id) != null) {
            repository.delete(id);
            return "{\"message\": \"Success\"}";
        } else {
            return "{\"message\": \"Not found\"}";
        }
    }
}
