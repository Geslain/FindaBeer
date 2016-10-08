package com.example.contoller;

import com.example.entity.Bar;
import com.example.entity.Beer;
import com.example.repository.BarRepository;
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
@RequestMapping(value = "/bars")
public class BarController {

    @Autowired
    private BarRepository barRepository;

    @Autowired
    private BeerRepository beerRepository;

    // Logger
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    // Bar routes

    /**
     * Get the list of all bars
     *
     * @param request
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Bar>> getAllBar(HttpServletRequest request) {
        return new ResponseEntity<>(barRepository.findAll(),HttpStatus.OK);
    }

    /**
     * Get a bar by id
     *
     * @param id
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.GET)
    public ResponseEntity getBarAction(@PathVariable("id") long id) {
        Bar bar = barRepository.findOne(id);
        if( bar == null) {
            return new ResponseEntity<>(getNotFoundMessage("Bar"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> (bar,HttpStatus.OK);
    }


    /**
     *  Add a bar
     * @param  bar
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createBarAction(@RequestBody Bar bar) {
        return new ResponseEntity<>(barRepository.save(bar), HttpStatus.OK);
    }


    /**
     * Update a bar by id
     *
     * @param id
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBarAction(@PathVariable(value = "id") long id,@RequestBody Bar _bar) {
        Bar bar = barRepository.findOne(id);
        if(bar == null) return new ResponseEntity<>(getNotFoundMessage("Bar"), HttpStatus.NOT_FOUND);
        _bar.setId(id);
        return new ResponseEntity<>(barRepository.save(_bar), HttpStatus.OK);
    }

    /**
     * Delete a bar by id
     * @param id
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody ResponseEntity<?> deleteBarAction(@PathVariable("id") long id) {
        if(barRepository.findOne(id) != null) {
            barRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(getNotFoundMessage("Bar"), HttpStatus.NOT_FOUND);
        }
    }

    // Beer by Bar routes

    /**
     * Get All beers for a bar
     *
     * @param id
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id:[\\d]+}/beers", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getBeerForBarAction(@PathVariable("id") long id) throws NotFoundException{
        Bar bar = barRepository.findOne(id);
        if(bar == null) return new ResponseEntity<>(getNotFoundMessage("Bar"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(bar.getBeers(), HttpStatus.OK);
    }

    /**
     * Add a new a beer and add it to a a bar
     *
     * @param id
     * @param beer
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id:[\\d]+}/beers", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> addBeerForBarAction(@PathVariable("id") long id,@RequestBody Beer beer) {
        if(beerRepository.findOne(beer.getId())== null) {
            beerRepository.save(beer);
        }

        Bar bar = barRepository.findOne(id);
        bar.addBeer(beer);
        return new ResponseEntity<>(barRepository.save(bar), HttpStatus.OK);
    }


    /**
     * Add an existing beer to a bar
     *
     * @param id
     * @param idBeer
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id:[\\d]+}/beers/{id_beer:[\\d]+}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> addBeerForBarAction(@PathVariable("id") long id,@PathVariable("id_beer") long idBeer) {
        Beer beer = beerRepository.findOne(idBeer);
        if(beer == null) {
            return new ResponseEntity<>(getNotFoundMessage("Beer"), HttpStatus.NOT_FOUND);
        }

        Bar bar = barRepository.findOne(id);
        bar.addBeer(beer);
        return new ResponseEntity<>(barRepository.save(bar), HttpStatus.OK);
    }

    /**
     * Delete an existing beer from a bar
     *
     * @param id
     * @param idBeer
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id:[\\d]+}/beers/{id_beer:[\\d]+}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> deleteBeerForBarAction(@PathVariable("id") long id,@PathVariable("id_beer") long idBeer) {
        Bar bar = barRepository.findOne(id);
        if(bar != null) {
            Beer beer = beerRepository.findOne(idBeer);
            if(beer == null) {
                return new ResponseEntity<>(getNotFoundMessage("Beer"), HttpStatus.NOT_FOUND);
            }
            bar.removeBeer(beer);
            return new ResponseEntity<Bar>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(getNotFoundMessage("Bar"), HttpStatus.NOT_FOUND);
        }
    }

    private static String getNotFoundMessage(String entity) {
        return "{\"code\": 404, \"message\" : \""+entity+" not found\" }";
    }
}
