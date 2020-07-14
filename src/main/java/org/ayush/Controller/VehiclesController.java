package org.ayush.Controller;

import java.util.List;

import org.ayush.Entity.Vehicles;
import org.ayush.Service.VehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/vehicles")
public class VehiclesController {

    @Autowired
    VehiclesService service;

    @GetMapping
    public List<Vehicles> findAll() {
        return service.findAll();
    }

    @GetMapping("/{vin}")
    public Vehicles findOne(@PathVariable("vin") String vin) {
        return service.findOne(vin);
    }

    @PostMapping
    public Vehicles create(@RequestBody Vehicles vehicles) {
        return service.create(vehicles);
    }

    @PutMapping
    public void update(@RequestBody List<Vehicles> vehicles) {
        service.update(vehicles);
    }

    @DeleteMapping("/{vin}")
    public void delete(@PathVariable("vin") String vin) {
        service.delete(vin);
    }
}