package com.example.alpin.makeup.base;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.example.alpin.makeup.model.Eyebrow;


/**
 * Created by sunari on 06/01/17.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Configuration.Builder confiBuilder = new Configuration.Builder(this);
        confiBuilder.addModelClasses(Eyebrow.class);
        ActiveAndroid.initialize(this);

    }
}
