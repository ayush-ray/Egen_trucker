package org.ayush.Repository;

import org.ayush.Entity.Vehicles;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VehiclesRepository  extends CrudRepository<Vehicles, String> {
    Optional<Vehicles> findByVin(String vin);
}
