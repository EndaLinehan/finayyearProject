package com.example.firebaseregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.firebaseregistration.models.DuoChromeTestResults;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DuoTestResults extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner;
    RecyclerView tableLayout;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseUser mUser;
    private List<String> items;
    private String item;
    private List<DuoChromeTestResults> duotestResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duo_test_results);

        database = FirebaseDatabase.getInstance("https://newproject-49f95-default-rtdb.europe-west1.firebasedatabase.app/");
        mDatabase = database.getReference();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        items = new ArrayList<>();
        duotestResults = new ArrayList<>();
        spinner = findViewById(R.id.spinner);
        tableLayout = findViewById(R.id.recyclerView);

        mDatabase.child("users").child(mUser.getUid()).child("DuoChrome Test").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> testResults = snapshot.getChildren();

                for(DataSnapshot ds: testResults){
                    String child = ds.getKey();
                    items.add(child);
                    ArrayAdapter arrayAdapter = new ArrayAdapter(DuoTestResults.this, android.R.layout.simple_spinner_dropdown_item,items);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                    spinner.setAdapter(arrayAdapter);
                    spinner.setOnItemSelectedListener(DuoTestResults.this);
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        item = spinner.getSelectedItem().toString();
        duotestResults.clear();

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

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}