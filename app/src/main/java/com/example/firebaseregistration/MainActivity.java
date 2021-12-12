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

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;
    private FirebaseUser mUser;

    private EditText email;
    private EditText password;
    private SignInButton signInButton;
    private Button regButton;
    private String email1;
    private String password1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        email = (EditText) findViewById(R.id.editTextTextPersonName);
        password = (EditText) findViewById(R.id.editTextTextPassword);

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        //Toast.makeText(this, "Outside"+email1, Toast.LENGTH_LONG).show();


        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                email1 = email.getText().toString();
                password1 = password.getText().toString();
                //Toast.makeText(MainActivity.this, email1, Toast.LENGTH_LONG).show();

                mAuth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "User signed in.",Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(MainActivity.this, HomePage.class);
                            startActivity(intent);
                        }
                        else {
                            Log.w("MySignin", "SignInUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }});

        regButton = findViewById(R.id.regButton);
        regButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
     }
}
