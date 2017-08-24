package com.example.alpin.makeup;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.alpin.makeup.base.Api;
import com.example.alpin.makeup.model.Eyebrow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    ArrayList<Eyebrow> list;
    AdapterEyebrow adapter;
    RecyclerView rv;
    SwipeRefreshLayout swipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv_eyebrow);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swip_eye);


        list = new ArrayList<>();

        if(Eyebrow.getAll().size() == 0){
            viewEyebrow("eyebrow");
            adapter = new AdapterEyebrow(this,list);
        }else{
            adapter = new AdapterEyebrow(this,Eyebrow.getAll());
        }


        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.addItemDecoration(new SpacesItemDecoration(this, R.dimen.space_5));
        rv.setAdapter(adapter);

        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(false);
                viewEyebrow("eyebrow");
            }
        });
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(false);
                adapter.clearData();
                viewEyebrow("eyebrow");
            }
        });

    }



    private void viewEyebrow(String productType) {

        retrofit2.Call<ResponseBody> call = Api.getService(true).getEyebrow(productType);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String body = null;
                try {
                    body = response.body().string();
                    if (response.isSuccessful()) {
                        try {
                            JSONArray eye = new JSONArray(body);
                            for (int i = 0; i < eye.length(); i++) {
                                JSONObject results = eye.getJSONObject(i);
                                String brand = results.getString("brand");
                                String name = results.getString("name");
                                String img = results.getString("image_link");
                                String price = results.getString("price");

                                list.add(new Eyebrow(brand,name,price,img));
                                Eyebrow eyebrow = new Eyebrow(brand,name,price,img);
                                eyebrow.save();

                                adapter.updateData(list);
                                Log.d(TAG, "onResponse: "+eyebrow.getName());
                            }

                            Log.d(TAG, "onResponse: " + body);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
