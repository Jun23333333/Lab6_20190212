package com.example.lab6_20190212;

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
    FirebaseFirestore db;

    List<foto> fotitos = new ArrayList<>();

    String Url1,Url2,Url3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego23);
        db = FirebaseFirestore.getInstance();

        db.collection("segunda").addSnapshotListener((collection, error) -> {
            if (error != null) {
                Log.d("lectura", "Error listening for document changes.");
                return;
            }
            if (collection != null && !collection.isEmpty()) {
                for (QueryDocumentSnapshot document : collection) {
                    foto foto = document.toObject(foto.class);
                    fotitos.add(foto);
                    Log.d("fotitos", foto.getUrl());
                }
            }
        });
        Log.d("testeo","avbs");
        AtomicInteger vol = new AtomicInteger();
        AtomicInteger gan = new AtomicInteger();
        Url1 = fotitos.get(0).getUrl();
        Url2 = fotitos.get(1).getUrl();
        Url3 = fotitos.get(2).getUrl();
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
        urlsTemporales.add(fotitos.get(0).getUrl());
        urlsTemporales.add(fotitos.get(1).getUrl());
        urlsTemporales.add(fotitos.get(2).getUrl());
        urlsTemporales.add(fotitos.get(0).getUrl());
        urlsTemporales.add(fotitos.get(1).getUrl());
        urlsTemporales.add(fotitos.get(2).getUrl());
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