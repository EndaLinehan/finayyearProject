package com.example.firebaseregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.firebaseregistration.models.CbTestResults;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TestResults extends AppCompatActivity {
    private DatabaseReference mDatabase, mDatabase2;
    private FirebaseDatabase mRootref;
    private DatabaseReference mNoderef;
    private FirebaseUser mUser;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_results);

        mUser = FirebaseAuth.getInstance().getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance("https://newproject-49f95-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        mDatabase2 = mDatabase.child("users").child(mUser.getUid()).child("plate1");

        mDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, Object> dataMap=(HashMap<String, Object>) snapshot.getValue();

                for(String key : dataMap.keySet()){
                    Object data = dataMap.get(key);
                    HashMap<String,Object> testResult = (HashMap<String, Object>) data;

                    CbTestResults cbTestResults= new CbTestResults((int)testResult.get("plate"),(int)testResult.get("answer"),(int)testResult.get("correctAnswer"),(int)testResult.get("result"));
                    tv = findViewById(R.id.r1b1);
                    tv.setText(String.valueOf(cbTestResults.getPlate()));
                    tv = findViewById(R.id.r1b2);
                    tv.setText(String.valueOf(cbTestResults.getAnswer()));
                    tv = findViewById(R.id.r1b3);
                    tv.setText(String.valueOf(cbTestResults.getCorrectAnswer()));
                    tv = findViewById(R.id.r1b4);
                    tv.setText(String.valueOf(cbTestResults.getResult()));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}