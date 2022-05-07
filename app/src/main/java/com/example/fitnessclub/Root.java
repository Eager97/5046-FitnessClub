package com.example.fitnessclub;

import java.util.ArrayList;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */


public class Root{
    public Coord coord;
    public ArrayList<Weather> weather;
    public String base;
    public Main main;
    public int visibility;
    public Wind wind;
    public Clouds clouds;
    public int dt;
    public Sys sys;
    public int timezone;
    public int id;
    public String name;
    public int cod;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main){
        this.main = main;
    }
}

class Clouds{
    public int all;
}

class Coord{
    public double lon;
    public double lat;
}

class Main{
    public double temp;
    public double feels_like;
    public double temp_min;
    public double temp_max;
    public int pressure;
    public int humidity;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp){
        this.temp = temp;
    }
}

class Sys{
    public int type;
    public int id;
    public String country;
    public int sunrise;
    public int sunset;
}

class Weather{
    public int id;
    public String main;
    public String description;
    public String icon;
}

class Wind{
    public double speed;
    public int deg;
}

