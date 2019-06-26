package com.examples.andriodrest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/characters/")
    Call<List<SWCharacter>> getCharacters();

}
