package com.example.firebaseregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EyeTests extends AppCompatActivity {

    private Button cbTest , vaTest , dcTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye_tests);

        cbTest = findViewById(R.id.colourblindTest);
        cbTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EyeTests.this, ColourBlindTest.class);
                startActivity(intent);
            }


        });

        vaTest = findViewById(R.id.visualAcuityTest);
        vaTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EyeTests.this, VisualAcuityTest.class);
                startActivity(intent);
            }


        });

        dcTest = findViewById(R.id.duochromeTest);
        dcTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(EyeTests.this, DuoChromeTest.class);
                startActivity(intent);
            }
        });
    }
}