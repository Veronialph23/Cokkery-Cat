package com.example.page1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class MainActivity3 extends AppCompatActivity {

    TextView  descr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        descr = findViewById(R.id.textView7);
        String result = getIntent().getStringExtra("result");
        String fin ="";
        try {
            JSONObject root = new JSONObject(result);
            JSONArray user_data = root.getJSONArray("result");
            for (int i = 0; i < user_data.length(); i++) {
                JSONObject test = user_data.getJSONObject(i);
                Log.i(String.valueOf(i), String.valueOf(test));
                fin+="\nDish Name: "+test.getString("dish_name")+"\n Cuisine: "+test.getString("cuisine")+"\n Time Required: "+test.getString("time_required")+"\n Main Ingredients: "+test.getString("ingredients")+"\n Description: "+test.getString("description")+"\n Ingredients: "+test.getString("ingre");
            }
            descr.setText(fin);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
