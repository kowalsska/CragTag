package com.example.magdakowalska.climbmap;

import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * Created by magdakowalska on 18/03/15.
 */
public class JsonUtil{


    public static String toJson(Crag crag){
        JSONObject jsonCrag = new JSONObject();
        try {
            jsonCrag.put("name", crag.getName());
            jsonCrag.put("rocktype", crag.getRocktype());
            jsonCrag.put("faces", crag.getFaces());
            jsonCrag.put("latitude", crag.getLatitude());
            jsonCrag.put("longitutde", crag.getLongitude());
            jsonCrag.put("climbs", crag.getClimbs());
            jsonCrag.put("photos", crag.getPhotos());

            JSONArray jsonCragArray = new JSONArray();
            jsonCragArray.put(jsonCrag);

            return jsonCrag.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
