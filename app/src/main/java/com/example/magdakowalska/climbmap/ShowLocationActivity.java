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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class ShowLocationActivity extends ActionBarActivity {

    private TextView name;
    private TextView rocktype;
    private TextView faces;
    private TextView location;
    private TextView description;
    private ImageView photo1, photo2, photo3;
    private Button showClimbs;
    public int cragIndex;
    private ArrayList<HashMap<String, Object>> defaultCragLocationList;
    private Locations locations = new Locations();
    private HashMap<String, Object> cragToShow;
    public static MyApplication ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_location);

        name = (TextView) findViewById(R.id.nameCrag);
        rocktype = (TextView) findViewById(R.id.textView3);
        faces = (TextView) findViewById(R.id.textView5);
        location = (TextView) findViewById(R.id.textView7);
        description = (TextView) findViewById(R.id.textView9);
        photo1 = (ImageView) findViewById(R.id.photo1);
        photo2 = (ImageView) findViewById(R.id.photo2);
        photo3 = (ImageView) findViewById(R.id.photo3);
        showClimbs = (Button) findViewById(R.id.showClimbs);

        Context c = ma.getInstance();

        SharedPreferences prefs = c.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String jsonStringCrags = prefs.getString("cragsStringFromJSON", null);

        locations.jsonToArraylist(jsonStringCrags);
        defaultCragLocationList = locations.getDefaultCragLocationList();

        Intent intent = this.getIntent();
        if(intent != null) {
            cragIndex = intent.getExtras().getInt("markerIndex");
        }



        cragToShow = defaultCragLocationList.get(cragIndex);

        name.setText((String)cragToShow.get("name"));
        rocktype.setText((String)cragToShow.get("rocktype"));
        faces.setText((String)cragToShow.get("faces"));
        String locationString = (String)cragToShow.get("latitude").toString() + "\n" + (String)cragToShow.get("longitude").toString();
        location.setText((String)cragToShow.get("location").toString().substring(8));
        description.setText((String)cragToShow.get("description"));



        showClimbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ClimbListActivity.class);
                i.putExtra("cragIndex", cragIndex);
                startActivity(i);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_location, menu);
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
