package com.example.firebaseregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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

public class ColourBlindTest extends AppCompatActivity {
private Button nextButton;
private EditText answer;
private ImageView imageView;
private String guess;
private StorageReference storageReference;
private int counter = 0;
DatabaseReference mRootRef = FirebaseDatabase.getInstance("https://newproject-49f95-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
DatabaseReference mpicture = mRootRef.child("answer");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_blind_test);

        nextButton = findViewById(R.id.cbButtonAnswer);

    }
    @Override
    protected void onStart(){
        super.onStart();
        mpicture.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String text =  snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                answer = (EditText) findViewById(R.id.cbanswer);
                mpicture.child("answer").setValue(answer.getText().toString()) ;
                next();

            }

        });

    }
    public void next(){

        imageView = findViewById(R.id.imageView);
        if(counter == 0) {
            counter++;
            storageReference = FirebaseStorage.getInstance().getReference().child("Ishihara Colour Blind Test/answer 16.jpg");
            Task<ListResult> picturelist = storageReference.listAll();
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

        }
    }
//public void next(){
//
//    imageView = findViewById(R.id.imageView);
//    if(counter == 0) {
//        storageReference = FirebaseStorage.getInstance().getReference().child("Ishihara Colour Blind Test/");
//        Task<ListResult> picturelist = storageReference.listAll();
//        picturelist.addOnSuccessListener(new OnSuccessListener<ListResult>() {
//            @Override
//            public void onSuccess(ListResult listResult) {
//                for(StorageReference prefix : listResult.getPrefixes()){
//                    String url = prefix.getDownloadUrl().toString();
//                    System.out.println(url);
//                }
//
//            }
//        });
//
//
//    }
//}
}