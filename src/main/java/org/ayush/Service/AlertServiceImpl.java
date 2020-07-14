package org.ayush.Service;

import org.ayush.Entity.Alert;
import org.ayush.Repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.toMap;

@Service
public class AlertServiceImpl implements AlertService{

    AlertRepository alertRepository;

    @Autowired
    public AlertServiceImpl(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public List<Alert> findAllByVin(String vin){
        return alertRepository.findAllByVin(vin);
    }

    public HashMap<String,Integer> findHighPriority() throws ParseException {
        HashMap<String,Integer> result = new HashMap<String, Integer>();
        HashMap<String,Integer> results = new HashMap<String, Integer>();

        Iterable<Alert> alert_list = alertRepository.findByPriority("HIGH");
        for(Alert alert : alert_list){
            String vin = alert.getVin();
            if(result.containsKey(vin))
                result.put(vin, result.get(vin)+1);
            else
                result.put(vin, 1);
        }
        return result .entrySet() .stream() .sorted(Map.Entry.<String, Integer> comparingByValue().reversed()) .collect( toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
