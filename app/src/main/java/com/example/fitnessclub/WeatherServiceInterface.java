package com.example.fitnessclub;

import com.example.fitnessclub.model.Root;
import retrofit2.Call;
import retrofit2.http.GET;


//https://api.openweathermap.org/data/2.5/weather?lat=-37.813629&lon=144.963058&appid=74e5dc2396026a727a8c76abf881f408

public interface WeatherServiceInterface {

        @GET("weather?lat=-37.813629&lon=144.963058&appid=74e5dc2396026a727a8c76abf881f408")
        Call<Root> getWeatherState();

}
