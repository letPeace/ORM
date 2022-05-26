package model;

import model.database.Query;
import model.entities.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class Model{

    private String filePath;
    private ArrayList<Region> regions;
    private ArrayList<Country> countries;
    private ArrayList<Company> companies;
    private ArrayList<Reactor> reactors;

    public Model(){
        this("D:\\_Mehi\\6sem\\java\\lab4\\src\\main\\resources\\reactors.accdb");
    }

    public Model(String filePath){
        this.filePath = filePath;
        this.regions = new ArrayList<>();
        this.countries = new ArrayList<>();
        this.companies = new ArrayList<>();
        this.reactors = new ArrayList<>();
    }

    //

    public ArrayList<Region> getRegions() {
        return regions;
    }

    public void setRegions(ArrayList<Region> regions) {
        this.regions = regions;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }

    public ArrayList<Reactor> getReactors() {
        return reactors;
    }

    public void setReactors(ArrayList<Reactor> reactors) {
        this.reactors = reactors;
    }

    // parsing

    public void parse(){
        try{
            parseRegions();
            parseCountries();
            parseCompanies();
            parseReactors();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private void parseRegions() throws SQLException{
        setRegions(Query.getRegions());
    }

    private void parseCountries() throws SQLException{
        setCountries(Query.getCountries(getRegions()));
    }

    private void parseCompanies() throws SQLException{
        setCompanies(Query.getCompanies());
    }

    private void parseReactors() throws SQLException{
        setReactors(Query.getReactors(getCountries(), getCompanies()));
    }

    //

    public Region getRegionById(int id){
        for(Region region : getRegions()){
            if(region.getId() == id) return region;
        }
        throw new RuntimeException("Incorrect value of region's id -> "+id+"!");
    }

    public Company getCompanyById(int id){
        for(Company company : getCompanies()){
            if(company.getId() == id) return company;
        }
        throw new RuntimeException("Incorrect value of company's id -> "+id+"!");
    }

    public Country getCountryById(int id){
        for(Country country : getCountries()){
            if(country.getId() == id) return country;
        }
        throw new RuntimeException("Incorrect value of country's id -> "+id+"!");
    }

}
