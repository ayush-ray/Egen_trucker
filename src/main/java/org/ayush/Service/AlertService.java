package org.ayush.Service;

import org.ayush.Entity.Alert;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface AlertService {
    List<Alert> findAllByVin(String vin);
    HashMap<String,Integer> findHighPriority() throws ParseException;
}
