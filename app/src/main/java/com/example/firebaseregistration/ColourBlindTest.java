package com.example.firebaseregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
import java.util.List;

public class ColourBlindTest extends AppCompatActivity {
private Button nextButton, homeButton;
private EditText answer;
private ImageView imageView;
private String guess = "" ;
private StorageReference storageReference;
private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://newproject-49f95-default-rtdb.europe-west1.firebasedatabase.app/Images");
private DatabaseReference databaseReference = firebaseDatabase.getReference();
private DatabaseReference imageref = databaseReference.child("Images");
private int counter = 0;
private int result = 0;
private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_blind_test);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        nextButton = findViewById(R.id.cbButtonAnswer);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ColourBlindTest.this, result  , Toast.LENGTH_SHORT).show();
                counter++;
                next();
                viewFlipper.showNext();

             }

        });
//        homeButton = findViewById(R.id.cbHomeButton);
//        homeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(ColourBlindTest.this, MainActivity2.class);
//                startActivity(intent);
//            }
//        });

    }
    public void next(){
            answer = (EditText) findViewById(R.id.cbanswer);
            guess= answer.getText().toString();

//        Toast.makeText(ColourBlindTest.this, guess  , Toast.LENGTH_SHORT).show();
        if(counter == 12) {

            imageView = findViewById(R.id.imageView1);
            storageReference = FirebaseStorage.getInstance().getReference().child("Ishihara Colour Blind Test/answer 16.jpg");

            try {
                File local = File.createTempFile("answer 16", "jpg");

                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);


                            }
                        });


            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(counter == 1){

             imageView = findViewById(R.id.imageView2);
            storageReference = FirebaseStorage.getInstance().getReference().child("Ishihara Colour Blind Test/answer 12.jpg");

            try {
                File local = File.createTempFile("answer 12", "jpg");

                storageReference.getFile(local)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                                imageView.setImageBitmap(bitmap);
                                if(answer.getText().toString().equals("12")){
                                    result++;
                                    Toast.makeText(ColourBlindTest.this, result + "12", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(counter == 2){
            if(guess.equals("12")){
                result++;
                Toast.makeText(ColourBlindTest.this, guess , Toast.LENGTH_LONG).show();

            }else{Toast.makeText(ColourBlindTest.this,"Something wrong with IF",Toast.LENGTH_LONG).show();}
            imageView = findViewById(R.id.imageView3);
            storageReference = FirebaseStorage.getInstance().getReference().child("Ishihara Colour Blind Test/answer 2.jpg");

            try {
                File local = File.createTempFile("answer 2", "jpg");

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
        }else if(counter == 3){
            if(guess.equals("2")){
                result++;
                Toast.makeText(ColourBlindTest.this, guess , Toast.LENGTH_LONG).show();

            }else{Toast.makeText(ColourBlindTest.this,"Something wrong with IF",Toast.LENGTH_LONG).show();}
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
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(counter == 4){
            if(guess.equals("29")){
                result++;
                Toast.makeText(ColourBlindTest.this, guess , Toast.LENGTH_LONG).show();

            }else{Toast.makeText(ColourBlindTest.this,"Something wrong with IF",Toast.LENGTH_LONG).show();}
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
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(counter == 5){
            if(guess.equals("3")){
                result++;
                Toast.makeText(ColourBlindTest.this, guess , Toast.LENGTH_LONG).show();

            }else{Toast.makeText(ColourBlindTest.this,"Something wrong with IF",Toast.LENGTH_LONG).show();}
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
        }else if(counter == 6){
            if(guess.equals("42")){
                result++;
                Toast.makeText(ColourBlindTest.this, guess , Toast.LENGTH_LONG).show();

            }else{Toast.makeText(ColourBlindTest.this,"Something wrong with IF",Toast.LENGTH_LONG).show();}
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
        }else if(counter == 7){
            if(guess.equals("45")){
                result++;
                Toast.makeText(ColourBlindTest.this, guess , Toast.LENGTH_LONG).show();

            }else{Toast.makeText(ColourBlindTest.this,"Something wrong with IF",Toast.LENGTH_LONG).show();}
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
        }else if(counter == 8){
            if(guess.equals("5")){
                result++;
                Toast.makeText(ColourBlindTest.this, guess , Toast.LENGTH_LONG).show();

            }else{Toast.makeText(ColourBlindTest.this,"Something wrong with IF",Toast.LENGTH_LONG).show();}
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
        }else if(counter == 9){
            if(guess.equals("6")){
                result++;
                Toast.makeText(ColourBlindTest.this, guess , Toast.LENGTH_LONG).show();

            }else{Toast.makeText(ColourBlindTest.this,"Something wrong with IF",Toast.LENGTH_LONG).show();}
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
        }else if(counter == 10){
            if(guess.equals("7")){
                result++;
                Toast.makeText(ColourBlindTest.this, guess , Toast.LENGTH_LONG).show();

            }else{Toast.makeText(ColourBlindTest.this,"Something wrong with IF",Toast.LENGTH_LONG).show();}
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
        }else if(counter == 11){
            if(guess.equals("74")){
                result++;
                Toast.makeText(ColourBlindTest.this, guess , Toast.LENGTH_LONG).show();

            }else{Toast.makeText(ColourBlindTest.this,"Something wrong with IF",Toast.LENGTH_LONG).show();}
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
        }else if(counter ==13){
            if(guess.equals("16")){
                result += 1;


            }else{Toast.makeText(ColourBlindTest.this,"Something wrong with IF",Toast.LENGTH_LONG).show();}
        }else{

            Toast.makeText(ColourBlindTest.this, "Test Completed", Toast.LENGTH_LONG).show();

        }

    }
}

