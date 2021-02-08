package sk.kosickaacademic.simon.examples.database;

public class Country {
    String name, code3, capital, continent;
    int area;

    public Country(String name, String code3, String capital, String continent, int area) {
        this.name = name;
        this.code3 = code3;
        this.capital = capital;
        this.continent = continent;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public String getCode3() {
        return code3;
    }

    public String getCapital() {
        return capital;
    }

    public String getContinent() {
        return continent;
    }

    public int getArea() {
        return area;
    }

}
