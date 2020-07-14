package org.ayush.Service;

import java.util.List;
import java.util.Optional;

import org.ayush.Entity.Vehicles;
import org.ayush.Exception.VehicleNotFoundException;
import org.ayush.Repository.VehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehiclesServiceImpl implements VehiclesService {

    @Autowired
    VehiclesRepository repository;

    @Transactional(readOnly = true)
    public List<Vehicles> findAll() {

        return (List<Vehicles>) repository.findAll();
    }

    @Transactional(readOnly = true)
    public Vehicles findOne(String vin) {
        Optional<Vehicles> vehicles = repository.findById(vin);
        if (!vehicles.isPresent())
            throw new VehicleNotFoundException("Vehicle with VIN number " + vin + " not found.");
        else
            return vehicles.get();
    }

    @Transactional
    public Vehicles create(Vehicles vehicles) {
        vehicles.setVin(vehicles.getVin());
        return repository.save(vehicles);
    }

    @Transactional
    public void update(List<Vehicles> vehicles) {
        for(Vehicles vehicle : vehicles) {
            vehicle.setVin(vehicle.getVin());
            repository.save(vehicle);
        }
    }

    @Transactional
    public void delete(String vin) {
        Optional<Vehicles> existing = repository.findById(vin);
        if(!existing.isPresent()){
            throw new VehicleNotFoundException("Vehicle with VIN number " + vin + " not found.");
        }
        repository.delete(existing.get());
    }
}
