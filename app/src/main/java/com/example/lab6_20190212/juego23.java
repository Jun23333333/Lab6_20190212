package com.example.lab6_20190212;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class juego23 extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    List<foto2> fotitos = new ArrayList<>();

    String Url1,Url2,Url3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego23);
        Log.d("testeo", "2");

        db.collection("segunda").addSnapshotListener((collection, error) -> {
            Log.d("testeo", "1");
            if (error != null) {
                Log.d("lectura", "Error listening for document changes.");
                return;
            }
            if (collection != null && !collection.isEmpty()) {
                for (QueryDocumentSnapshot document : collection) {
                    foto2 foto = document.toObject(foto2.class);
                    fotitos.add(foto);
                    Log.d("testeo", foto.getUrl());
                }
            }
        });
        AtomicInteger vol = new AtomicInteger();
        AtomicInteger gan = new AtomicInteger();
        Log.d("testeo","ae");
        if (fotitos.size() >= 3) {
            Url1 = fotitos.get(0).getUrl();
            Url2 = fotitos.get(1).getUrl();
            Url3 = fotitos.get(2).getUrl();}else{
            Url1 = "https://firebasestorage.googleapis.com/v0/b/vertical-reason-402704.appspot.com/o/photos%2FIMG_1acc99da-9c4b-4fa3-b256-9360113d1801.jpg?alt=media&token=1a79f9ab-51ff-4e11-b52d-40d2552b01aa";
            Url2 = "https://firebasestorage.googleapis.com/v0/b/vertical-reason-402704.appspot.com/o/photos%2FIMG_077c47ce-b84e-4db7-9275-db2ca829397e.jpg?alt=media&token=db2919b8-a435-469e-a200-a7299dc9cefe";
            Url3 = "https://firebasestorage.googleapis.com/v0/b/vertical-reason-402704.appspot.com/o/photos%2FIMG_61d72850-9a09-4b8c-9a16-9393118c9ffa.jpg?alt=media&token=e7948309-6b8b-461b-9d1b-c5d3edbe04d3";
        }
        Log.d("testeo","a");
        String Url_f = "https://firebasestorage.googleapis.com/v0/b/vertical-reason-402704.appspot.com/o/Snapshot_1.PNG?alt=media&token=dd44eeb8-1a56-46cd-b75b-9b4a506684b2&_gl=1*19vwuht*_ga*NzE2OTM3NDg2LjE2OTc4NTQxMTY.*_ga_CW55HF8NVT*MTY5OTIzMzAxOS45LjEuMTY5OTIzNDE0MS4xMi4wLjA.";
        ImageView ima1 = findViewById(R.id.ima1);
        ImageView ima2 = findViewById(R.id.ima2);
        ImageView ima3 = findViewById(R.id.ima3);
        ImageView ima4 = findViewById(R.id.ima4);
        ImageView ima5 = findViewById(R.id.ima5);
        ImageView ima6 = findViewById(R.id.ima6);
        Picasso.get().load(Url_f).into(ima1);
        Picasso.get().load(Url_f).into(ima2);
        Picasso.get().load(Url_f).into(ima3);
        Picasso.get().load(Url_f).into(ima4);
        Picasso.get().load(Url_f).into(ima5);
        Picasso.get().load(Url_f).into(ima6);
        Log.d("testeo","b");
        List<String> urlsTemporales = new ArrayList<>();
        urlsTemporales.add(Url1);
        urlsTemporales.add(Url1);
        urlsTemporales.add(Url2);
        urlsTemporales.add(Url2);
        urlsTemporales.add(Url3);
        urlsTemporales.add(Url3);
        TextView ganar = findViewById(R.id.textView2);
        Log.d("testeo","c");
        Collections.shuffle(urlsTemporales);
        Log.d("testeo","d");
        Map<Integer, String> urlsMap = new HashMap<>();
        for (int i = 0; i < urlsTemporales.size(); i++) {
            urlsMap.put(i, urlsTemporales.get(i));
        }
        Log.d("testeo","f");
        ima1.setOnClickListener(view -> {
            if(vol.get() == 3){
                Picasso.get().load(Url_f).into(ima1);
                Picasso.get().load(Url_f).into(ima2);
                Picasso.get().load(Url_f).into(ima3);
                Picasso.get().load(Url_f).into(ima4);
                Picasso.get().load(Url_f).into(ima5);
                Picasso.get().load(Url_f).into(ima6);
                vol.set(0);
            }
            Picasso.get().load(urlsMap.get(0)).into(ima1);
            Log.d("testeo",urlsMap.get(0));
            Log.d("testeo",urlsMap.get(1));
            Log.d("testeo",urlsMap.get(2));
            Log.d("testeo",urlsMap.get(3));
            Log.d("testeo",urlsMap.get(4));
            vol.getAndIncrement();
            if(ima1.getDrawable().equals(ima2.getDrawable())){
                ima1.setVisibility(View.GONE);
                ima2.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima1.getDrawable().equals(ima3.getDrawable())){
                ima1.setVisibility(View.GONE);
                ima3.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima1.getDrawable().equals(ima4.getDrawable())){
                ima1.setVisibility(View.GONE);
                ima4.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima1.getDrawable().equals(ima5.getDrawable())){
                ima1.setVisibility(View.GONE);
                ima5.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima1.getDrawable().equals(ima6.getDrawable())){
                ima1.setVisibility(View.GONE);
                ima6.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(gan.get()==3){
                ganar.setVisibility(View.VISIBLE);
            }
        });

        ima2.setOnClickListener(view -> {
            if(vol.get() == 3){
                Picasso.get().load(Url_f).into(ima1);
                Picasso.get().load(Url_f).into(ima2);
                Picasso.get().load(Url_f).into(ima3);
                Picasso.get().load(Url_f).into(ima4);
                Picasso.get().load(Url_f).into(ima5);
                Picasso.get().load(Url_f).into(ima6);
                vol.set(0);
            }
            Picasso.get().load(urlsMap.get(1)).into(ima2);
            vol.getAndIncrement();
            if(ima2.getDrawable().equals(ima1.getDrawable())){
                ima1.setVisibility(View.GONE);
                ima2.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima2.getDrawable().equals(ima3.getDrawable())){
                ima2.setVisibility(View.GONE);
                ima3.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima2.getDrawable().equals(ima4.getDrawable())){
                ima1.setVisibility(View.GONE);
                ima4.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima2.getDrawable().equals(ima5.getDrawable())){
                ima2.setVisibility(View.GONE);
                ima5.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima2.getDrawable().equals(ima6.getDrawable())){
                ima2.setVisibility(View.GONE);
                ima6.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(gan.get()==3){
                ganar.setVisibility(View.VISIBLE);
            }
        });

        ima3.setOnClickListener(view -> {
            if(vol.get() == 3){
                Picasso.get().load(Url_f).into(ima1);
                Picasso.get().load(Url_f).into(ima2);
                Picasso.get().load(Url_f).into(ima3);
                Picasso.get().load(Url_f).into(ima4);
                Picasso.get().load(Url_f).into(ima5);
                Picasso.get().load(Url_f).into(ima6);
                vol.set(0);
            }
            Picasso.get().load(urlsMap.get(2)).into(ima3);
            vol.getAndIncrement();
            if(ima3.getDrawable().equals(ima2.getDrawable())){
                ima3.setVisibility(View.GONE);
                ima2.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima1.getDrawable().equals(ima3.getDrawable())){
                ima1.setVisibility(View.GONE);
                ima3.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima3.getDrawable().equals(ima4.getDrawable())){
                ima3.setVisibility(View.GONE);
                ima4.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima3.getDrawable().equals(ima5.getDrawable())){
                ima3.setVisibility(View.GONE);
                ima5.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima3.getDrawable().equals(ima6.getDrawable())){
                ima3.setVisibility(View.GONE);
                ima6.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(gan.get()==3){
                ganar.setVisibility(View.VISIBLE);
            }
        });


        ima4.setOnClickListener(view -> {
            if(vol.get() == 3){
                Picasso.get().load(Url_f).into(ima1);
                Picasso.get().load(Url_f).into(ima2);
                Picasso.get().load(Url_f).into(ima3);
                Picasso.get().load(Url_f).into(ima4);
                Picasso.get().load(Url_f).into(ima5);
                Picasso.get().load(Url_f).into(ima6);
                vol.set(0);
            }
            Picasso.get().load(urlsMap.get(3)).into(ima4);
            vol.getAndIncrement();
            if(ima4.getDrawable().equals(ima2.getDrawable())){
                ima4.setVisibility(View.GONE);
                ima2.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima4.getDrawable().equals(ima3.getDrawable())){
                ima4.setVisibility(View.GONE);
                ima3.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima1.getDrawable().equals(ima4.getDrawable())){
                ima1.setVisibility(View.GONE);
                ima4.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima4.getDrawable().equals(ima5.getDrawable())){
                ima4.setVisibility(View.GONE);
                ima5.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima4.getDrawable().equals(ima6.getDrawable())){
                ima4.setVisibility(View.GONE);
                ima6.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(gan.get()==3){
                ganar.setVisibility(View.VISIBLE);
            }
        });

        ima5.setOnClickListener(view -> {
            if(vol.get() == 3){
                Picasso.get().load(Url_f).into(ima1);
                Picasso.get().load(Url_f).into(ima2);
                Picasso.get().load(Url_f).into(ima3);
                Picasso.get().load(Url_f).into(ima4);
                Picasso.get().load(Url_f).into(ima5);
                Picasso.get().load(Url_f).into(ima6);
                vol.set(0);
            }
            Picasso.get().load(urlsMap.get(4)).into(ima5);
            vol.getAndIncrement();
            if(ima5.getDrawable().equals(ima2.getDrawable())){
                ima5.setVisibility(View.GONE);
                ima2.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima5.getDrawable().equals(ima3.getDrawable())){
                ima5.setVisibility(View.GONE);
                ima3.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima5.getDrawable().equals(ima4.getDrawable())){
                ima5.setVisibility(View.GONE);
                ima4.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima1.getDrawable().equals(ima5.getDrawable())){
                ima1.setVisibility(View.GONE);
                ima5.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima5.getDrawable().equals(ima6.getDrawable())){
                ima5.setVisibility(View.GONE);
                ima6.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(gan.get()==3){
                ganar.setVisibility(View.VISIBLE);
            }
        });


        ima6.setOnClickListener(view -> {
            if(vol.get() == 3){
                Picasso.get().load(Url_f).into(ima1);
                Picasso.get().load(Url_f).into(ima2);
                Picasso.get().load(Url_f).into(ima3);
                Picasso.get().load(Url_f).into(ima4);
                Picasso.get().load(Url_f).into(ima5);
                Picasso.get().load(Url_f).into(ima6);
                vol.set(0);
            }
            Picasso.get().load(urlsMap.get(5)).into(ima6);
            vol.getAndIncrement();
            if(ima6.getDrawable().equals(ima2.getDrawable())){
                ima6.setVisibility(View.GONE);
                ima2.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima6.getDrawable().equals(ima3.getDrawable())){
                ima6.setVisibility(View.GONE);
                ima3.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima6.getDrawable().equals(ima4.getDrawable())){
                ima6.setVisibility(View.GONE);
                ima4.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima6.getDrawable().equals(ima5.getDrawable())){
                ima6.setVisibility(View.GONE);
                ima5.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(ima1.getDrawable().equals(ima6.getDrawable())){
                ima1.setVisibility(View.GONE);
                ima6.setVisibility(View.GONE);
                gan.getAndIncrement();
            }
            if(gan.get()==3){
                ganar.setVisibility(View.VISIBLE);
            }
        });

    }

    private void mezcalr(){
        List<String> urlsTemporales = new ArrayList<>();
        urlsTemporales.add(fotitos.get(0).getUrl());
        urlsTemporales.add(fotitos.get(1).getUrl());
        urlsTemporales.add(fotitos.get(2).getUrl());
        urlsTemporales.add(fotitos.get(0).getUrl());
        urlsTemporales.add(fotitos.get(1).getUrl());
        urlsTemporales.add(fotitos.get(2).getUrl());

        Collections.shuffle(urlsTemporales);

        Map<Integer, String> urlsMap = new HashMap<>();
        for (int i = 0; i < urlsTemporales.size(); i++) {
            urlsMap.put(i, urlsTemporales.get(i));
        }
    }

}