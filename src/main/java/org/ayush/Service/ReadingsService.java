package org.ayush.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.ayush.Entity.Readings;

public interface ReadingsService {

    List<Readings> findAll();

    Readings findOne(String id);

    List<List<Double>> findLocation30Minutes(String vin);

    Readings create(Readings readings) throws ParseException;

    Readings update(Readings readings);

    void delete(String id);

}
