package com.example.contoller;

import com.example.entity.Beer;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/beer", method = RequestMethod.GET)
public class BeerController {

    @RequestMapping(value = "/{id:[\\d]+}", method = RequestMethod.GET, produces = "application/json")
    public String test(ModelMap model,@PathVariable("id") long id) {

        return "{\"succes\": ok}";
    }

}
