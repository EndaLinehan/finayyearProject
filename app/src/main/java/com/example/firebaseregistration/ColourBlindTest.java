package com.example.firebaseregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.firebaseregistration.models.CbTestResults;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ColourBlindTest extends AppCompatActivity {
    private Button nextButton, homeButton;
    private EditText answer;
    private ImageView imageView;
    private String guess = "";
    private StorageReference storageReference;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference imageref = databaseReference.child("Images");
    private int counter = 0;
    private int result = 0;
    private int plate = 0;
    private int correctanswer = 0;
    private ViewFlipper viewFlipper;
    private Date date= new Date();;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mRootref;
    private DatabaseReference mNoderef;
    private FirebaseUser mUser;
    private int previousFlipperID = -1; //this stores the previous state of the flipper that was selected

    private DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
    private String date2String = df.format(date);
    private BottomNavigationView btmNavbar;


    //FH (1) need an array of CBTestResults objects
    private CbTestResults [ ] CBTestResultsArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_blind_test);

        btmNavbar = findViewById(R.id.bottomNavigationView);
        btmNavbar.setOnItemSelectedListener(navBar);


        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        nextButton = findViewById(R.id.cbButtonAnswer);
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        mRootref = FirebaseDatabase.getInstance("https://newproject-49f95-default-rtdb.europe-west1.firebasedatabase.app/");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mNoderef = mRootref.getReference();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("server/saving-data/fireblog");

        imageView = findViewById(R.id.imageView1);
        imageView.setImageResource(R.drawable.cbtestimage);


        answer = (EditText) findViewById(R.id.cbanswer);
        answer.setVisibility(View.GONE);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ColourBlindTest.this, result  , Toast.LENGTH_SHORT).show();
                nextButton.setText("Next");
                counter++;

                answer.setVisibility(View.VISIBLE);
                //load images counter
                loadImages();
                viewFlipper.showNext();
                getValues();
                if(counter == 12){
                    nextButton.setText("Finish");
                }else if(counter == 13){
                    Intent intent= new Intent(ColourBlindTest.this, ColourBlindTestResults.class);
                    startActivity(intent);
                }
            }

        });

    }

    public void loadImages() {
        if(counter == 0){

        }
        else if (counter == 1) {
            imageView = findViewById(R.id.imageView2);

            //ENDA change this to the correct location/folder file for your database
            storageReference = FirebaseStorage.getInstance().getReference().child("Ishihara Colour Blind Test/answer 12.jpg");

            try {
                File local = File.createTempFile("answer 12", "jpg");
                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (counter == 2) {
            imageView = findViewById(R.id.imageView3);
            storageReference = FirebaseStorage.getInstance().getReference().child("Ishihara Colour Blind Test/answer 6.jpg");
            try {
                File local = File.createTempFile("answer 6", "jpg");

                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (counter == 3) {
            imageView = findViewById(R.id.imageView4);
            storageReference = FirebaseStorage.getInstance().getReference().child("Ishihara Colour Blind Test/answer 29.jpg");
            try {
                File local = File.createTempFile("answer 29", "jpg");
                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
                                Toast.makeText(ColourBlindTest.this, result + "29", Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (counter == 4) {
            imageView = findViewById(R.id.imageView5);
            storageReference = FirebaseStorage.getInstance().getReference().child("Ishihara Colour Blind Test/answer 3.jpg");
            try {
                File local = File.createTempFile("answer 3", "jpg");

                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
                                Toast.makeText(ColourBlindTest.this, result + "3", Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (counter == 5) {
            imageView = findViewById(R.id.imageView6);
            storageReference = FirebaseStorage.getInstance().getReference().child("Ishihara Colour Blind Test/answer 42.jpg");
            try {
                File local = File.createTempFile("answer 42", "jpg");

                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (counter == 6) {
            imageView = findViewById(R.id.imageView7);
            storageReference = FirebaseStorage.getInstance().getReference().child("Ishihara Colour Blind Test/answer 45.jpg");
            try {
                File local = File.createTempFile("answer 45", "jpg");

                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (counter == 7) {
            imageView = findViewById(R.id.imageView8);
            storageReference = FirebaseStorage.getInstance().getReference().child("Ishihara Colour Blind Test/answer 5.jpg");

            try {
                File local = File.createTempFile("answer 5", "jpg");

                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (counter == 8) {
            imageView = findViewById(R.id.imageView9);
            storageReference = FirebaseStorage.getInstance().getReference().child("Ishihara Colour Blind Test/answer 6.jpg");

            try {
                File local = File.createTempFile("answer 6", "jpg");

                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (counter == 9) {
            imageView = findViewById(R.id.imageView10);
            storageReference = FirebaseStorage.getInstance().getReference().child("Ishihara Colour Blind Test/answer 7.jpg");

            try {
                File local = File.createTempFile("answer 7", "jpg");

                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (counter == 10) {
            imageView = findViewById(R.id.imageView11);
            storageReference = FirebaseStorage.getInstance().getReference().child("Ishihara Colour Blind Test/answer 74.jpg");

            try {
                File local = File.createTempFile("answer 74", "jpg");

                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (counter == 11) {
            imageView = findViewById(R.id.imageView12);
            storageReference = FirebaseStorage.getInstance().getReference().child("Ishihara Colour Blind Test/answer 8.jpg");

            try {
                File local = File.createTempFile("answer 8", "jpg");

                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (counter == 12) {
            imageView = findViewById(R.id.imageView13);
            storageReference = FirebaseStorage.getInstance().getReference().child("Ishihara Colour Blind Test/answer 97.jpg");

            try {
                File local = File.createTempFile("answer 97", "jpg");

                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void getValues(){
        Toast.makeText(ColourBlindTest.this, "Counter is=" + counter, Toast.LENGTH_SHORT).show();

        answer = (EditText) findViewById(R.id.cbanswer);
        guess = answer.getText().toString();
        //FH counter will be increment by 1 for previous view
        if (counter == 2) {
            plate = 1;
            correctanswer = 12;
            Toast.makeText(ColourBlindTest.this, "Guess is=" + guess, Toast.LENGTH_SHORT).show();
            if (guess.equals("12")) {
                result=1;
             }
            else
                result=0;


            //FH (3) create object

            CbTestResults cbTestResults = new CbTestResults(plate, Integer.parseInt(guess), correctanswer, result, date2String);

            //FH (4) Save object to database

            mNoderef.child("users").child(mUser.getUid()).child("Colour Blind Test").child(date2String).child("plate 1").setValue(cbTestResults);
        }
        else if (counter == 3) {
            plate = 2;
            correctanswer = 6;
                if (guess.equals("6")) {
                    result=1;
                }
                else
                    result = 0;

            CbTestResults cbTestResults = new CbTestResults(plate, Integer.parseInt(guess), correctanswer, result, date2String);
            //FH (4) Save object to database
            mNoderef.child("users").child(mUser.getUid()).child("Colour Blind Test").child(date2String).child("plate 2").setValue(cbTestResults);
        }
        else if (counter == 4) {

            plate = 3;
            correctanswer = 29;
            if (guess.equals("29")) {
                result=1;
            }
            else
                result=0;

            CbTestResults cbTestResults = new CbTestResults(plate, Integer.parseInt(guess), correctanswer, result, date2String);
            //FH (4) Save object to database
            mNoderef.child("users").child(mUser.getUid()).child("Colour Blind Test").child(date2String).child("plate 3").setValue(cbTestResults);
        }
        else if (counter == 5) {

            plate = 4;
            correctanswer = 3;
            if (guess.equals("3")) {
                result=1;
            }
            else
                result=0;

            CbTestResults cbTestResults = new CbTestResults(plate, Integer.parseInt(guess), correctanswer, result, date2String);
            //FH (4) Save object to database
            mNoderef.child("users").child(mUser.getUid()).child("Colour Blind Test").child(date2String).child("plate 4").setValue(cbTestResults);
        }
        else if (counter == 6) {

            plate = 5;
            correctanswer = 42;
            if (guess.equals("42")) {
                result=1;
            }
            else
                result=0;

            CbTestResults cbTestResults = new CbTestResults(plate, Integer.parseInt(guess), correctanswer, result, date2String);
            //FH (4) Save object to database
            mNoderef.child("users").child(mUser.getUid()).child("Colour Blind Test").child(date2String).child("plate 5").setValue(cbTestResults);
        }
        else if (counter == 7) {

            plate = 6;
            correctanswer = 45;
            if (guess.equals("45")) {
                result=1;
            }
            else
                result=0;

            CbTestResults cbTestResults = new CbTestResults(plate, Integer.parseInt(guess), correctanswer, result, date2String);
            //FH (4) Save object to database
            mNoderef.child("users").child(mUser.getUid()).child("Colour Blind Test").child(date2String).child("plate 6").setValue(cbTestResults);
        }
        else if (counter == 8) {

            plate = 7;
            correctanswer = 5;
            if (guess.equals("5")) {
                result=1;
            }
            else
                result=0;

            CbTestResults cbTestResults = new CbTestResults(plate, Integer.parseInt(guess), correctanswer, result, date2String);
            //FH (4) Save object to database
            mNoderef.child("users").child(mUser.getUid()).child("Colour Blind Test").child(date2String).child("plate 7").setValue(cbTestResults);
        }
        else if (counter == 9) {

            plate = 8;
            correctanswer = 6;
            if (guess.equals("6")) {
                result=1;
            }
            else
                result=0;

            CbTestResults cbTestResults = new CbTestResults(plate, Integer.parseInt(guess), correctanswer, result, date2String);
            //FH (4) Save object to database
            mNoderef.child("users").child(mUser.getUid()).child("Colour Blind Test").child(date2String).child("plate 8").setValue(cbTestResults);
        }
        else if (counter == 10) {

            plate = 9;
            correctanswer = 7;
            if (guess.equals("7")) {
                result=1;
            }
            else
                result=0;

            CbTestResults cbTestResults = new CbTestResults(plate, Integer.parseInt(guess), correctanswer, result, date2String);
            //FH (4) Save object to database
            mNoderef.child("users").child(mUser.getUid()).child("Colour Blind Test").child(date2String).child("plate 9").setValue(cbTestResults);
        }
        else if (counter == 11) {

            plate = 10;
            correctanswer = 74;
            if (guess.equals("74")) {
                result=1;
            }
            else
                result=0;

            CbTestResults cbTestResults = new CbTestResults(plate, Integer.parseInt(guess), correctanswer, result, date2String);
            //FH (4) Save object to database
            mNoderef.child("users").child(mUser.getUid()).child("Colour Blind Test").child(date2String).child("plate 10").setValue(cbTestResults);
        }
        else if (counter == 12) {

            plate = 11;
            correctanswer = 8;
            if (guess.equals("8")) {
                result=1;
            }
            else
                result=0;

            CbTestResults cbTestResults = new CbTestResults(plate, Integer.parseInt(guess), correctanswer, result, date2String);
            //FH (4) Save object to database
            mNoderef.child("users").child(mUser.getUid()).child("Colour Blind Test").child(date2String).child("plate 11").setValue(cbTestResults);
        }
        else if (counter == 13) {

            plate = 12;
            correctanswer = 97;
            if (guess.equals("97")) {
                result=1;
            }
            else
                result=0;

            CbTestResults cbTestResults = new CbTestResults(plate, Integer.parseInt(guess), correctanswer, result, date2String);
            //FH (4) Save object to database
            mNoderef.child("users").child(mUser.getUid()).child("Colour Blind Test").child(date2String).child("plate 12").setValue(cbTestResults);

        }
        //etc


    }

    private NavigationBarView.OnItemSelectedListener navBar = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch(item.getItemId()){
                case R.id.eyeTests:
                    Intent intent = new Intent(ColourBlindTest.this, EyeTests.class);
                    startActivity(intent);
                    break;

                case R.id.map:
                    Intent intent1 = new Intent(ColourBlindTest.this, MapsActivity.class);
                    startActivity(intent1);
                    break;

                case R.id.testResults:
                    Intent intent2 = new Intent(ColourBlindTest.this, TestResults.class);
                    startActivity(intent2);
                    break;

                case R.id.home:
                    Intent intent3 = new Intent(ColourBlindTest.this, HomePage.class);
                    startActivity(intent3);
                    break;
            }
            return false;
        }
    };



}

