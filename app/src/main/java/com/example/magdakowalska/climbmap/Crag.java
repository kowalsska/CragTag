package com.example.magdakowalska.climbmap;

import org.json.JSONArray;

/**
 * Created by magdakowalska on 18/03/15.
 */
public class Crag {

    private String name;
    private String rocktype;
    private String faces;
    private long latitude;
    private long longitude;
    private String description;
    private JSONArray climbs;
    private String[] photos;

    public Crag(String name, String rocktype, String faces, long latitude, long longitude,
                String description, JSONArray climbs, String[] photos){
        this.name = name;
        this. rocktype = rocktype;
        this.faces = faces;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.climbs = climbs;
        this.photos = photos;
    }

    public String getName(){
        return name;
    }

    public String getRocktype(){
        return rocktype;
    }

    public String getFaces(){
        return faces;
    }

    public long getLatitude(){
        return latitude;
    }

    public long getLongitude(){
        return longitude;
    }

    public String getDescription(){
        return description;
    }

    public JSONArray getClimbs(){
        return climbs;
    }

    public String[] getPhotos(){
        return photos;
    }


}
