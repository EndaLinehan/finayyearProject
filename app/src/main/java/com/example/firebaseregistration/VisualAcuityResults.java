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

import com.example.firebaseregistration.models.VATestResults;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VisualAcuityResults extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private VATestResults testResult;
    private List<VATestResults> vatestResults,vatestResults2;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseUser mUser;
    private List<String> items;
    RecyclerView tableLayout, tablelayout2;
    private String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_acuity_results);
        vatestResults = new ArrayList<>();

        database = FirebaseDatabase.getInstance("https://newproject-49f95-default-rtdb.europe-west1.firebasedatabase.app/");
        mDatabase = database.getReference();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        items = new ArrayList<>();
        spinner = findViewById(R.id.spinner);
        tableLayout = findViewById(R.id.recyclerView);

        mDatabase.child("users").child(mUser.getUid()).child("Visual Acuity Test").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> testResults = snapshot.getChildren();

                for(DataSnapshot ds: testResults){
                    String child = ds.getKey();
                    items.add(child);
                    ArrayAdapter arrayAdapter = new ArrayAdapter(VisualAcuityResults.this, android.R.layout.simple_spinner_dropdown_item,items);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                    spinner.setAdapter(arrayAdapter);
                    spinner.setOnItemSelectedListener(VisualAcuityResults.this);
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
        Toast.makeText(this, item, Toast.LENGTH_SHORT).show();
        vatestResults.clear();

        mDatabase.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(item).child("Left Eye").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> testResults = snapshot.getChildren();

                for(DataSnapshot ds: testResults){
                    VATestResults vaTestResults = ds.getValue(VATestResults.class);
                    vatestResults.add(vaTestResults);

                }
                VA_Adapter adapter = new VA_Adapter(VisualAcuityResults.this,(ArrayList<VATestResults>) vatestResults);
                tableLayout.setAdapter(adapter);
                tableLayout.setLayoutManager(new LinearLayoutManager(VisualAcuityResults.this));

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        mDatabase.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(item).child("Right Eye").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> testResults = snapshot.getChildren();

                for(DataSnapshot ds: testResults){
                    VATestResults vaTestResults = ds.getValue(VATestResults.class);
                    vatestResults.add(vaTestResults);

                }
                VA_Adapter adapter = new VA_Adapter(VisualAcuityResults.this,(ArrayList<VATestResults>) vatestResults);
                tableLayout.setAdapter(adapter);
                tableLayout.setLayoutManager(new LinearLayoutManager(VisualAcuityResults.this));

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