package org.ayush.Service;

import java.util.List;

import org.ayush.Entity.Vehicles;

public interface VehiclesService {

    List<Vehicles> findAll();

    Vehicles findOne(String id);

    Vehicles create(Vehicles vehicles);

    void update(List<Vehicles> vehicles);

    void delete(String id);

}
