package com.example.fitnessclub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import android.os.Bundle;
import android.view.View;

import com.example.fitnessclub.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;


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

        // set navigation drawer
        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_main_activity, R.id.nav_new_training_activity)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // retrofit to use weather API
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