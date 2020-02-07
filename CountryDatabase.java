
/**
 * functions to help find info about countries
 *
 * @author Jon Rabideau
 * @version 1.0.0
 */

import java.util.*;
import java.io.IOException;
import java.io.FileInputStream;
import java.lang.String;
public class CountryDatabase{
    private ArrayList<Country> worldCountries;

    /**
     * database constructor
     */
    public CountryDatabase(){
        worldCountries = new ArrayList<Country>();
    }
    
    /**
     * reads a file and puts country info in appropriate variable
     * @param filename
     */
    public void readCountriesData(String filename){
        // Read the full set of data from a text file
        try{
            // open the text file and use a Scanner to read the text
            FileInputStream fileByteStream = new FileInputStream(filename);
            Scanner scnr = new Scanner(fileByteStream);
            scnr.useDelimiter("[,\r\n]+");

            // keep reading as long as there is more data
            while(scnr.hasNext()) {
                // reading the fields from the record one at the time
                String name = scnr.next();
                String continent = scnr.next();
                int area = scnr.nextInt();
                int population = scnr.nextInt();
                double gdp = scnr.nextDouble();
                String capital = scnr.next();

                Country countryX = new Country(name, continent, area, population, gdp, capital);

                worldCountries.add(countryX);

                // FIX ME: capture the area, population, GDP, and capital
                // FIX ME: instantiate a new Country with the data that was read
                // FIX ME: Add the Country created on previous instruction
                // to the ArrayList
            }
            fileByteStream.close();
        }
        catch(IOException e) {
            System.out.println("Failed to read the data file: " + filename);
        }

    }
    
    /**
     * @return number of countries
     */
    public int countAllCountries(){
        return worldCountries.size();
    }
    
    /**
     * @return list of all countries
     */
    public ArrayList<Country> getAllCountries(){
        return worldCountries;
    }
    
    /**
     * @return country with the highest gdp in specified continent
     * @param name of continent
     */
    public Country highestGdp(String continent){
        double maxGDP = 0;
        Country highest = new Country();
        for(Country c : worldCountries){
            if(c.getContinent().equalsIgnoreCase(continent)){
                if(c.getGDP() > maxGDP){
                    maxGDP = c.getGDP();
                    highest = c;
                }
            } 
        }
        return highest;
    }
    
    /**
     * @return country with the smallest area in the specified continent
     * @param name of continent
     */
    public Country smallestArea(String continent){
        int minArea = 999999999;
        Country smallest = new Country();
        for(Country c : worldCountries){
            if(c.getContinent().equalsIgnoreCase(continent)){
                if(c.getArea() < minArea){
                    minArea = c.getArea();
                    smallest = c;
                }
            }
        }
        return smallest;
    }
    
    /**
     * @return name of the capital of specified country
     * @param name of country
     */
    public String searchForCapital(String countryName){
        for(Country c : worldCountries){
            if(c.getCountry().equalsIgnoreCase(countryName)){
                return c.getCapital();
            }
        }
        return null;
    }
    
    /**
     * @return details about specified country
     * @param name of country
     */
    public Country findCountryDetails(String countryName){
        for(Country c : worldCountries){
            if(c.getCountry().equalsIgnoreCase(countryName)){
                return c;
            }
        }
        return null;
    }
    
    /**
     * @return arraylist of all the countries in a specified continent
     * @param name of continent
     */
    public ArrayList<Country> searchCountriesInContinent(String continent){
        ArrayList<Country> continentCountries = new ArrayList<Country>();
        for(Country c : worldCountries){
            if(c.getContinent().equalsIgnoreCase(continent)){
                continentCountries.add(c);
            }
        }
        Collections.sort(continentCountries);
        return continentCountries;
    }
    
    /**
     * arraylist of all the countries with a population >= specified pop
     * @param population to compare against
     */
    public ArrayList<Country> searchCountriesWithPopulation(int population){
        ArrayList<Country> populationCountries = new ArrayList<Country>();
        for(Country c : worldCountries){
            if(c.getPopulation() > population){
                populationCountries.add(c); 
            }
        }
        return populationCountries;
    }
    
    /**
     * @return arraylist of top ten countries in specified continent based on gdp
     * @param name of continent
     */
    public ArrayList<Country> topTenGdpCountries(String continent){
        ArrayList<Country> topTenGDP = new ArrayList<Country>();
        ArrayList<Country> temp = new ArrayList<Country>();
        temp = searchCountriesInContinent(continent);
        for(int i = 0; i < 10; ++i){
            topTenGDP.add(temp.get(i));
        }
        return topTenGDP;
    }

    public static void main(String[] args){
        CountryDatabase myDB = new CountryDatabase();
        myDB.readCountriesData("Countries.CSV");
        System.out.println(myDB.getAllCountries());
        System.out.println(myDB.highestGdp("Africa"));
    }
}
