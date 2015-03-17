package com.example.magdakowalska.climbmap;

import android.content.Context;
import android.content.Intent;
import android.location.*;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;


public class AddClimbActivity extends ActionBarActivity {

    private EditText spotNameBox;
    private String spotName;
    private Button photoButton;
    private Button descriptionButton;
    private Switch putOnMapSwitch;
    private DatePicker datePicker;
    private int year;
    private int month;
    private int day;
    private Button confirmButton;

    private ArrayList<HashMap<String, String[]>> locations =
            new ArrayList<HashMap<String, String[]>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_climb);

        spotNameBox = (EditText) findViewById(R.id.nameBox);
        photoButton = (Button) findViewById(R.id.button2);
        descriptionButton = (Button) findViewById(R.id.button3);
        putOnMapSwitch = (Switch) findViewById(R.id.mapLocationSwitch);
        datePicker = (DatePicker) findViewById(R.id.datePicker2);
        confirmButton = (Button) findViewById(R.id.button4);

        spotName = spotNameBox.getText().toString();

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(i, 1);

            }
        });

        putOnMapSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // Get LocationManager object from System Service LOCATION_SERVICE
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                    // Create a criteria object to retrieve provider
                    Criteria criteria = new Criteria();

                    // Get the name of the best provider
                    String provider = locationManager.getBestProvider(criteria, true);

                    // Get Current Location
                    android.location.Location myLocation = locationManager.getLastKnownLocation(provider);

                    // Get latitude of the current location
                    double latitude = myLocation.getLatitude();

                    // Get longitude of the current location
                    double longitude = myLocation.getLongitude();

                    // Create a LatLng object for the current location
                    LatLng latLng = new LatLng(latitude, longitude);
                }
            }
        });
    }

    public void onDateChanged (DatePicker datePicker, int year, int monthOfYear, int dayOfMonth){
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
