package com.example.magdakowalska.climbmap;

import android.content.Intent;
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
    private Button cragToMap;
    public int cragIndex;
    private ArrayList<HashMap<String, Object>> defaultCragLocationList;
    private Locations locations = new Locations(this);

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
        cragToMap = (Button) findViewById(R.id.cragToMap);

        locations.jsonToArraylist();
        defaultCragLocationList = locations.getDefaultCragLocationList();

        Intent intent = this.getIntent();
        if(intent != null) {
            cragIndex = intent.getExtras().getInt("markerIndex");
        }

        HashMap<String, Object> cragToShow = defaultCragLocationList.get(cragIndex);

        name.setText((String)cragToShow.get("name"));
        rocktype.setText((String)cragToShow.get("rocktype"));
        faces.setText((String)cragToShow.get("faces"));
        location.setText((String)cragToShow.get("location").toString());
        description.setText((String)cragToShow.get("description"));



        cragToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MapsActivity.class);
                i.putExtra("methodname","zoomInToLocation");
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
