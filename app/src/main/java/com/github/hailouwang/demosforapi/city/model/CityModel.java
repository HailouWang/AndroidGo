package com.github.hailouwang.demosforapi.city.model;
import com.github.hailouwang.demosforapi.city.bean.City;

public class CityModel {

    public static City createDefaultCity() {
        City city = new City();
        city.setCity_name("北京");
        city.setCity_en_name("Beijing");

        return city;
    }

    public static String getCityName(City city) {
        if (city == null) {
            return "";
        }

        return city.getCity_name();
    }

    public static String getCityEnName(City city) {
        if (city == null) {
            return "";
        }

        return city.getCity_en_name();
    }
}
