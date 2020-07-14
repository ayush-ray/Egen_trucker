package org.ayush.Repository;

import org.ayush.Entity.Readings;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReadingsRepository extends CrudRepository<Readings, String> {
    List<Readings> findAllByVin(String vin);
}
