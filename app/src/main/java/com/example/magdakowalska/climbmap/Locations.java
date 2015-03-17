package com.example.magdakowalska.climbmap;

import android.graphics.Picture;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by magdakowalska on 1/03/15.
 */
public class Locations {

    private LatLng latLong;
    private String name;
    private Date someDate;
    private Picture photo;
    private String type;

    private ArrayList<HashMap<String, Object>> defaultCragLocationList = new ArrayList<HashMap<String, Object>>();

    public ArrayList<HashMap<String, Object>> getDefaultCragLocationList() {

        ArrayList<String> location1Climbs = new ArrayList<String>();
        ArrayList<String> location1Photos = new ArrayList<String>();
        location1Climbs.add("Undercut Wall");
        location1Climbs.add("Shield Bug");
        HashMap<String, Object> location1 = new HashMap<String, Object>();
        String data1 = "Limited but enjoyable climbing. Routes from 3 to Font 7a+.\n" +
                "Routes are slightly overgrown at top but the rock is generally sound. " +
                        "Quarry consists of main face and two side walls. " +
                        "The main face is roughly 8-9m high with several cracks, " +
                        "left wall has two chimney routes (4a and 5a) and the right " +
                        "wall offers various routes as well (though most are overgrown in summer). " +
                        "Bouldering is possible but hindered by rocky landing on some routes " +
                        "(plenty of mats and spotters needed). Graffiti on main wall suggests " +
                        "potential conflict of usage between climbers and local kids.";
        location1.put("Name", "Court Knowe Quarry");
        location1.put("Rocktype", "Dolerite");
        location1.put("Faces", "SW");
        location1.put("Location", new LatLng(55.81250,-4.25472));
        location1.put("Description", data1);
        location1.put("Climbs", location1Climbs);
        location1.put("Photos", location1Photos);
        defaultCragLocationList.add(location1);

        HashMap<String, Object> location2 = new HashMap<String, Object>();
        String data2 = "Limited but enjoyable climbing. Routes from 3 to Font 7a+.\n" +
                "Routes are slightly overgrown at top but the rock is generally sound. " +
                "Quarry consists of main face and two side walls. " +
                "The main face is roughly 8-9m high with several cracks, " +
                "left wall has two chimney routes (4a and 5a) and the right " +
                "wall offers various routes as well (though most are overgrown in summer). " +
                "Bouldering is possible but hindered by rocky landing on some routes " +
                "(plenty of mats and spotters needed). Graffiti on main wall suggests " +
                "potential conflict of usage between climbers and local kids.";
        location2.put("Name", "Slackdhu");
        location2.put("Rocktype", "Sandstone");
        location2.put("Faces", "NW");
        location2.put("Location", new LatLng(56.00508,-4.31517));
        location2.put("Description", data2);
        defaultCragLocationList.add(location2);

        HashMap<String, Object> location3 = new HashMap<String, Object>();
        String data3 = "Limited but enjoyable climbing. Routes from 3 to Font 7a+.\n" +
                "Routes are slightly overgrown at top but the rock is generally sound. " +
                "Quarry consists of main face and two side walls. " +
                "The main face is roughly 8-9m high with several cracks, " +
                "left wall has two chimney routes (4a and 5a) and the right " +
                "wall offers various routes as well (though most are overgrown in summer). " +
                "Bouldering is possible but hindered by rocky landing on some routes " +
                "(plenty of mats and spotters needed). Graffiti on main wall suggests " +
                "potential conflict of usage between climbers and local kids.";
        location3.put("Name", "Auchinstarry Quarry");
        location3.put("Rocktype", "Dolerite");
        location3.put("Faces", "S");
        location3.put("Location", new LatLng(55.96972,-4.05389));
        location3.put("Description", data3);
        defaultCragLocationList.add(location3);

        return defaultCragLocationList;
    }

    public void addCragLocation(String name, String rocktype, String direction, LatLng location, String description){

    }
}
