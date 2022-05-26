package model.entities;

import java.util.ArrayList;

public class Company implements HavingReactors{

    private int id;
    private String name;
    private ArrayList<Reactor> reactors;

    public Company(int id, String name){
        this(id, name, new ArrayList<>());
    }

    public Company(int id, String name, ArrayList<Reactor> reactors){
        this.id = id;
        this.name = name;
        this.reactors = reactors;
    }

    // SET

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReactors(ArrayList<Reactor> reactors) {
        this.reactors = reactors;
    }

    // GET

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Reactor> getReactors() {
        return reactors;
    }

    //

    @Override
    public String toString() {
        return "\nCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reactors=" + reactors +
                '}';
    }

}
