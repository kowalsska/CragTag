package com.example.magdakowalska.climbmap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;


public class AddClimbActivity extends ActionBarActivity {

    private String climbName;
    private String climbGrade;
    private String climbDescription;
    private String climbPhotoPath;
    private String climbType;

    private TextView nameBox;
    private TextView gradeBox;
    private TextView descriptionBox;
    private Button takePhoto;
    private RadioGroup radioGroup;

    private RadioButton trad;
    private RadioButton boulder;
    private RadioButton winter;
    private RadioButton sport;

    private Button confirmButton;



    private String jsonStringNewCrag;
    private SharedPreferences.Editor editor;

    public static MyApplication ma;

    public String cragName;

    public JSONArray jsonMainArray;
    public int indexCragToUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_climb);

        //LOADING SHARED PREFERENCES
        Context c = ma.getInstance();
        SharedPreferences prefs = c.getSharedPreferences("myPrefs1", Context.MODE_PRIVATE);
        jsonStringNewCrag = prefs.getString("cragsStringFromJSON1", null);
        editor = prefs.edit();
        editor.clear();

        nameBox = (TextView) findViewById(R.id.nameBox);
        gradeBox = (TextView) findViewById(R.id.climbGrade);
        descriptionBox = (TextView) findViewById(R.id.descriptionTextView);
        takePhoto = (Button) findViewById(R.id.photoButton);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup123);
        confirmButton = (Button) findViewById(R.id.button4);

        //RADIO BUTTONS
        trad = (RadioButton) findViewById(R.id.radio0);
        boulder = (RadioButton) findViewById(R.id.radio1);
        sport = (RadioButton) findViewById(R.id.radio2);
        winter = (RadioButton) findViewById(R.id.radio3);

        Intent intent = this.getIntent();
        if(intent != null) {
            cragName = intent.getExtras().getString("cragName");
        }

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(i, 1);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                climbName = nameBox.getText().toString();
                climbGrade = gradeBox.getText().toString();
                climbDescription = descriptionBox.getText().toString();

                climbType = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();




                JSONObject obj = null;
                try {
                    obj = new JSONObject(jsonStringNewCrag);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                jsonMainArray = null;
                try {
                    jsonMainArray = obj.getJSONArray("crags");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < jsonMainArray.length(); i++) {

                    JSONObject singleCrag = null;
                    try {
                        singleCrag = jsonMainArray.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        if(singleCrag.getString("name").equals(cragName)){
                            indexCragToUpdate = i;

                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                JSONObject newClimb = new JSONObject();
                JSONArray climbsArray = new JSONArray();
                try {
                    climbsArray = jsonMainArray.getJSONObject(indexCragToUpdate).getJSONArray("climbs");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    newClimb.put("name", climbName);
                    newClimb.put("grade", climbGrade);
                    newClimb.put("type", climbType);
                    newClimb.put("description", climbDescription);

                    climbsArray.put(newClimb);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                editor.putString("cragsStringFromJSON1", obj.toString());
                editor.apply();

                Toast.makeText(getApplicationContext(), "New climb added!", Toast.LENGTH_LONG).show();

                finish();


            }
        });


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
