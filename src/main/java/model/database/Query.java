package model.database;

import model.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Query{

    // Regions
    public static ArrayList<Region> getRegions(String tableName) throws SQLException{
        ArrayList<Region> regions = new ArrayList<>();
        Connector connector = new Connector();
        connector.connect();
        Connection connection = connector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from "+tableName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("region_name").trim();
            Region region = new Region(id, name);
            regions.add(region);
        }
        connector.disconnect();
        return regions;
    }

    public static ArrayList<Region> getRegions() throws SQLException{
        return getRegions("regions"); // ENUM
    }

    // Countries

    public static ArrayList<Country> getCountries(String tableName, ArrayList<Region> regions) throws SQLException{
        ArrayList<Country> countries = new ArrayList<>();
        Connector connector = new Connector();
        connector.connect();
        Connection connection = connector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from "+tableName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("country_name").trim();
            Country country = new Country(id, name);
            countries.add(country);
            //
            int regionId = resultSet.getInt("region_id");
            for(Region region : regions){
                if(region.getId() == regionId) region.addCountries(country);
            }
        }
        connector.disconnect();
        return countries;
    }

    public static ArrayList<Country> getCountries(ArrayList<Region> regions) throws SQLException{
        return getCountries("countries", regions);
    }

    // Companies

    public static ArrayList<Company> getCompanies(String tableName) throws SQLException{
        ArrayList<Company> companies = new ArrayList<>();
        Connector connector = new Connector();
        connector.connect();
        Connection connection = connector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from "+tableName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("companies_name").trim();
            Company company = new Company(id, name);
            companies.add(company);
        }
        connector.disconnect();
        return companies;
    }

    public static ArrayList<Company> getCompanies() throws SQLException{
        return getCompanies("companies");
    }

    // Reactors

    public static ArrayList<Reactor> getReactors(String tableName, ArrayList<Country> countries, ArrayList<Company> companies) throws SQLException{
        ArrayList<Reactor> reactors = new ArrayList<>();
        Connector connector = new Connector();
        connector.connect();
        Connection connection = connector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from "+tableName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            if(!resultSet.getString("status").trim().equals("in operation")) continue;
            int id = resultSet.getInt("id");
            int siteId = resultSet.getInt("site");
            if(siteId == 0) continue;
            String name = resultSet.getString("unit_name").trim();
            String type = resultSet.getString("class").trim();
            int loadFactor = resultSet.getInt("load_factor");
            if(loadFactor == 0) loadFactor = 90; // ENUMS FOR "CONSTANTS"
            int thermalCapacity = resultSet.getInt("thermal_capacity");
            //
            int burnup = 0;
                PreparedStatement preparedStatementBurnup = connection.prepareStatement("select * from units_forecasts where unit_id = "+id);
                ResultSet resultSetBurnup = preparedStatementBurnup.executeQuery();
                while(resultSetBurnup.next()){
                    burnup = resultSetBurnup.getInt("burnups");
                    if(burnup > 0) break;
                }
            if(burnup == 0){
                if(type.equals("CANDU")) burnup = 8;
                else if(type.equals("BN")) burnup = 100;
                else if(type.equals("CNP-1000")) burnup = 46;
                else if(type.equals("VVER-440") ||
                        type.equals("VVER-1000") ||
                        type.equals("PWR-900") ||
                        type.equals("PWR-1300") ||
                        type.equals("PWR-1500")) burnup = 45;
                else if(type.equals("BWR")) burnup = 40;
                else if(type.equals("ABWR")) burnup = 55;
                else if(type.equals("RBMK")) burnup = 25;
                else if(type.equals("AGR")) burnup = 30;
                else if(type.equals("MAGNOX")) burnup = 5;
                else burnup = 50; // average value of burnup -> ENUMS FOR "CONSTANTS"
            }
            Reactor reactor = new Reactor(id, name, type, loadFactor, thermalCapacity, burnup);
            reactors.add(reactor);
            //
            PreparedStatement preparedStatementSite = connection.prepareStatement("select * from sites where id = "+siteId);
            ResultSet resultSetSite = preparedStatementSite.executeQuery();
            resultSetSite.next();
            int countryId = resultSetSite.getInt("country");
            int companyId = resultSetSite.getInt("owner_id");
            for(Country country : countries){
                if(country.getId() == countryId) country.addReactors(reactor);
            }
            for(Company company : companies){
                if(company.getId() == companyId) company.addReactors(reactor);
            }
        }
        connector.disconnect();
        return reactors;
    }

    public static ArrayList<Reactor> getReactors(ArrayList<Country> countries, ArrayList<Company> companies) throws SQLException{
        return getReactors("units", countries, companies);
    }

}
