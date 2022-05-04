package com.company.App;

public class WeatherResponse {

    public Coord coord;
    public Weather[] weather;
    public Main main;
    public String name;
    public Sys sys;
//    public String base;
//    public Integer visibility;
//    public Wind[] wind;
//    public Clouds clouds;
//    public Long dt;
//    public double timezone;
//    public Long id;
//    public Integer cod;
}

class Coord {
    public Double lon;
    public Double lat;
}
class Weather {
    public Integer id;
    public String main;
    public String description;
    public String icon;
}
class Main {
    public Double temp;
    public Double feels_like;
    public Double temp_min;
    public Double temp_max;
    public Double pressure;
    public Double humidity;
}
class Wind {
    public Double speed;
    public Integer deg;
    public Double gust;
}
class Clouds {
    public Integer all;
}
class Sys {
    public Integer type;
    public Long id;
    public String country;
    public Long sunrise;
    public Long sunset;
}