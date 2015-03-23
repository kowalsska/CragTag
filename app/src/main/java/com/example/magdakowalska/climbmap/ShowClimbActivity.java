package com.example.magdakowalska.climbmap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class ShowClimbActivity extends ActionBarActivity {

    private TextView name;
    private TextView grade;
    private TextView descr;
    private TextView type;
    private Button backButton;

    public static MyApplication ma;
    private Locations locations = new Locations();
    private HashMap climbToShow;
    public int climbIndex;
    public int cragIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_climb);

        name = (TextView) findViewById(R.id.nameClimb);
        grade = (TextView) findViewById(R.id.gradeDisplay);
        descr = (TextView) findViewById(R.id.descriptionView);
        type = (TextView) findViewById(R.id.typeDisplay);
        backButton = (Button) findViewById(R.id.backButton);

        Context c = ma.getInstance();

        SharedPreferences prefs = c.getSharedPreferences("myPrefs1", Context.MODE_PRIVATE);
        String jsonStringCrags = prefs.getString("cragsStringFromJSON1", null);

        locations.jsonToArraylist(jsonStringCrags);

        Intent intent = this.getIntent();
        if(intent != null) {
            climbIndex = intent.getExtras().getInt("climbIndex");
            cragIndex = intent.getExtras().getInt("cragIndex");
        }

        ArrayList climbsArray = (ArrayList)locations.getDefaultCragLocationList().get(cragIndex).get("climbs");
        climbToShow = (HashMap)climbsArray.get(climbIndex);

        name.setText((String)climbToShow.get("name"));
        grade.setText((String)climbToShow.get("grade"));
        type.setText((String)climbToShow.get("type"));
        descr.setText((String)climbToShow.get("description"));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_climb, menu);
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
