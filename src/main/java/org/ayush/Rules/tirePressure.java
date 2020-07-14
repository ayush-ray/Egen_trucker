package org.ayush.Rules;

import org.ayush.Entity.Readings;
import org.ayush.Entity.Vehicles;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;

import java.util.HashMap;

@Rule(name = "tirePressure")
public class tirePressure {
    @Condition
    public boolean when(Readings readings, Vehicles vehicles){
        HashMap<String, Integer> tires = readings.getTires();
        for ( String tire : tires.keySet()){
            if(tires.get(tire) <32 || tires.get(tire) >36)
                return true;
        }
        return false;
    }

    @Action
    public void then() throws Exception{
        System.out.println("Check Tire Pressure!!!");
    }
}
