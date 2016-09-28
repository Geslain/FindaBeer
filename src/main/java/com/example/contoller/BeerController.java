package com.example.contoller;

import com.example.entity.Beer;
import com.example.repository.BeerRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/beer", method = RequestMethod.GET)
public class BeerController {

    @Autowired
    private BeerRepository repository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.GET, produces = "application/json")
    public Beer getBeerAction(ModelMap model,@PathVariable("id") long id) {
        Beer beer = repository.findOne(id);
        return beer;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes="application/json", headers = "content-type=application/x-www-form-urlencoded")
    public  @ResponseBody Beer addBeerAction(HttpServletRequest request) {
        Beer beer = new Beer(request.getParameter("brand"));
        repository.save(beer);
        return beer;
    }

//    @RequestMapping(value = "//{id:[\\d]+}", method = RequestMethod.DELETE,produces = "application/json")
//    public  @ResponseBody String deleteBeerAction(HttpServletRequest request) {
//        repository.save(beer);
//        return "Success";
//    }
}
