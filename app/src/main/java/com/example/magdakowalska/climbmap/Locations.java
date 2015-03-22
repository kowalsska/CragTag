package com.example.magdakowalska.climbmap;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Picture;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by magdakowalska on 1/03/15.
 */
public class Locations {

    private Context context;

    public static MyApplication ma;


    public ArrayList<HashMap<String, Object>> cragList;


    public void jsonToArraylist(String jsonString) {

        cragList = new ArrayList<HashMap<String, Object>>();

        JSONObject obj = null;
        try {
            obj = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray m_jArry = null;
        try {
            m_jArry = obj.getJSONArray("crags");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < m_jArry.length(); i++) {

            HashMap<String, Object> singleCrag;

            JSONObject jo_inside = null;
            try {
                jo_inside = m_jArry.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(jo_inside.toString());

            String crag_name = null;
            try {
                crag_name = jo_inside.getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String crag_rocktype = null;
            try {
                crag_rocktype = jo_inside.getString("rocktype");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String crag_faces = null;
            try {
                crag_faces = jo_inside.getString("faces");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            double crag_latitude = 0;
            try {
                crag_latitude = jo_inside.getDouble("latitude");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            double crag_longitude = 0;
            try {
                crag_longitude = jo_inside.getDouble("longitude");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            LatLng crag_location = new LatLng(crag_latitude, crag_longitude);

            String crag_description = null;
            try {
                crag_description = jo_inside.getString("description");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray crag_climbs = null;
            try {
                crag_climbs = jo_inside.getJSONArray("climbs");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            ArrayList<HashMap<String, String>> climbList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> singleClimb = new HashMap<String, String>();
            for (int j = 0; j < crag_climbs.length(); j++) {

                JSONObject aClimb = null;
                try {
                    aClimb = crag_climbs.getJSONObject(j);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String climb_name = null;
                try {
                    climb_name = aClimb.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String climb_grade = null;
                try {
                    climb_grade = aClimb.getString("grade");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String climb_description = null;
                try {
                    climb_description = aClimb.getString("description");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                singleClimb.put("name", climb_name);
                singleClimb.put("grade", climb_grade);
                singleClimb.put("description", climb_description);

                climbList.add(singleClimb);

            }

            singleCrag = new HashMap<String, Object>();
            singleCrag.put("name", crag_name);
            singleCrag.put("rocktype", crag_rocktype);
            singleCrag.put("faces", crag_faces);
            singleCrag.put("latitude", crag_latitude);
            singleCrag.put("longitude", crag_longitude);
            singleCrag.put("location", crag_location);
            singleCrag.put("description", crag_description);
            singleCrag.put("climbs", climbList);

            cragList.add(singleCrag);
        }

    }


    public ArrayList<HashMap<String, Object>> getDefaultCragLocationList() {
        System.out.println("number of crags in the arraylist: " + cragList.size());
        return cragList;
    }

    //TODO
    public void addCragLocation(String name, String rocktype, String faces,
                                String latitude, String longitude, String description,
                                ArrayList<HashMap<String, String>> climbs){
        //ADDING NEW ENTRY TO THE ARRAYLIST
    }
}
