package com.example.firebaseregistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.firebaseregistration.models.VATestResults;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Store extends AppCompatActivity implements OnChartGestureListener, OnChartValueSelectedListener, IAxisValueFormatter {
    private DatabaseReference mDatabase;
    private FirebaseUser mUser;
    private FirebaseDatabase database;
    private String result ="";
    private List<VATestResults> vatestResults = new ArrayList<>();
    private List<VATestResults> vatestResults2 = new ArrayList<>();;
    private List<String> items = new ArrayList<>();
    private List<String> finalresult = new ArrayList<>();
    private VATestResults vaTestResults, vaTestResults2;
    private LineChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        barChart = findViewById(R.id.barChart);
        barChart.setOnChartGestureListener(Store.this);
        barChart.setOnChartValueSelectedListener(Store.this);


        database = FirebaseDatabase.getInstance("https://newproject-49f95-default-rtdb.europe-west1.firebasedatabase.app/");
        mDatabase = database.getReference();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        mDatabase.child("users").child(mUser.getUid()).child("Visual Acuity Test").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> testResults = snapshot.getChildren();

                for(DataSnapshot ds: testResults){
                    String child = ds.getKey();
                    items.add(child);
//                    Toast.makeText(Store.this, items., Toast.LENGTH_SHORT).show();
                }

                for(String item:items){
                    result = null;
//                    Toast.makeText(Store.this, item.toString(), Toast.LENGTH_SHORT).show();
                    mDatabase.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(item).child("Left Eye").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Iterable<DataSnapshot> testResults = snapshot.getChildren();

                            for(DataSnapshot ds: testResults){
                                vaTestResults = ds.getValue(VATestResults.class);

                                if(vaTestResults.getResult() == 1) {
                                    result = vaTestResults.getEyesiteResult();
                                    vatestResults.add(vaTestResults);
                                }else{
                                    result = "not Valid";
                                }
//                                Toast.makeText(Store.this, result, Toast.LENGTH_SHORT).show();
                                //                                getbestEyeste(result,vaTestResults);
                            }
//                            Toast.makeText(Store.this, item, Toast.LENGTH_SHORT).show();
//                            Toast.makeText(Store.this, result, Toast.LENGTH_SHORT).show();


                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });
                }

                for(String item:items){
                    result = null;
//                    Toast.makeText(Store.this, item.toString(), Toast.LENGTH_SHORT).show();
                    mDatabase.child("users").child(mUser.getUid()).child("Visual Acuity Test").child(item).child("Right Eye").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Iterable<DataSnapshot> testResults = snapshot.getChildren();

                            for(DataSnapshot ds: testResults){
                                vaTestResults2 = ds.getValue(VATestResults.class);

                                if(vaTestResults2.getResult() == 1) {
                                    result = vaTestResults2.getEyesiteResult();
                                    vatestResults2.add(vaTestResults2);
                                }else{
                                    result = "not Valid";
                                }
//                                Toast.makeText(Store.this, result, Toast.LENGTH_SHORT).show();
                                //                                getbestEyeste(result,vaTestResults);
                            }
//                            Toast.makeText(Store.this, item, Toast.LENGTH_SHORT).show();
//                            Toast.makeText(Store.this, result, Toast.LENGTH_SHORT).show();


                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Toast.makeText(this, items.get(1), Toast.LENGTH_SHORT).show();

        ArrayList<Entry> xValue = new ArrayList();
        xValue.add(new Entry(0, 20f));
        xValue.add(new Entry(1, 25f));
        xValue.add(new Entry(2, 30f));
        xValue.add(new Entry(3, 40f));
        xValue.add(new Entry(4, 50f));
        xValue.add(new Entry(5, 70f));
        xValue.add(new Entry(6, 100f));
        xValue.add(new Entry(7, 200f));

        ArrayList<Entry> yValue = new ArrayList();
        yValue.add(new Entry(0, 20f));
        yValue.add(new Entry(1, 25f));
        yValue.add(new Entry(2, 30f));
        yValue.add(new Entry(3, 40f));
        yValue.add(new Entry(4, 40f));
        yValue.add(new Entry(5, 30f));
        yValue.add(new Entry(6, 25f));
        yValue.add(new Entry(7, 20f));


        LineDataSet set1 = new LineDataSet(xValue, "DataSat1");
        set1.setFillAlpha(110);

        LineDataSet set2 = new LineDataSet(yValue, "DataSat2");
        set1.setFillAlpha(200);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);

        LineData data = new LineData(dataSets);

        barChart.setData(data);
        XAxis xAxis = barChart.getXAxis();
        Toast.makeText(this, String.valueOf(items.size()), Toast.LENGTH_SHORT).show();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return super.getFormattedValue(value, axis);
            }
        } );




    }




    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return "null";
    }


}