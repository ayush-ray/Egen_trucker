package org.ayush.Controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.ayush.Entity.Readings;
import org.ayush.Service.ReadingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/readings")
public class ReadingsController {

    @Autowired
    ReadingsService service;

    @GetMapping
    public List<Readings> findAll() {
        return service.findAll();
    }

    @GetMapping("/{vin}")
    public Readings findOne(@PathVariable("vin") String vin) {
        return service.findOne(vin);
    }

    @GetMapping("/track/{vin}")
    public List<List<Double>> location30Minutes(@PathVariable("vin") String vin) {
        return service.findLocation30Minutes(vin);
    }

    @PostMapping
    public Readings create(@RequestBody Readings readings) throws ParseException {
        return service.create(readings);
    }

    @PutMapping
    public Readings update(@RequestBody Readings readings) {
        return service.update(readings);
    }

    @DeleteMapping("/{vin}")
    public void delete(@PathVariable("vin") String vin) {
        service.delete(vin);
    }
}
