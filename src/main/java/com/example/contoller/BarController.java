package com.example.contoller;

import com.example.entity.Bar;
import com.example.entity.Beer;
import com.example.repository.BarRepository;
import com.example.repository.BeerRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bar")
public class BarController {

    @Autowired
    private BarRepository barRepository;

    @Autowired
    private BeerRepository beerRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Bar> getAllBar(HttpServletRequest request) {
        return barRepository.findAll();
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.GET)
    public Bar getBarAction(@PathVariable("id") long id) {
        return barRepository.findOne(id);
    }

    @RequestMapping(value = "/{id:[\\d]+}/beer", method = RequestMethod.POST)
    public Bar addBeerForBarAction(@PathVariable("id") long id,@RequestBody Beer beer) {
        if(beerRepository.findOne(beer.getId())== null) {
            beerRepository.save(beer);
        }

        Bar bar = barRepository.findOne(id);
        bar.addBeer(beer);
        return barRepository.save(bar);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Bar createBarAction(@RequestBody Bar bar) {
        return barRepository.save(bar);
    }



    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.PUT)
    public Bar updateBarAction(@PathVariable(value = "id") long id ,@RequestBody Bar bar) {
        bar.setId(id);
        return barRepository.save(bar);
    }

    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.DELETE,produces = "application/json")
    public @ResponseBody String deleteBarAction(@PathVariable("id") long id) {
        if(barRepository.findOne(id) != null) {
            barRepository.delete(id);
            return "{\"message\": \"Success\"}";
        } else {
            return "{\"message\": \"Not found\"}";
        }
    }
}
