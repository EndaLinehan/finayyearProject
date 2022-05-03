package com.example.firebaseregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebaseregistration.models.CbTestResults;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ColourBlindTestResults extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference mDatabase, mDatabase2;
    private List<CbTestResults> listofResults;
    private FirebaseUser mUser;
    RecyclerView tableLayout;
    private String datechild;
    private BottomNavigationView btmNavbar;


    private TextView tv1,tv2,tv3,tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_results);
        tableLayout = findViewById(R.id.recyclerView);
        listofResults = new ArrayList<>();
        database = FirebaseDatabase.getInstance("https://newproject-49f95-default-rtdb.europe-west1.firebasedatabase.app/");
        mDatabase = database.getReference();
        mUser = FirebaseAuth.getInstance().getCurrentUser();


        btmNavbar = findViewById(R.id.bottomNavigationView);
        btmNavbar.setOnItemSelectedListener(navBar);

        mDatabase.child("users").child(mUser.getUid()).child("Colour Blind Test").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> testResults = snapshot.getChildren();

                for (DataSnapshot ds:testResults) {
                    datechild = ds.getKey();
                }
                    mDatabase.child("users").child(mUser.getUid()).child("Colour Blind Test").child(datechild).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Iterable<DataSnapshot> testResults = snapshot.getChildren();

                            for (DataSnapshot as:testResults) {
                                CbTestResults value = as.getValue(CbTestResults.class);
                                listofResults.add(value);
                            }
                            Adapter adapter = new Adapter(ColourBlindTestResults.this, (ArrayList<CbTestResults>) listofResults);
                            tableLayout.setAdapter(adapter);
                            tableLayout.setLayoutManager(new LinearLayoutManager(ColourBlindTestResults.this));

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private NavigationBarView.OnItemSelectedListener navBar = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch(item.getItemId()){
                case R.id.eyeTests:
                    Intent intent = new Intent(ColourBlindTestResults.this, EyeTests.class);
                    startActivity(intent);
                    break;

                case R.id.map:
                    Intent intent1 = new Intent(ColourBlindTestResults.this, MapsActivity.class);
                    startActivity(intent1);
                    break;

                case R.id.testResults:
                    Intent intent2 = new Intent(ColourBlindTestResults.this, TestResults.class);
                    startActivity(intent2);
                    break;

                case R.id.home:
                    Intent intent3 = new Intent(ColourBlindTestResults.this, HomePage.class);
                    startActivity(intent3);
                    break;
            }
            return false;
        }
    };

}