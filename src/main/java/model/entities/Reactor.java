package model.entities;

import model.sub.Calculations;

public class Reactor{

    // "status" is not needed due to that each reactor here is "in operation"
    private int id;
    private String name;
    private String type; // it means "class" in database
    private int loadFactor; // коэффициент использования установленной мощности [%]
    private int thermalCapacity; // тепловая мощность [МВт]
    private int burnup; // коэффициент выгорания [МВт*сут/т]
    private Float fuelConsumption; // объем ежегодного потребления топлива [т/год]

    public Reactor(int id, String name, String type, int loadFactor, int thermalCapacity, int burnup){
        this.id = id;
        this.name = name;
        this.type = type;
        this.loadFactor = loadFactor;
        this.thermalCapacity = thermalCapacity;
        this.burnup = burnup;
        this.fuelConsumption = Calculations.getFuelConsumption(this);
    }

    // SET

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLoadFactor(int loadFactor) {
        this.loadFactor = loadFactor;
    }

    public void setThermalCapacity(int thermalCapacity) {
        this.thermalCapacity = thermalCapacity;
    }

    public void setBurnup(int burnup) {
        this.burnup = burnup;
    }

    public void setFuelConsumption(Float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    // GET

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLoadFactor() {
        return loadFactor;
    }

    public int getThermalCapacity() {
        return thermalCapacity;
    }

    public int getBurnup() {
        return burnup;
    }

    public Float getFuelConsumption() {
        return fuelConsumption;
    }

    //

    @Override
    public String toString(){
        return "\n\tReactor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", loadFactor=" + loadFactor +
                ", thermalCapacity=" + thermalCapacity +
                ", burnup=" + burnup +
                ", fuelConsumption=" + fuelConsumption +
                "}";
    }

}
