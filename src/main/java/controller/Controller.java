package controller;

import model.Model;
import model.entities.Company;
import model.entities.Country;
import model.entities.Reactor;
import model.entities.Region;
import model.sub.Calculations;


public class Controller{

    private Model model;

    public Controller(){
        this.model = new Model();
    }

    //

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    //

    public void parseDatabase(){
        getModel().parse();
    }

    // calculate

    public String getFuelConsumption(String entity, int id){
        try{
            if(entity.equals("region")){
                Region region = getModel().getRegionById(id);
                return region.getName()+" -> "+Calculations.getFuelConsumption(region);
            }
            else if(entity.equals("country")){
                Country country = getModel().getCountryById(id);
                return country.getName()+" -> "+Calculations.getFuelConsumption(country);
            }
            else if(entity.equals("company")){
                Company company = getModel().getCompanyById(id);
                return company.getName()+" -> "+Calculations.getFuelConsumption(company);
            }
            else return "Incorrect value of entity!";
        } catch(RuntimeException e){
            return e.getMessage();
        }
    }

    public Float getFuelConsumptionCompanies(){
        Float generalFuelConsumption = 0f;
        for(Company company : getModel().getCompanies()){
            generalFuelConsumption += Calculations.getFuelConsumption(company);
        }
        return generalFuelConsumption;
    }

    // print

    public Model getData(){
        return getModel();
    }

    public Reactor getReactorByID(int id){
        for(Reactor reactor : getModel().getReactors()){
            if(reactor.getId() == id) {
                return reactor;
            }
        }
        throw new RuntimeException("No such reactor with id -> "+id);
    }

    public Company getCompanyById(int id){
        for(Company company : getModel().getCompanies()){
            if(company.getId() == id) {
                return company;
            }
        }
        throw new RuntimeException("No such company with id -> "+id);
    }

    public Country getCountryById(int id){
        for(Country country : getModel().getCountries()){
            if(country.getId() == id) {
                return country;
            }
        }
        throw new RuntimeException("No such country with id -> "+id);
    }

    public Region getRegionById(int id){
        for(Region region : getModel().getRegions()){
            if(region.getId() == id){
                return region;
            }
        }
        throw new RuntimeException("No such region with id -> "+id);
    }

}
