package sk.kosickaacademic.simon.examples.database;

public class City {
    String name, country, code3, district;
    int population;

    public City(String name, int population) {
        this.name = name;
        this.population = population;
    }

    public City(String name, String country, String district, int population) {
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCode3() {
        return code3;
    }

    public String getDistrict() {
        return district;
    }

    public int getPopulation() {
        return population;
    }
}
