package com.example.firebaseregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebaseregistration.models.CbTestResults;
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

}