package org.ayush.Controller;

import org.ayush.Entity.Alert;
import org.ayush.Service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/alerts")
public class AlertController {


    private final AlertService alertService;

    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/{vin}")
    public List<Alert> findAllByVin(@PathVariable("vin") String vin){
        return alertService.findAllByVin(vin);
    }

    @GetMapping("/high")
    public HashMap<String,Integer> findHighPriority() throws ParseException {
        return alertService.findHighPriority();
    }
}
