package com.example.firebaseregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebaseregistration.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private String email;
    private String password;

    private EditText etemail;
    private EditText etpassword;
    private EditText confirmPassword;

    private FirebaseDatabase mRootref;
    private DatabaseReference mNoderef;
    private Button signUp;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        user = new User();
        etemail = findViewById(R.id.editTextEmailAddress);
        etpassword = findViewById(R.id.editTextPassword);
        confirmPassword = findViewById(R.id.editTextConfirmPassword);

        signUp = findViewById(R.id.signUpButton);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRootref = FirebaseDatabase.getInstance("https://newproject-49f95-6adcc.firebaseio.com/");
                mNoderef = mRootref.getReference();
                mNoderef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
//                            id = snapshot.getChildrenCount();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                mAuth = FirebaseAuth.getInstance();
                email = etemail.getText().toString();

                if(etpassword.getText().toString().equals(confirmPassword.getText().toString())) {
                    password = etpassword.getText().toString();
                    user.setEmail(email);
                    user.setPassword(password);
//                    mNoderef.child(String.valueOf(id+1)).setValue(user);
//                    user.setId(id);
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity2.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
//                                id = id++;
                                Log.d("myActivity", "createUserWithEmail:success");
                                mUser = mAuth.getCurrentUser();
                                String id = task.getResult().getUser().getUid();
                                mRootref.getReference().child("Users").child(id).setValue(user);
                                Intent intent= new Intent(MainActivity2.this, MainActivity.class);
                                startActivity(intent);}

                            else {

                                Log.w("myActivity", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity2.this, "Authentication 1 failed:"+ task.getException(), Toast.LENGTH_SHORT).show();}  }});
                }
                else{
                    Toast.makeText(MainActivity2.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}