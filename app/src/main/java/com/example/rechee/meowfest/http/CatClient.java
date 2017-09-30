package com.example.rechee.meowfest.http;

import com.example.rechee.meowfest.models.Cat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rechee on 9/30/2017.
 */

public interface CatClient {
    @GET("/api/cats")
    Call<List<Cat>> catsForPage(@Query("page") int page);
}
