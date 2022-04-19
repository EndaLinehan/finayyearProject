package com.example.firebaseregistration;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    private Button eyetests;
    private Button shop,testResults,localOpticiansBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eyetests = findViewById(R.id.eyetestbutton);

        eyetests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "Selected Eye Tests",Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(HomePage.this, EyeTests.class);
                startActivity(intent);
            }
        });

        shop = findViewById(R.id.shopBtn);

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Store.class);
                startActivity(intent);
            }
        });

        testResults = findViewById(R.id.testResults);

        testResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, TestResults.class);
                startActivity(intent);

            }
        });

        localOpticiansBtn = findViewById(R.id.localOpticiansBtn);

        localOpticiansBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}