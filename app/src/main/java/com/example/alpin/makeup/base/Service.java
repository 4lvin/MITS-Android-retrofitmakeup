package com.example.alpin.makeup.base;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alpin on 20/08/17.
 */

public interface Service {


    @GET("api/v1/products.json")
    Call<ResponseBody> getEyebrow(@Query("product_type") String product_type);

}
