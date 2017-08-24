package com.example.alpin.makeup.base;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by alpin on 20/08/17.
 */


public class Api {

    private static String BASE_URL = "http://makeup-api.herokuapp.com/";
    private static Retrofit retrofit;

    public static Retrofit getClient(boolean tiket) {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(new OkHttpClient())
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }

    public static Service getService(boolean tiket) {
        return getClient(tiket).create(Service.class);
    }
}
