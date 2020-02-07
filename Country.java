
/**
 * Gives stats about countries of the world
 *
 * @author Jon Rabideau
 * @version 1.0.0
 */

import java.util.*;
import java.text.DecimalFormat;
public class Country implements Comparable{
    private String country;
    private String continent;
    private int area;
    private int population;
    private double gdp;
    private String capital;
    private String pattern = "###,###,###,###,###,###";
    DecimalFormat dFormat = new DecimalFormat(pattern);
    
    
    /**
     * compares countries based on gdp
     * @return difference in country gdp
     */
    public int compareTo(Object other){
        Country c = (Country) other;
        return (int)(c.gdp - gdp);
    }

    /**
     * default constructor
     */
    public Country(){
        country = "United States";
        continent = "North America";
        capital = "Washington D.C.";
    }

    /**
     * constructor that takes in country name, continent, area, pop, gdp, and capital
     */
    public Country (String name, String continent, int area, int population, 
    double gdp, String capital){
        country = name;
        this.continent = continent;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
        this.capital = capital;
    }

    /**
     * @return country name
     */
    public String getCountry(){
        return country;
    }

    /**
     * @return continent of country
     */
    public String getContinent(){
        return continent;
    }

    /**
     * @return are of country in km squared
     */
    public int getArea(){
        return area;
    }

    /**
     * @return population of country
     */
    public int getPopulation(){
        return population;
    }

    /**
     * @return gross domestic product of country
     */
    public double getGDP(){
        return gdp;
    }

    /**
     * @return capital of country
     */
    public String getCapital(){
        return capital;
    }

    /**
     * sets name of country to value n
     */
    public void setCountry(String n){
        country = n;
    }

    /**
     * sets continent to value c
     */
    public void setContinent(String c){
        continent = c;
    }

    /**
     * sets area of country to value a
     */
    public void setArea(int a){
        area = a;
    }

    /**
     * sets population of country to value p
     */
    public void setPopulation(int p){
        population = p;
    }

    /**
     * sets gdp of country to value g
     */
    public void setGDP(double g){
        gdp = g;
    }

    /**
     * sets capital of country to value c
     */
    public void setCapital(String c){
        capital = c;
    }
    
    /**
     * @return string form of country name, capital, gdp, and per capita gdp
     */
    public String toString(){
        return country + ", Capital: " + capital + ", GDP: "
        + dFormat.format(gdp/1000000000) + " billion, Per Capita GDP: " + dFormat.format(gdp/population);
    }
}
