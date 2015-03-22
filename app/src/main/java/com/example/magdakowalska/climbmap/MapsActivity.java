package com.example.magdakowalska.climbmap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Marker locationMarker;
    private Map<Marker, Integer> allMarkersMap = new HashMap<>();
    private ArrayList<HashMap<String, Object>> defaultCragLocationList;
    private Locations locations = new Locations();
    public MyApplication ma;

    public Double newLat;
    public Double newLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Context c = ma.getInstance();

        SharedPreferences prefs = c.getSharedPreferences("myPrefs1", Context.MODE_PRIVATE);
        String jsonStringCrags = prefs.getString("cragsStringFromJSON1", null);

        //System.out.println(jsonStringCrags);

        locations.jsonToArraylist(jsonStringCrags);
        defaultCragLocationList = locations.getDefaultCragLocationList();

        setUpMapIfNeeded();


        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Add new crag here"));
                Intent i = new Intent();
                i.setClass(MapsActivity.this, AddCragActivity.class);
                newLat = latLng.latitude;
                newLng = latLng.longitude;
                i.putExtra("latitude", latLng.latitude);
                i.putExtra("longitude", latLng.longitude);
                //System.out.println("THIS PLACE HAVE LATITUDE: " + newLat + " AND LONGITUDE " + newLng);
                MapsActivity.this.startActivity(i);
            }
        });



    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        // Enable MyLocation Layer of Google Map
        mMap.setMyLocationEnabled(true);

        // Get LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Create a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Get the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Get Current Location
        Location myLocation = locationManager.getLastKnownLocation(provider);

        // set map type
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Get latitude of the current location
        double latitude = myLocation.getLatitude();

        // Get longitude of the current location
        double longitude = myLocation.getLongitude();

        // Create a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

        // Show the current location in Google Map
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Zoom in the Google Map
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));

        for(int i = 0; i < defaultCragLocationList.size(); i++){
            HashMap location = defaultCragLocationList.get(i);
            LatLng l = (LatLng)location.get("location");
            locationMarker = mMap.addMarker(new MarkerOptions()
                .position(l)
                .title((String)location.get("name"))
            );
            allMarkersMap.put(locationMarker, i);
        }

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent i = new Intent();
                if(!marker.getTitle().equals("Add new crag here")) {
                    i.setClass(MapsActivity.this, ShowLocationActivity.class);
                    int markerIndex = allMarkersMap.get(marker);
                    i.putExtra("markerIndex", markerIndex);
                    MapsActivity.this.startActivity(i);
                } else {
                    i.setClass(MapsActivity.this, AddCragActivity.class);
                    i.putExtra("latitude", newLat);
                    i.putExtra("longitude", newLng);
                    MapsActivity.this.startActivity(i);
                }
            }
        });
    }

}
