package view;

import controller.Controller;
import model.entities.Company;
import model.entities.Region;
import model.sub.Calculations;

public class View{

    private Controller controller;

    public static void main(String[] args){
        View gui = new View();
        gui.parseDatabase();
//        gui.printData();
        /*gui.printRegion(13);
        gui.printCountry(72);
        gui.printCompany(35);
        gui.printFuelConsumption("region", 13);
        gui.printFuelConsumption("country", 72);
        gui.printFuelConsumption("company", 35);*/
        gui.printFuelConsumptionCompanies();
        gui.printFuelConsumptionEachCompany();
    }

    public View(){
        this.controller = new Controller();
    }

    //

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    //

    public void parseDatabase(){
        getController().parseDatabase();
    }

    public void printData(){
        for(Region region : getController().getModel().getRegions()){
            System.out.println(region);
        }
    }

    public void printRegion(int id){
        System.out.println(getController().getRegionById(id));
    }

    public void printCountry(int id){
        System.out.println(getController().getCountryById(id));
    }

    public void printCompany(int id){
        System.out.println(getController().getCompanyById(id));
    }

    public void printReactor(int id) {
        System.out.println(getController().getReactorByID(id));
    }

    //

    public void printFuelConsumption(String entity, int id){
        System.out.println(getController().getFuelConsumption(entity, id));
    }

    public void printFuelConsumptionCompanies(){
        System.out.println("Fuel consumption for companies = "+getController().getFuelConsumptionCompanies());
    }

    public void printFuelConsumptionEachCompany(){
        for(Company company : getController().getModel().getCompanies()){
            System.out.println(company.getId()+" -> "+company.getName()+" -> "+Calculations.getFuelConsumption(company));
        }
    }

}
