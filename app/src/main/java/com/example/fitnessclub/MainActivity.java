package com.example.fitnessclub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.fitnessclub.databinding.ActivityMainBinding;
import com.example.fitnessclub.databinding.ActivitySignupscreenBinding;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create()) //convert to json format
                .build();

        WeatherServiceInterface weatherServiceInterface = retrofit.create(WeatherServiceInterface.class);

        Call<Root> call = weatherServiceInterface.getWeatherState();

        //
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root root = response.body(); //get root
                double temp = root.getMain().getTemp() - 273.15;
                binding.tempTextView.setText(String.valueOf((int)temp));
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });



    }
}


//https://api.openweathermap.org/data/2.5/weather?lat=-37.813629&lon=144.963058&appid=74e5dc2396026a727a8c76abf881f408