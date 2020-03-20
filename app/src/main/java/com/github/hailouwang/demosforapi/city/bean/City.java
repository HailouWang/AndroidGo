package com.github.hailouwang.demosforapi.city.bean;

public class City {
    private String city_name;

    private String city_en_name;

    public String getCity_name() {
        return city_name;
    }

    public City setCity_name(String city_name) {
        this.city_name = city_name;
        return this;
    }

    public String getCity_en_name() {
        return city_en_name;
    }

    public City setCity_en_name(String city_en_name) {
        this.city_en_name = city_en_name;
        return this;
    }
}
