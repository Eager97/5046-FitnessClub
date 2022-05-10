package com.example.fitnessclub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.example.fitnessclub.databinding.ActivityMainBinding;
import com.example.fitnessclub.databinding.ActivitySignupscreenBinding;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.appBar.toolbar);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_main_activity,
                R.id.nav_new_training_activity)
                .setOpenableLayout(binding.drawerLayout)
                .build();

        FragmentManager fragmentManager= getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment)
                fragmentManager.findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        //Sets up a NavigationView for use with a NavController.
        NavigationUI.setupWithNavController(binding.navView, navController);
        //Sets up a Toolbar for use with a NavController.
        NavigationUI.setupWithNavController(binding.appBar.toolbar,navController,
                mAppBarConfiguration);


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
                binding.tempTextView.setText("Temperature: "+String.valueOf((int)temp)+" °");
                String name = root.getName();
                binding.locateNameTextView.setText(name);
                ArrayList<Weather> weathers = root.getWeather();

                //binding.weatherDescriptionTextview.setText(weatherDescription);

            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });



    }
}


//https://api.openweathermap.org/data/2.5/weather?lat=-37.813629&lon=144.963058&appid=74e5dc2396026a727a8c76abf881f408