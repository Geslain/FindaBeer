package com.example.contoller;

import com.example.entity.Beer;
import com.example.repository.BeerRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/beers")
public class BeerController {

    @Autowired
    private BeerRepository repository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * Get all beers
     *
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Beer>> getAllBeer(HttpServletRequest request) {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    /**
     * Get a beer by id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getBeerAction(@PathVariable("id") long id) {
        Beer beer = repository.findOne(id);
        if(beer == null) {
            return new ResponseEntity<>(getNotFoundBeerMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }

    /**
     *  Add a new beer
     *
     * @param beer
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> createBeerAction(@RequestBody Beer beer) {
        return new ResponseEntity<>(repository.save(beer), HttpStatus.OK);
    }


    /**
     * Update a beer by id
     *
     * @param id
     * @param _beer
     * @return
     */
    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> updateBeerAction(@PathVariable(value = "id") long id ,@RequestBody Beer _beer) {
        Beer beer = repository.findOne(id);
        if(beer == null) {
            return new ResponseEntity<>(getNotFoundBeerMessage(), HttpStatus.NOT_FOUND);
        }
        _beer.setId(id);
        return new ResponseEntity<>(repository.save(_beer), HttpStatus.OK);
    }

    /**
     *
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.DELETE,produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> deleteBeerAction(@PathVariable("id") long id) {
        if(repository.findOne(id) != null) {
            repository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(getNotFoundBeerMessage(), HttpStatus.NOT_FOUND);
        }
    }

    private static String getNotFoundBeerMessage() {
        return "{\"code\": 404, \"message\" : \"Beer not found\" }";
    }
}
