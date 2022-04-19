package com.example.firebaseregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.firebaseregistration.models.VATestResults;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class VisualAcuityTest extends AppCompatActivity {
    private Button nextButton, startbtn, homeButton;
    private EditText answer;
    private ImageView imageView;
    private String guess = "";
    private StorageReference storageReference;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference imageref = databaseReference.child("Images");
    private int counter = 0, eyecounter = 0;
    private int result = 0;
    private int image = 0;
    private String correctanswer = "";
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


    private VATestResults[] vaTestResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_acuity_test);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        nextButton = findViewById(R.id.submitBtn);
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        mRootref = FirebaseDatabase.getInstance("https://newproject-49f95-default-rtdb.europe-west1.firebasedatabase.app/");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mNoderef = mRootref.getReference();

        nextButton.setVisibility(View.GONE);
        answer = findViewById(R.id.editTextAnswer);
        answer.setVisibility(View.GONE);

        if (counter == 0) {
            imageView = findViewById(R.id.imageView0);

            //ENDA change this to the correct location/folder file for your database
            storageReference = FirebaseStorage.getInstance().getReference().child("Instructions/Instruction 1.jpg");

            try {
                File local = File.createTempFile("File1", "jpg");
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

        startbtn = findViewById(R.id.beginBtn);
        startbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                counter++;
                loadImages();
                viewFlipper.showNext();
                getValues();

                Toast.makeText(VisualAcuityTest.this, String.valueOf(counter), Toast.LENGTH_SHORT).show();
                if(counter == 3){
                    startbtn.setText("Start");
                }
                else if (counter == 4|| counter == 13) {
                    startbtn.setVisibility(View.GONE);
                    nextButton.setVisibility(View.VISIBLE);
                    answer.setVisibility(View.VISIBLE);
                }else if(counter == 22){

                    Intent intent = new Intent(VisualAcuityTest.this, EyeTests.class);
                    startActivity(intent);
                }
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;

                if (counter == 12) {
                    nextButton.setVisibility(View.GONE);
                    answer.setVisibility(View.GONE);
                    startbtn.setVisibility(View.VISIBLE);
                    imageView = findViewById(R.id.imageView04);

                    //ENDA change this to the correct location/folder file for your database
                    storageReference = FirebaseStorage.getInstance().getReference().child("Instructions/Instruction 5.jpg");

                    try {
                        File local = File.createTempFile("File1", "jpg");
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

                loadImages();
                viewFlipper.showNext();
                getValues();

                Toast.makeText(VisualAcuityTest.this, String.valueOf(counter), Toast.LENGTH_SHORT).show();


                if (counter == 21) {
                    nextButton.setVisibility(View.GONE);
                    answer.setVisibility(View.GONE);
                    startbtn.setText("Finish");
                    startbtn.setVisibility(View.VISIBLE);
                }

                answer.setText("");
            }
        });

    }

    public void loadImages() {

        if (counter == 1) {

            imageView = findViewById(R.id.imageView01);

            //ENDA change this to the correct location/folder file for your database
            storageReference = FirebaseStorage.getInstance().getReference().child("Instructions/Instruction 2.jpg");

            try {
                File local = File.createTempFile("File2", "jpg");
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

            imageView = findViewById(R.id.imageView02);

            //ENDA change this to the correct location/folder file for your database
            storageReference = FirebaseStorage.getInstance().getReference().child("Instructions/Instruction 3.jpg");

            try {
                File local = File.createTempFile("File1", "jpg");
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

            imageView = findViewById(R.id.imageView03);
            storageReference = FirebaseStorage.getInstance().getReference().child("Instructions/Instruction 4.jpg");
            try {
                File local = File.createTempFile("File3", "jpg");
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

            imageView = findViewById(R.id.imageView1);

            //ENDA change this to the correct location/folder file for your database
            storageReference = FirebaseStorage.getInstance().getReference().child("Visual Acuity Test/Line1.jpg");

            try {
                File local = File.createTempFile("File1", "jpg");
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


        } else if (counter == 5) {

            imageView = findViewById(R.id.imageView2);
            storageReference = FirebaseStorage.getInstance().getReference().child("Visual Acuity Test/Line2.jpg");
            try {
                File local = File.createTempFile("File2", "jpg");

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


        } else if (counter == 6) {


            imageView = findViewById(R.id.imageView3);
            storageReference = FirebaseStorage.getInstance().getReference().child("Visual Acuity Test/Line3.jpg");
            try {
                File local = File.createTempFile("File3", "jpg");
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


        } else if (counter == 7) {
            imageView = findViewById(R.id.imageView4);
            storageReference = FirebaseStorage.getInstance().getReference().child("Visual Acuity Test/Line4.jpg");
            try {
                File local = File.createTempFile("File4", "jpg");

                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
//                                Toast.makeText(ColourBlindTest.this, result + "3", Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (counter == 8) {

            imageView = findViewById(R.id.imageView5);
            storageReference = FirebaseStorage.getInstance().getReference().child("Visual Acuity Test/Line5.jpg");
            try {
                File local = File.createTempFile("File5", "jpg");

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
        } else if (counter == 9) {

            imageView = findViewById(R.id.imageView6);
            storageReference = FirebaseStorage.getInstance().getReference().child("Visual Acuity Test/Line6.jpg");
            try {
                File local = File.createTempFile("File6", "jpg");

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

        } else if (counter == 10) {
            imageView = findViewById(R.id.imageView7);
            storageReference = FirebaseStorage.getInstance().getReference().child("Visual Acuity Test/Line7.jpg");

            try {
                File local = File.createTempFile("File7", "jpg");

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
        } else if (counter == 11) {
            imageView = findViewById(R.id.imageView8);
            storageReference = FirebaseStorage.getInstance().getReference().child("Visual Acuity Test/Line8.jpg");

            try {
                File local = File.createTempFile("File8", "jpg");

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
        } else if (counter == 13) {

            imageView = findViewById(R.id.imageView9);

            //ENDA change this to the correct location/folder file for your database
            storageReference = FirebaseStorage.getInstance().getReference().child("Visual Acuity Test/Line1.jpg");

            try {
                File local = File.createTempFile("File1", "jpg");
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


        } else if (counter == 14) {

            imageView = findViewById(R.id.imageView10);
            storageReference = FirebaseStorage.getInstance().getReference().child("Visual Acuity Test/Line2.jpg");
            try {
                File local = File.createTempFile("File2", "jpg");

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


        } else if (counter == 15) {


            imageView = findViewById(R.id.imageView11);
            storageReference = FirebaseStorage.getInstance().getReference().child("Visual Acuity Test/Line3.jpg");
            try {
                File local = File.createTempFile("File3", "jpg");
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


        } else if (counter == 16) {
            imageView = findViewById(R.id.imageView12);
            storageReference = FirebaseStorage.getInstance().getReference().child("Visual Acuity Test/Line4.jpg");
            try {
                File local = File.createTempFile("File4", "jpg");

                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
//                                Toast.makeText(ColourBlindTest.this, result + "3", Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (counter == 17) {

            imageView = findViewById(R.id.imageView13);
            storageReference = FirebaseStorage.getInstance().getReference().child("Visual Acuity Test/Line5.jpg");
            try {
                File local = File.createTempFile("File5", "jpg");

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
        } else if (counter == 18) {

            imageView = findViewById(R.id.imageView14);
            storageReference = FirebaseStorage.getInstance().getReference().child("Visual Acuity Test/Line6.jpg");
            try {
                File local = File.createTempFile("File6", "jpg");

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

        } else if (counter == 19) {
            imageView = findViewById(R.id.imageView15);
            storageReference = FirebaseStorage.getInstance().getReference().child("Visual Acuity Test/Line7.jpg");

            try {
                File local = File.createTempFile("File7", "jpg");

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
        } else if (counter == 20) {
            imageView = findViewById(R.id.imageView16);
            storageReference = FirebaseStorage.getInstance().getReference().child("Visual Acuity Test/Line8.jpg");

            try {
                File local = File.createTempFile("File8", "jpg");

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

    public void getValues() {
        answer = (EditText) findViewById(R.id.editTextAnswer);
        guess = answer.getText().toString();
        String eyesite = "";
        String[] guess2 = guess.split("(?!^)");
        if (counter == 5) {
            image = 1;
            correctanswer = "E";
            if (guess.equalsIgnoreCase(correctanswer)) {
                result = 1;
                eyesite = "20/200";
            } else
                result = 0;


            VATestResults vaTestResults = new VATestResults(image, guess, correctanswer, result, date2String, eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(date2String).child("Left Eye").child("Line 1").setValue(vaTestResults);

        } else if (counter == 6) {
            image = 2;
            correctanswer = "FP";
            int counter2 = 0;
            String[] correctanswer2 = correctanswer.split("(?!^)");
            for (int i = 0; i < guess2.length; i++) {
                if (correctanswer2[i].equalsIgnoreCase(guess2[i])) {
                    counter2++;
                }
            }
            if (counter2 >= 2) {
                result = 1;
                eyesite = "20/100";
            } else
                result = 0;

            VATestResults vaTestResults = new VATestResults(image, guess, correctanswer, result, date2String, eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(date2String).child("Left Eye").child("Line 2").setValue(vaTestResults);


        } else if (counter == 7) {
            image = 3;
            correctanswer = "TOZ";
            int counter2 = 0;
            String[] correctanswer2 = correctanswer.split("(?!^)");
            for (int i = 0; i < guess2.length; i++) {
                if (correctanswer2[i].equalsIgnoreCase(guess2[i])) {
                    counter2++;
                }
            }
            if (counter2 >= 3) {
                result = 1;
                eyesite = "20/70";
            } else
                result = 0;


            VATestResults vaTestResults = new VATestResults(image, guess, correctanswer, result, date2String, eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(date2String).child("Left Eye").child("Line 3").setValue(vaTestResults);


        } else if (counter == 8) {
            image = 4;
            correctanswer = "LPED";
            int counter2 = 0;
            String[] correctanswer2 = correctanswer.split("(?!^)");
            for (int i = 0; i < guess2.length; i++) {
                if (correctanswer2[i].equalsIgnoreCase(guess2[i])) {
                    counter2++;
                }
            }
            if (counter2 >= 3) {
                result = 1;
                eyesite = "20/50";
            } else
                result = 0;

            VATestResults vaTestResults = new VATestResults(image, guess, correctanswer, result, date2String, eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(date2String).child("Left Eye").child("Line 4").setValue(vaTestResults);


        } else if (counter == 9) {
            image = 5;
            correctanswer = "PECFD";
            int counter2 = 0;
            String[] correctanswer2 = correctanswer.split("(?!^)");
            for (int i = 0; i < guess2.length; i++) {
                if (correctanswer2[i].equalsIgnoreCase(guess2[i])) {
                    counter2++;
                }
            }
            if (counter2 >= 3) {
                result = 1;
                eyesite = "20/40";
            } else
                result = 0;

            VATestResults vaTestResults = new VATestResults(image, guess, correctanswer, result, date2String, eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(date2String).child("Left Eye").child("Line 5").setValue(vaTestResults);


        } else if (counter == 10) {
            image = 6;
            correctanswer = "EDFCZP";
            int counter2 = 0;
            String[] correctanswer2 = correctanswer.split("(?!^)");
            for (int i = 0; i < guess2.length; i++) {
                if (correctanswer2[i].equalsIgnoreCase(guess2[i])) {
                    counter2++;
                }
            }
            if (counter2 >= 3) {
                result = 1;
                eyesite = "20/30";
            } else
                result = 0;

            VATestResults vaTestResults = new VATestResults(image, guess, correctanswer, result, date2String, eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(date2String).child("Left Eye").child("Line 6").setValue(vaTestResults);


        } else if (counter == 11) {
            image = 7;
            correctanswer = "FELOPZD";
            int counter2 = 0;
            String[] correctanswer2 = correctanswer.split("(?!^)");
            for (int i = 0; i < guess2.length; i++) {
                if (correctanswer2[i].equalsIgnoreCase(guess2[i])) {
                    counter2++;
                }
            }
            if (counter2 >= 3) {
                result = 1;
                eyesite = "20/25";
            } else
                result = 0;

            VATestResults vaTestResults = new VATestResults(image, guess, correctanswer, result, date2String, eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(date2String).child("Left Eye").child("Line 7").setValue(vaTestResults);

        } else if (counter == 12) {
            image = 8;
            correctanswer = "DEFPOTEC";
            int counter2 = 0;
            String[] correctanswer2 = correctanswer.split("(?!^)");
            for (int i = 0; i < guess2.length; i++) {
                if (correctanswer2[i].equalsIgnoreCase(guess2[i])) {
                    counter2++;
                }
            }
            if (counter2 >= 3) {
                result = 1;
                eyesite = "20/20";
            } else
                result = 0;

            VATestResults vaTestResults = new VATestResults(image, guess, correctanswer, result, date2String, eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(date2String).child("Left Eye").child("Line 8").setValue(vaTestResults);

        } else if (counter == 14) {
            image = 1;
            correctanswer = "E";
            if (guess.equalsIgnoreCase(correctanswer)) {
                result = 1;
                eyesite = "20/200";
            } else
                result = 0;


            VATestResults vaTestResults = new VATestResults(image, guess, correctanswer, result, date2String, eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(date2String).child("Right Eye").child("Line 1").setValue(vaTestResults);

        } else if (counter == 15) {
            image = 2;
            correctanswer = "FP";
            int counter2 = 0;
            String[] correctanswer2 = correctanswer.split("(?!^)");
            for (int i = 0; i < guess2.length; i++) {
                if (correctanswer2[i].equalsIgnoreCase(guess2[i])) {
                    counter2++;
                }
            }
            if (counter2 >= 2) {
                result = 1;
                eyesite = "20/100";
            } else
                result = 0;

            VATestResults vaTestResults = new VATestResults(image, guess, correctanswer, result, date2String, eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(date2String).child("Right Eye").child("Line 2").setValue(vaTestResults);


        } else if (counter == 16) {
            image = 3;
            correctanswer = "TOZ";
            int counter2 = 0;
            String[] correctanswer2 = correctanswer.split("(?!^)");
            for (int i = 0; i < guess2.length; i++) {
                if (correctanswer2[i].equalsIgnoreCase(guess2[i])) {
                    counter2++;
                }
            }
            if (counter2 >= 3) {
                result = 1;
                eyesite = "20/70";
            } else
                result = 0;


            VATestResults vaTestResults = new VATestResults(image, guess, correctanswer, result, date2String, eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(date2String).child("Right Eye").child("Line 3").setValue(vaTestResults);


        } else if (counter == 17) {
            image = 4;
            correctanswer = "LPED";
            int counter2 = 0;
            String[] correctanswer2 = correctanswer.split("(?!^)");
            for (int i = 0; i < guess2.length; i++) {
                if (correctanswer2[i].equalsIgnoreCase(guess2[i])) {
                    counter2++;
                }
            }
            if (counter2 >= 3) {
                result = 1;
                eyesite = "20/50";
            } else
                result = 0;

            VATestResults vaTestResults = new VATestResults(image, guess, correctanswer, result, date2String, eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(date2String).child("Right Eye").child("Line 4").setValue(vaTestResults);


        } else if (counter == 18) {
            image = 5;
            correctanswer = "PECFD";
            int counter2 = 0;
            String[] correctanswer2 = correctanswer.split("(?!^)");
            for (int i = 0; i < guess2.length; i++) {
                if (correctanswer2[i].equalsIgnoreCase(guess2[i])) {
                    counter2++;
                }
            }
            if (counter2 >= 3) {
                result = 1;
                eyesite = "20/40";
            } else
                result = 0;

            VATestResults vaTestResults = new VATestResults(image, guess, correctanswer, result, date2String, eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(date2String).child("Right Eye").child("Line 5").setValue(vaTestResults);


        } else if (counter == 19) {
            image = 6;
            correctanswer = "EDFCZP";
            int counter2 = 0;
            String[] correctanswer2 = correctanswer.split("(?!^)");
            for (int i = 0; i < guess2.length; i++) {
                if (correctanswer2[i].equalsIgnoreCase(guess2[i])) {
                    counter2++;
                }
            }
            if (counter2 >= 3) {
                result = 1;
                eyesite = "20/30";
            } else
                result = 0;

            VATestResults vaTestResults = new VATestResults(image, guess, correctanswer, result, date2String, eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(date2String).child("Right Eye").child("Line 6").setValue(vaTestResults);


        } else if (counter == 20) {
            image = 7;
            correctanswer = "FELOPZD";
            int counter2 = 0;
            String[] correctanswer2 = correctanswer.split("(?!^)");
            for (int i = 0; i < guess2.length; i++) {
                if (correctanswer2[i].equalsIgnoreCase(guess2[i])) {
                    counter2++;
                }
            }
            if (counter2 >= 3) {
                result = 1;
                eyesite = "20/25";
            } else
                result = 0;

            VATestResults vaTestResults = new VATestResults(image, guess, correctanswer, result, date2String, eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(date2String).child("Right Eye").child("Line 7").setValue(vaTestResults);

        } else if (counter == 21) {
            image = 8;
            correctanswer = "DEFPOTEC";
            int counter2 = 0;
            String[] correctanswer2 = correctanswer.split("(?!^)");
            for (int i = 0; i < guess2.length; i++) {
                if (correctanswer2[i].equalsIgnoreCase(guess2[i])) {
                    counter2++;
                }
            }
            if (counter2 >= 3) {
                result = 1;
                eyesite = "20/20";
            } else
                result = 0;

            VATestResults vaTestResults = new VATestResults(image, guess, correctanswer, result, date2String, eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(date2String).child("Right Eye").child("Line 8").setValue(vaTestResults);

        }


    }
}
