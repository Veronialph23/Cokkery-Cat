package com.example.page1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView resultfield = findViewById(R.id.result);
        login = findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] key = new String[2];
                        key[0] = "email";
                        key[1] = "pass";
                        String[] value = new String[2];
                        EditText email = findViewById(R.id.editTextTextPersonName);
                        EditText pass = findViewById(R.id.editTextTextPassword2);
                        value[0] = email.getText().toString();
                        value[1] = pass.getText().toString();
                        PutData putData = new PutData("https://cookhousepanda.herokuapp.com/login.php", "POST", key, value);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                Log.i("PutData", result);
                                if (result.equals("success")){
                                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                    startActivity(intent);
                                }else{
                                    resultfield.setText(result);
                                }
                                //End ProgressBar (Set visibility to GONE)
                            }
                        }

                    }
                });
            }
        });
    }
}