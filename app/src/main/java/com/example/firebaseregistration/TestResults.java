package com.example.firebaseregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestResults extends AppCompatActivity {
    private Button cbTest , vaTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_results2);

        cbTest = findViewById(R.id.colourblindTest);
        cbTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TestResults.this, ColourBlindTestResults.class);
                startActivity(intent);
            }


        });

        vaTest = findViewById(R.id.visualAcuityTest);
        vaTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TestResults.this, VisualAcuityTest.class);
                startActivity(intent);
            }


        });
    }
}