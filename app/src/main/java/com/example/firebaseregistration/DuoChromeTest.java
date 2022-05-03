package com.example.firebaseregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

import com.example.firebaseregistration.models.DuoChromeTestResults;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DuoChromeTest extends AppCompatActivity {


    private Button redBtn, greenBtn, startBtn;
    private EditText answer;
    private ImageView imageView;
    private String guess = "";
    private StorageReference storageReference;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private int counter = 0, redCounter = 0, greenCounter = 0;
    private ViewFlipper viewFlipper;
    private Date date = new Date();
    ;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mRootref;
    private DatabaseReference mNoderef;
    private FirebaseUser mUser;
    private int previousFlipperID = -1; //this stores the previous state of the flipper that was selected

    private DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
    private String date2String = df.format(date);
    private BottomNavigationView btmNavbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duo_chrome_test);

        //Short sited - Green
        //Long sited - Red

        btmNavbar = findViewById(R.id.bottomNavigationView);
        btmNavbar.setOnItemSelectedListener(navBar);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        redBtn = findViewById(R.id.redBtn);
        greenBtn = findViewById(R.id.greenBtn);
        startBtn = findViewById(R.id.beginBtn);
        redBtn.setVisibility(View.GONE);
        greenBtn.setVisibility(View.GONE);
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        mRootref = FirebaseDatabase.getInstance("https://newproject-49f95-default-rtdb.europe-west1.firebasedatabase.app/");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mNoderef = mRootref.getReference();

        if (counter == 0) {
            imageView = findViewById(R.id.imageView0);

            //ENDA change this to the correct location/folder file for your database
            storageReference = FirebaseStorage.getInstance().getReference().child("Duochrome Test/Image 0.jpg");

            try {
                File local = File.createTempFile("Image 0", "jpg");
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


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                    counter++;
                    startBtn.setVisibility(View.GONE);
                    redBtn.setVisibility(View.VISIBLE);
                    greenBtn.setVisibility(View.VISIBLE);

                    loadImages();
                    viewFlipper.showNext();


                    redBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            counter++;


                            loadImages();
                            viewFlipper.showNext();
                            redCounter++;
                            getValues();

                            if (counter == 5) {
                                imageView = findViewById(R.id.imageView0);

                                //ENDA change this to the correct location/folder file for your database
                                storageReference = FirebaseStorage.getInstance().getReference().child("Duochrome Test/Image 5.jpg");

                                startBtn.setVisibility(View.VISIBLE);
                                redBtn.setVisibility(View.GONE);
                                greenBtn.setVisibility(View.GONE);
                                startBtn.setText("Eyes Swapped");

                                try {
                                    File local = File.createTempFile("Image 5", "jpg");
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
                            if (counter == 10) {

                                redBtn.setVisibility(View.GONE);
                                greenBtn.setVisibility(View.GONE);
                                startBtn.setText("Finish!");
                                startBtn.setVisibility(View.VISIBLE);

                            } else if (counter == 11) {
                                Intent intent = new Intent(DuoChromeTest.this, EyeTests.class);
                                startActivity(intent);
                            }
                        }
                    });

                    greenBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            counter++;

                            loadImages();
                            viewFlipper.showNext();
                            greenCounter++;
                            Toast.makeText(DuoChromeTest.this, String.valueOf(greenCounter), Toast.LENGTH_SHORT).show();
                            getValues();

                            if (counter == 5) {
                                imageView = findViewById(R.id.imageView0);

                                //ENDA change this to the correct location/folder file for your database
                                storageReference = FirebaseStorage.getInstance().getReference().child("Duochrome Test/Image 5.jpg");

                                startBtn.setVisibility(View.VISIBLE);
                                redBtn.setVisibility(View.GONE);
                                greenBtn.setVisibility(View.GONE);
                                startBtn.setText("Eyes Swapped");

                                try {
                                    File local = File.createTempFile("Image 5", "jpg");
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
                            if (counter == 10) {

                                redBtn.setVisibility(View.GONE);
                                greenBtn.setVisibility(View.GONE);
                                startBtn.setText("Finish!");
                                startBtn.setVisibility(View.VISIBLE);

                            }

                        }
                    });

                if (counter == 11) {
                    Intent intent = new Intent(DuoChromeTest.this, DuoTestResults.class);
                    startActivity(intent);
                }
            }
        });
        getValues();
    }


    public void loadImages() {
        if (counter == 1) {
            imageView = findViewById(R.id.imageView1);

            //ENDA change this to the correct location/folder file for your database
            storageReference = FirebaseStorage.getInstance().getReference().child("Duochrome Test/Image 1.png");

            try {
                File local = File.createTempFile("Image 1", "png");
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
        } else if (counter == 2) {
            imageView = findViewById(R.id.imageView2);
            storageReference = FirebaseStorage.getInstance().getReference().child("Duochrome Test/Image 2.png");
            try {
                File local = File.createTempFile("Image 2", "png");

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
        } else if (counter == 3) {
            imageView = findViewById(R.id.imageView3);
            storageReference = FirebaseStorage.getInstance().getReference().child("Duochrome Test/Image 3.jpg");
            try {
                File local = File.createTempFile("Image 3", "jpg");
                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
//                                Toast.makeText(ColourBlindTest.this, result + "29", Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (counter == 4) {
            imageView = findViewById(R.id.imageView4);
            storageReference = FirebaseStorage.getInstance().getReference().child("Duochrome Test/Image 4.jpg");
            try {
                File local = File.createTempFile("Image 4", "jpg");
                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
//                                Toast.makeText(ColourBlindTest.this, result + "29", Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void getValues() {
        String eyesite = "";
        if (greenCounter > redCounter) {
            eyesite = "You are likely to be short sited";
        } else if (redCounter > greenCounter) {
            eyesite = "You are likely to be long sited";
        } else {
            eyesite = "You are neither short or long sited";
        }

        DuoChromeTestResults dcTestResults = new DuoChromeTestResults(eyesite, date2String);

        mNoderef.child("users").child(mUser.getUid()).child("DuoChrome Test").child(date2String).setValue(dcTestResults);

    }


    private NavigationBarView.OnItemSelectedListener navBar = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch(item.getItemId()){
                case R.id.eyeTests:
                    Intent intent = new Intent(DuoChromeTest.this, EyeTests.class);
                    startActivity(intent);
                    break;

                case R.id.map:
                    Intent intent1 = new Intent(DuoChromeTest.this, MapsActivity.class);
                    startActivity(intent1);
                    break;

                case R.id.testResults:
                    Intent intent2 = new Intent(DuoChromeTest.this, TestResults.class);
                    startActivity(intent2);
                    break;

                case R.id.home:
                    Intent intent3 = new Intent(DuoChromeTest.this, HomePage.class);
                    startActivity(intent3);
                    break;
            }
            return false;
        }
    };
}