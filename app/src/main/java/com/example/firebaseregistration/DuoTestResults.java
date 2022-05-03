package com.example.firebaseregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.firebaseregistration.models.DuoChromeTestResults;
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

public class DuoTestResults extends AppCompatActivity {

    private Spinner spinner;
    RecyclerView tableLayout;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseUser mUser;
    private List<String> items;
    private String item;
    private List<DuoChromeTestResults> duotestResults;
    private BottomNavigationView btmNavbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duo_test_results);

        database = FirebaseDatabase.getInstance("https://newproject-49f95-default-rtdb.europe-west1.firebasedatabase.app/");
        mDatabase = database.getReference();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        items = new ArrayList<>();
        duotestResults = new ArrayList<>();

        tableLayout = findViewById(R.id.recyclerView);


        btmNavbar = findViewById(R.id.bottomNavigationView);
        btmNavbar.setOnItemSelectedListener(navBar);


        mDatabase.child("users").child(mUser.getUid()).child("DuoChrome Test").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> testResults = snapshot.getChildren();

                for(DataSnapshot ds: testResults){
                    DuoChromeTestResults duoChromeTestResults = ds.getValue(DuoChromeTestResults.class);
                    duotestResults.add(duoChromeTestResults);
                }
                VA_Adapter2 adapter2 = new VA_Adapter2(DuoTestResults.this,(ArrayList<DuoChromeTestResults>) duotestResults);
                tableLayout.setAdapter(adapter2);
                tableLayout.setLayoutManager(new LinearLayoutManager(DuoTestResults.this));
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
                    Intent intent = new Intent(DuoTestResults.this, EyeTests.class);
                    startActivity(intent);
                    break;

                case R.id.map:
                    Intent intent1 = new Intent(DuoTestResults.this, MapsActivity.class);
                    startActivity(intent1);
                    break;

                case R.id.testResults:
                    Intent intent2 = new Intent(DuoTestResults.this, TestResults.class);
                    startActivity(intent2);
                    break;

                case R.id.home:
                    Intent intent3 = new Intent(DuoTestResults.this, HomePage.class);
                    startActivity(intent3);
                    break;
            }
            return false;
        }
    };

}