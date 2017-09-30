package com.example.rechee.meowfest;

import com.example.rechee.meowfest.http.CatClient;
import com.example.rechee.meowfest.models.Cat;
import com.example.rechee.meowfest.models.CatListener;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rechee on 9/30/2017.
 */

class CatViewModel {
    private final CatListener listener;
    private final CatClient catClient;
    private String BASE_URL = "https://chex-triplebyte.herokuapp.com/";

    CatViewModel(CatListener listener){
        this.listener = listener;

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();

        this.catClient = retrofit.create(CatClient.class);

    }

    void getCats(int page){
        Call<List<Cat>> call = catClient.catsForPage(page);

        call.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                if(response.isSuccessful()){
                    listener.catsReceived(response.body());
                }
                else{
                    listener.catRetrieveFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                listener.catRetrieveFailure(t.getMessage());
            }
        });
    }
}
