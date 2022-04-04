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
    private int image = 0;
    private String correctanswer = "";
    private ViewFlipper viewFlipper;
    private Date date= new Date();;
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

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;

                loadImages();
                viewFlipper.showNext();
                getValues();

                if(counter == 8){
                    nextButton.setText("Finish");
                }else if(counter == 9){
                    Intent intent= new Intent(VisualAcuityTest.this, EyeTests.class);
                    startActivity(intent);
                }
            }
        });
    }
    public void loadImages(){
        if(counter == 0){

        }
        if (counter == 1) {
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
        }
        else if (counter == 2) {
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
        }
        else if (counter == 3) {
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
        }
        else if (counter == 4) {
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
        }
        else if (counter == 5) {
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
        }
        else if (counter == 6) {
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
        }
        else if (counter == 7) {
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
        }
        else if (counter == 8) {
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
        }

    }

    public void getValues(){
        answer = (EditText) findViewById(R.id.editTextAnswer);
        guess = answer.getText().toString();
        String eyesite = "";
        String [] guess2 = guess.split("(?!^)");
        if(counter == 2){
            image = 1;
            correctanswer = "E";
            if(guess.equalsIgnoreCase(correctanswer)){
                result = 1;
                eyesite = "20/200";
            }else
                result = 0;


            VATestResults vaTestResults = new VATestResults(image,guess,correctanswer,result,date2String,eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child("Line 1").setValue(vaTestResults);

        }else if(counter == 3){
            image = 2;
            correctanswer = "FP";
            int counter2 = 0;
            String [] correctanswer2 = correctanswer.split("(?!^)");
            for(int i = 0;i< guess2.length; i++){
                if(correctanswer2[i].equalsIgnoreCase(guess2[i])){
                    counter2++;
                }
            }
            if(counter2>=2){
                result = 1;
                eyesite = "20/100";
            }else
                result =0;

            VATestResults vaTestResults = new VATestResults(image,guess,correctanswer,result,date2String,eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child("Line 2").setValue(vaTestResults);


        }else if(counter == 4){
            image = 3;
            correctanswer = "TOZ";
            int counter2 = 0;
            String [] correctanswer2 = correctanswer.split("(?!^)");
            for(int i = 0;i< guess2.length; i++){
                if(correctanswer2[i].equalsIgnoreCase(guess2[i])){
                    counter2++;
                }
            }
            if(counter2>=3){
                result = 1;
                eyesite = "20/70";
            }else
                result =0;


            VATestResults vaTestResults = new VATestResults(image,guess,correctanswer,result,date2String,eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child("Line 3").setValue(vaTestResults);


        }else if(counter == 5){
            image = 4;
            correctanswer = "LPED";
            int counter2 = 0;
            String [] correctanswer2 = correctanswer.split("(?!^)");
            for(int i = 0;i< guess2.length; i++){
                if(correctanswer2[i].equalsIgnoreCase(guess2[i])){
                    counter2++;
                }
            }
            if(counter2>=3){
                result = 1;
                eyesite = "20/50";
            }else
                result =0;

            VATestResults vaTestResults = new VATestResults(image,guess,correctanswer,result,date2String,eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child("Line 4").setValue(vaTestResults);


        }else if(counter == 6){
            image = 5;
            correctanswer = "PECFD";
            int counter2 = 0;
            String [] correctanswer2 = correctanswer.split("(?!^)");
            for(int i = 0;i< guess2.length; i++){
                if(correctanswer2[i].equalsIgnoreCase(guess2[i])){
                    counter2++;
                }
            }
            if(counter2>=3){
                result = 1;
                eyesite = "20/40";
            }else
                result =0;

            VATestResults vaTestResults = new VATestResults(image,guess,correctanswer,result,date2String,eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child("Line 5").setValue(vaTestResults);


        }else if(counter == 7){
            image = 6;
            correctanswer = "EDFCZP";
            int counter2 = 0;
            String [] correctanswer2 = correctanswer.split("(?!^)");
            for(int i = 0;i< guess2.length; i++){
                if(correctanswer2[i].equalsIgnoreCase(guess2[i])){
                    counter2++;
                }
            }
            if(counter2>=3){
                result = 1;
                eyesite = "20/30";
            }else
                result =0;

            VATestResults vaTestResults = new VATestResults(image,guess,correctanswer,result,date2String,eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child("Line 6").setValue(vaTestResults);


        }else if(counter == 8){
            image = 7;
            correctanswer = "FELOPZD";
            int counter2 = 0;
            String [] correctanswer2 = correctanswer.split("(?!^)");
            for(int i = 0;i< guess2.length; i++){
                if(correctanswer2[i].equalsIgnoreCase(guess2[i])){
                    counter2++;
                }
            }
            if(counter2>=3){
                result = 1;
                eyesite = "20/25";
            }else
                result =0;

            VATestResults vaTestResults = new VATestResults(image,guess,correctanswer,result,date2String,eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child("Line 7").setValue(vaTestResults);

        }else if(counter == 9){
            image = 8;
            correctanswer = "DEFPOTEC";
            int counter2 = 0;
            String [] correctanswer2 = correctanswer.split("(?!^)");
            for(int i = 0;i< guess2.length; i++){
                if(correctanswer2[i].equalsIgnoreCase(guess2[i])){
                    counter2++;
                }
            }
            if(counter2>=3){
                result = 1;
                eyesite = "20/20";
            }else
                result =0;

            VATestResults vaTestResults = new VATestResults(image,guess,correctanswer,result,date2String,eyesite);

            mNoderef.child("users").child(mUser.getUid()).child("Visual Acuity Test").child("Line 8").setValue(vaTestResults);

        }


    }
}
