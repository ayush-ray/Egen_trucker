package org.ayush.Rules;

import org.ayush.Entity.Readings;
import org.ayush.Entity.Vehicles;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "fuelVolume")
public class fuelVolume {
    @Condition
    public boolean when(Readings readings, Vehicles vehicles){
        if(readings.getFuelVolume() < (0.1*vehicles.getMaxFuelVolume()))
            return true;
        else
            return false;
    }
    @Action
    public void then() throws Exception{
        System.out.println("Fuel Volume is LOW");
    }
}
