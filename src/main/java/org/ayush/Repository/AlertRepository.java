package org.ayush.Repository;

import org.ayush.Entity.Alert;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AlertRepository extends CrudRepository<Alert, String> {
    List<Alert> findAllByVin(String vin);
    List<Alert> findByPriority(String priority);
}
