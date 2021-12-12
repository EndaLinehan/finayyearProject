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
private Button nextButton;
private EditText answer;
private ImageView imageView;
private String guess;
private StorageReference storageReference;
//private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://newproject-49f95-default-rtdb.europe-west1.firebasedatabase.app/Images");
//private DatabaseReference databaseReference = firebaseDatabase.getReference();
//private DatabaseReference imageref = databaseReference.child("Images");
private int counter = 0;
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
                counter++;
//                answer = (EditText) findViewById(R.id.cbanswer);
                next();
                viewFlipper.showNext();

             }

        });
    }
    public void next(){

        Toast.makeText(ColourBlindTest.this, counter + "", Toast.LENGTH_SHORT).show();

        if(counter == 0) {
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
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(counter == 1){
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
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(counter == 2){
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
        }else{
            Toast.makeText(ColourBlindTest.this, "Test Completed", Toast.LENGTH_LONG).show();
            Intent intent= new Intent(ColourBlindTest.this, HomePage.class);
            startActivity(intent);
        }

    }
}

