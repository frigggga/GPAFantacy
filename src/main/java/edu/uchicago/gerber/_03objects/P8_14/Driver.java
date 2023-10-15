package edu.uchicago.gerber._03objects.P8_14;

import java.util.ArrayList;
import java.util.Collections;

public class Driver {
        public static void main(String[] args) {
            ArrayList<Country> countries = new ArrayList<>();

            countries.add(new Country("aaa", 33.3, 100000));
            countries.add(new Country("bbb", 66, 6000));
            countries.add(new Country("ccc", 999, 880000000));

            Country area = Collections.max(countries, (c1, c2) -> Double.compare(c1.getArea(), c2.getArea()));
            System.out.println("Country that has the largest area: " + area.getName());

            Country pop = Collections.max(countries, (c1, c2) -> Double.compare(c1.getPopulation(), c2.getPopulation()));
            System.out.println("Country that has the largest population: " + pop.getName());

            Country density = Collections.max(countries, (c1, c2) -> Double.compare(c1.getDensity(), c2.getDensity()));
            System.out.println("Country that has the largest population density: " + density.getName());
        }
}
