package model.sub;

import model.entities.*;

public class Calculations{

    public static Float getFuelConsumption(Reactor reactor){
        return ((float)reactor.getThermalCapacity()*reactor.getLoadFactor()*365)/(reactor.getBurnup()*100000);
    }

    public static Float getFuelConsumption(Region region){
        Float generalFuelConsumption = 0f;
        for(Country country : region.getCountries()){
            generalFuelConsumption += getFuelConsumption(country);
        }
        return generalFuelConsumption;
    }

    public static Float getFuelConsumption(HavingReactors havingReactors){
        Float generalFuelConsumption = 0f;
        for(Reactor reactor : havingReactors.getReactors()){
            generalFuelConsumption += reactor.getFuelConsumption();
        }
        return generalFuelConsumption;
    }

}
