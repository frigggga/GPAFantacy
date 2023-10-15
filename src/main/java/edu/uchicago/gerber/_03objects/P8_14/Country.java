package edu.uchicago.gerber._03objects.P8_14;

import java.util.ArrayList;
import java.util.Collections;

class Country {
    private String name;
    private double population;
    private double area;

    public Country(String name, double population, double area) {
        this.name = name;
        this.population = population;
        this.area = area;
    }

    public String getName() {
        return this.name;
    }

    public double getPopulation() {
        return this.population;
    }

    public double getArea() {
        return this.area;
    }

    public double getDensity() {
        return this.population / this.area;
    }
}



