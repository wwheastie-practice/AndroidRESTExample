package com.examples.andriodrest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    final String TAG = "AndroidREST";
    Retrofit mRetrofit;
    List<SWCharacter> mSWCharacterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Retrofit object that will be used to instantiate created service interfaces
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://swgoh.gg/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Instantiate created web service
        ApiService apiService = mRetrofit.create(ApiService.class);

        //Create new call object that will handle/hold the response back from the web server/call
        Call<List<SWCharacter>> call = apiService.getCharacters();

        //Method handles response back from the web server/call
        call.enqueue(new Callback<List<SWCharacter>>() {
            @Override
            public void onResponse(Call<List<SWCharacter>> call, Response<List<SWCharacter>> response) {
                Log.d(TAG, "onResponse: successful");
                mSWCharacterList = response.body();
            }

            @Override
            public void onFailure(Call<List<SWCharacter>> call, Throwable t) {
                Log.d(TAG, "onFailure: failed");
            }
        });
    }
}
