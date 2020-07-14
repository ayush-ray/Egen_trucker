package org.ayush.Rules;

import org.ayush.Entity.Readings;
import org.ayush.Entity.Vehicles;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;

import java.util.Optional;

@Rule(name = "engineCoolant")
public class engineCoolant {
    @Condition
    public boolean when(Readings readings, Vehicles vehicles){
        return readings.getEngineCoolantLow() || readings.getCheckEngineLightOn();
    }
    @Action
    public void then() throws Exception{
        System.out.println("Either engine coolant is LOW or check engine light is ON");
    }
}
