package org.ayush.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.ayush.Entity.Alert;
import org.ayush.Entity.Readings;
import org.ayush.Entity.Vehicles;
import org.ayush.Exception.ReadingNotFoundException;
import org.ayush.Exception.VehicleNotFoundException;
import org.ayush.Repository.AlertRepository;
import org.ayush.Repository.ReadingsRepository;
import org.ayush.Repository.VehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReadingsServiceImpl implements ReadingsService {

    private final ReadingsRepository repository;
    private final VehiclesRepository vehiclesRepository;
    private final AlertRepository alertRepository;

    @Autowired
    public ReadingsServiceImpl(ReadingsRepository repository, VehiclesRepository vehiclesRepository, AlertRepository alertRepository) {
        this.repository = repository;
        this.vehiclesRepository = vehiclesRepository;
        this.alertRepository = alertRepository;
    }

    @Transactional(readOnly = true)
    public List<Readings> findAll() {
        return (List<Readings>) repository.findAll();
    }

    @Transactional(readOnly = true)
    public Readings findOne(String id) {
        Optional<Readings> readings = repository.findById(id);
        if (!readings.isPresent())
            throw new ReadingNotFoundException("Reading with id number " + id + " not found.");
        else
            return readings.get();
    }

    @Transactional(readOnly = true)
    public List<List<Double>> findLocation30Minutes(String vin){
        List<Readings> fetch = repository.findAllByVin(vin);
        List<List<Double>> map = new ArrayList<>();
        Timestamp currTime = new Timestamp(System.currentTimeMillis());
        for(Readings reading : fetch){
            long diff = currTime.getTime() - reading.getTimestamp().getTime();
            int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(diff);
            if(minutes>30)
                continue;
            List<Double> temp = new ArrayList<>();
            temp.add(reading.getLatitude());
            temp.add(reading.getLongitude());
            map.add(temp);
        }
        return map;
    }

    @Transactional
    public Readings create(Readings readings) throws ParseException {
        readings.setId(UUID.randomUUID().toString());
        Optional<Vehicles> existing = vehiclesRepository.findByVin(readings.getVin());
        if(!existing.isPresent()){
            throw new VehicleNotFoundException("Vehicle with VIN number " + readings.getVin() + " not found.");
        }
        Vehicles vehicle = existing.get();
        check(readings, vehicle);
        return repository.save(readings);
    }

    @Transactional
    public Readings update(Readings readings) {
        return repository.save(readings);
    }

    @Transactional
    public void delete(String id) {
        Optional<Readings> existing = repository.findById(id);
        if(!existing.isPresent()){
            throw new ReadingNotFoundException("Reading with id number " + id + " not found.");
        }
        repository.delete(existing.get());
    }

    @Transactional
    public void check(Readings readings, Vehicles vehicles) throws ParseException {
        if(readings.getEngineRpm() > vehicles.getRedlineRpm()){
            Alert alert = new Alert(readings.getVin(), "engineRPM", "HIGH", readings.getTimestamp());
            alertRepository.save(alert);
        }

        if (readings.getFuelVolume() < (0.1 * vehicles.getMaxFuelVolume())) {
            Alert alert = new Alert(readings.getVin(), "fuelVolume", "MEDIUM", readings.getTimestamp());
            alertRepository.save(alert);
        }
        if (readings.getEngineCoolantLow() || readings.getCheckEngineLightOn()) {
            Alert alert = new Alert(readings.getVin(), "engineCoolant", "LOW", readings.getTimestamp());
            alertRepository.save(alert);
        }
        HashMap<String, Integer> tires = readings.getTires();
        for (String tire : tires.keySet()) {
            if (tires.get(tire) < 32 || tires.get(tire) > 36) {
                Alert alert = new Alert(readings.getVin(), "tirePressure", "LOW", readings.getTimestamp());
                alertRepository.save(alert);
                break;
            }
        }
    }


}
