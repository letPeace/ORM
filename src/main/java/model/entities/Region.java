package model.entities;

import java.util.ArrayList;
import java.util.Collections;

public class Region{

    private int id;
    private String name;
    private ArrayList<Country> countries;

    public Region(int id, String name){
        this(id, name, new ArrayList<>());
    }

    public Region(int id, String name, ArrayList<Country> countries){
        this.id = id;
        this.name = name;
        this.countries = countries;
    }

    // SET

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    // GET

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    //

    public void addCountries(Country... countries){
        Collections.addAll(getCountries(), countries);
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countries=" + countries +
                '}';
    }
}
