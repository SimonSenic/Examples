package sk.kosickaacademic.simon.examples.database;

public class Monument {
    String name, country, city;
    int id;

    public Monument(String name, String country, String city, int id) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public int getId() {
        return id;
    }
}
