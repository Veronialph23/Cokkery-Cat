package com.example.page1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {

    EditText ingre1,ingre2;
    Button ingre_btn;

    Spinner dropdown1,dropdown2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ingre1 = findViewById(R.id.editTextTextPersonName2);
        ingre2 = findViewById(R.id.editTextTextPersonName3);
        ingre_btn = findViewById(R.id.button2);
        TextView resultfield = findViewById(R.id.result);


        //get the spinner from the xml.
        Spinner dropdown1 = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] items = new String[]{"Indian", "Chinese", "Italian","Continental"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown1.setAdapter(adapter);


        Spinner dropdown2 = findViewById(R.id.spinner2);
        String[] items2 = new String[]{"15 min","30 min", "1 hr","No Time Limit"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
        Spinner cuisine = (Spinner) findViewById(R.id.spinner1);
        Spinner time = (Spinner) findViewById(R.id.spinner2);

        ingre_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] key = new String[4];
                        key[0] = "ingre1";
                        key[1] = "ingre2";
                        key[2] = "cuisine";
                        key[3] = "time";
                        String[] value = new String[4];
                        value[0] = ingre1.getText().toString();
                        value[1] = ingre2.getText().toString();
                        value[2] = cuisine.getSelectedItem().toString();
                        value[3] = time.getSelectedItem().toString();

                        PutData putData = new PutData("https://cookhousepanda.herokuapp.com/dish.php", "POST", key, value);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String check = putData.getResult();
                                if (check.equals("Incorrect Ingredients or Dishes for Selected Parameters Not Available")) {
                                    Log.i("PutData", check);
                                    resultfield.setText(check);
                                }else{
                                    StringBuilder res = new StringBuilder(putData.getResult());
                                    res = res.deleteCharAt(11);
                                    res = res.deleteCharAt(11);
                                    res = res.deleteCharAt(11);
                                    String result = res.toString();
                                    Intent intent2 = new Intent(MainActivity2.this, MainActivity3.class);
                                    intent2.putExtra("result", result);
                                    startActivity(intent2);

                                }
//
                                //End ProgressBar (Set visibility to GONE)
                            }
                        }
                    }
                });

            }
        });
    }
}