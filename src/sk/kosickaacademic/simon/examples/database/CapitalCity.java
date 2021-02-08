package sk.kosickaacademic.simon.examples.database;

public class CapitalCity {
    String name, country;
    int population;

    public CapitalCity(String name, String country, int population) {
        this.name = name;
        this.country = country;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }
}
