package com.example.firebaseregistration;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseregistration.models.Glasses;
import com.example.firebaseregistration.models.GlassesAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ThirdFragment extends Fragment {

    RecyclerView popularGlasses;
    FirebaseFirestore db;

    GlassesAdapter glassesApator;
    List<Glasses> glasses;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View root = inflater.inflate(R.layout.fragment_third, container, false);
        db = FirebaseFirestore.getInstance();
        popularGlasses = root.findViewById(R.id.popularRecycler);

       popularGlasses.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
       glasses = new ArrayList<>();
       glassesApator = new GlassesAdapter(getActivity(),glasses);
       popularGlasses.setAdapter(glassesApator);

        db.collection("glasses_for_shop")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                            Glasses glass = document.toObject(Glasses.class);
                            glasses.add(glass);
                            glassesApator.notifyDataSetChanged();
                            }
                        } else {
                        }
                    }
                });

       return root;
    }
}
