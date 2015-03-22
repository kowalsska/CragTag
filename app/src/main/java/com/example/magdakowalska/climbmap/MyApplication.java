package com.example.magdakowalska.climbmap;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by magdakowalska on 20/03/15.
 */
public class MyApplication extends Application{

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        SharedPreferences prefs = instance.getSharedPreferences("myPrefs1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        //editor.putString("cragsStringFromJSON1", loadJSONFromAsset());
        if(prefs.getString("cragsStringFromJSON1", null) == null) {
            editor.putString("cragsStringFromJSON1", loadJSONFromAsset());
        } else {
            System.out.println("data is already here");
        }
        editor.apply();


    }

    public static MyApplication getInstance() {
        return instance;
    }

    public String loadJSONFromAsset() {

        String json = null;

        try {

            InputStream is = getAssets().open("jsonCrags.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

}
