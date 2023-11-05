package com.example.lab6_20190212;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class juego1 extends AppCompatActivity {
    FirebaseFirestore db;
    StorageReference storageReference;
    String storage_path = "foto/*";

    private Uri image_url;
    String photo = "photo";
    String idd;
    ImageView foto;

    String download_uri = "";
    ProgressDialog progressDialog;

    String Url1,Url2,Url3,Url4,Url5,Url6,Url7,Url8, Url9;


    List<foto> fotos = new ArrayList<>();

    private static final int COD_SEL_STORAGE = 200;
    private static final int COD_SEL_IMAGE = 300;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego1);
        Url1 = "https://firebasestorage.googleapis.com/v0/b/vertical-reason-402704.appspot.com/o/foto%2Fpiece_0_0.png?alt=media&token=3f4e18d9-c36c-4212-aa42-1ce5cfc18074&_gl=1*1qj6ub8*_ga*NzE2OTM3NDg2LjE2OTc4NTQxMTY.*_ga_CW55HF8NVT*MTY5OTIwODcxMS42LjEuMTY5OTIwOTI1OS41Ny4wLjA.";
        Url2 = "https://firebasestorage.googleapis.com/v0/b/vertical-reason-402704.appspot.com/o/foto%2Fpiece_0_1.png?alt=media&token=6bd8e0f0-f195-4c3e-8839-f134d542f937&_gl=1*24iwxr*_ga*NzE2OTM3NDg2LjE2OTc4NTQxMTY.*_ga_CW55HF8NVT*MTY5OTIwODcxMS42LjEuMTY5OTIwOTQ0MC4yMS4wLjA.";
        Url3 = "https://firebasestorage.googleapis.com/v0/b/vertical-reason-402704.appspot.com/o/foto%2Fpiece_0_2.png?alt=media&token=16093a0f-5897-464e-9f8a-94ef4f4dd314&_gl=1*lzok3n*_ga*NzE2OTM3NDg2LjE2OTc4NTQxMTY.*_ga_CW55HF8NVT*MTY5OTIwODcxMS42LjEuMTY5OTIwOTQ2Ni42MC4wLjA.";
        Url4 = "https://firebasestorage.googleapis.com/v0/b/vertical-reason-402704.appspot.com/o/foto%2Fpiece_1_0.png?alt=media&token=ec80c956-4e17-41d6-9103-235987fcc1ab&_gl=1*7yhs63*_ga*NzE2OTM3NDg2LjE2OTc4NTQxMTY.*_ga_CW55HF8NVT*MTY5OTIwODcxMS42LjEuMTY5OTIwOTQ4NS40MS4wLjA.";
        Url5 = "https://firebasestorage.googleapis.com/v0/b/vertical-reason-402704.appspot.com/o/foto%2Fpiece_1_1.png?alt=media&token=a2e3192e-d2e3-4baa-bbcf-153ada8f28d0&_gl=1*1n99vzi*_ga*NzE2OTM3NDg2LjE2OTc4NTQxMTY.*_ga_CW55HF8NVT*MTY5OTIwODcxMS42LjEuMTY5OTIwOTUwNC4yMi4wLjA.";
        Url6 = "https://firebasestorage.googleapis.com/v0/b/vertical-reason-402704.appspot.com/o/foto%2Fpiece_1_2.png?alt=media&token=cd60539e-dd13-4939-b8b7-727ee9ff8e67&_gl=1*ju69ov*_ga*NzE2OTM3NDg2LjE2OTc4NTQxMTY.*_ga_CW55HF8NVT*MTY5OTIwODcxMS42LjEuMTY5OTIwOTUxNS4xMS4wLjA.";
        Url7 = "https://firebasestorage.googleapis.com/v0/b/vertical-reason-402704.appspot.com/o/foto%2Fpiece_2_0.png?alt=media&token=3032828c-df1c-4167-badc-25691a57a303&_gl=1*zk6bdm*_ga*NzE2OTM3NDg2LjE2OTc4NTQxMTY.*_ga_CW55HF8NVT*MTY5OTIwODcxMS42LjEuMTY5OTIxMTkwMy41MC4wLjA.";
        Url8 = "https://firebasestorage.googleapis.com/v0/b/vertical-reason-402704.appspot.com/o/foto%2Fpiece_2_2.png?alt=media&token=c0d08b4e-1a91-4d49-bb2e-3274d54b1d53&_gl=1*15qc59d*_ga*NzE2OTM3NDg2LjE2OTc4NTQxMTY.*_ga_CW55HF8NVT*MTY5OTIwODcxMS42LjEuMTY5OTIxMTc4OS4zNi4wLjA.";
        Button subir = findViewById(R.id.button3);
        db = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        storageReference = FirebaseStorage.getInstance().getReference();
        int[] imageViewsIDs = {
                R.id.div0, R.id.div1, R.id.div2, R.id.div3,R.id.div4, R.id.div5, R.id.div6, R.id.div7,R.id.div8,
        };
        Button divi = findViewById(R.id.button5);
        AtomicInteger contadorjuego = new AtomicInteger(8);
        ImageView imagen1 = findViewById(R.id.div0);
        ImageView imagen2 = findViewById(R.id.div1);
        ImageView imagen3 = findViewById(R.id.div2);
        ImageView imagen4 = findViewById(R.id.div3);
        ImageView imagen5 = findViewById(R.id.div4);
        ImageView imagen6 = findViewById(R.id.div5);
        ImageView imagen7 = findViewById(R.id.div6);
        ImageView imagen8 = findViewById(R.id.div7);
        ImageView imagen9 = findViewById(R.id.div8);
        Map<ImageView, String> imageViewUrlMap = new HashMap<>();
        TextView contador = findViewById(R.id.contador);
        ImageView imageView = findViewById(R.id.imageView);
        Button comenzar = findViewById(R.id.button4);
        comenzar.setEnabled(false);
        divi.setEnabled(false);
        subir.setOnClickListener(v -> {
            divi.setEnabled(true);
            uploadPhoto();
            db.collection("foto").addSnapshotListener((collection, error) -> {
                if (error != null) {
                    Log.d("lectura", "Error listening for document changes.");
                    return;
                }
                if (collection != null && !collection.isEmpty()) {
                    for (QueryDocumentSnapshot document : collection) {
                        foto foto = document.toObject(foto.class);
                        String imageUrl = foto.getUrl();
                        Picasso.get().load(imageUrl).into(imageView);
                        imageView.setVisibility(View.VISIBLE);
                    }}});
        });

        contador.setOnClickListener(view -> {
            imagen9.setVisibility(View.INVISIBLE);
            Picasso.get().load(Url8).into(imagen6);
        });
        divi.setOnClickListener(view -> {
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            splitAndUploadImage(bitmap,3,3);
            comenzar.setEnabled(true);
            Toast.makeText(this, "espera unos 10 seg para comenzar xfa", Toast.LENGTH_SHORT).show();
        });

        comenzar.setOnClickListener(view -> {
            imageView.setVisibility(View.GONE);
            Random rand = new Random();
            int numeroAleatorio = rand.nextInt(5) + 1;
            if(numeroAleatorio == 1){
                juego1(imageViewUrlMap);
            } else if (numeroAleatorio == 2) {
                juego2(imageViewUrlMap);
            }
            else if (numeroAleatorio == 3) {
                juego3(imageViewUrlMap);
            }
            else if (numeroAleatorio == 4) {
                juego4(imageViewUrlMap);
            }
            else if (numeroAleatorio == 5) {
                juego5(imageViewUrlMap);
            }

        });

        TextView gana = findViewById(R.id.contador2);



        imagen1.setOnClickListener(view -> {
            if(imageViewUrlMap.get(imagen2).equals("1")){
                imageViewUrlMap.put(imagen2,imageViewUrlMap.get(imagen1));
                Picasso.get().load(imageViewUrlMap.get(imagen1)).into(imagen2);
                imagen2.setVisibility(View.VISIBLE);
                imagen1.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen1,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));

            }
            if(imageViewUrlMap.get(imagen4).equals("1")){
                imageViewUrlMap.put(imagen4,imageViewUrlMap.get(imagen1));
                Picasso.get().load(imageViewUrlMap.get(imagen1)).into(imagen4);
                imagen4.setVisibility(View.VISIBLE);
                imagen1.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen1,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen1).equals(Url1)
                    && imageViewUrlMap.get(imagen2).equals(Url2)
                    && imageViewUrlMap.get(imagen3).equals(Url3)
                    && imageViewUrlMap.get(imagen4).equals(Url4)
                    && imageViewUrlMap.get(imagen5).equals(Url5)
                    && imageViewUrlMap.get(imagen6).equals(Url6)
                    && imageViewUrlMap.get(imagen7).equals(Url7)
                    && imageViewUrlMap.get(imagen8).equals(Url8)){
                Toast.makeText(this, "Culminaste el juego", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        });

        imagen2.setOnClickListener(view -> {
            if(imageViewUrlMap.get(imagen1).equals("1")){
                imageViewUrlMap.put(imagen1,imageViewUrlMap.get(imagen2));
                Picasso.get().load(imageViewUrlMap.get(imagen2)).into(imagen1);
                imagen1.setVisibility(View.VISIBLE);
                imagen2.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen2,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen5).equals("1")){
                imageViewUrlMap.put(imagen5,imageViewUrlMap.get(imagen2));
                Picasso.get().load(imageViewUrlMap.get(imagen2)).into(imagen5);
                imagen5.setVisibility(View.VISIBLE);
                imagen2.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen2,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen3).equals("1")){
                imageViewUrlMap.put(imagen3,imageViewUrlMap.get(imagen2));
                Picasso.get().load(imageViewUrlMap.get(imagen2)).into(imagen3);
                imagen3.setVisibility(View.VISIBLE);
                imagen2.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen2,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen1).equals(Url1)
                    && imageViewUrlMap.get(imagen2).equals(Url2)
                    && imageViewUrlMap.get(imagen3).equals(Url3)
                    && imageViewUrlMap.get(imagen4).equals(Url4)
                    && imageViewUrlMap.get(imagen5).equals(Url5)
                    && imageViewUrlMap.get(imagen6).equals(Url6)
                    && imageViewUrlMap.get(imagen7).equals(Url7)
                    && imageViewUrlMap.get(imagen8).equals(Url8)){
                Toast.makeText(this, "Culminaste el juego", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        });


        imagen3.setOnClickListener(view -> {
            if(imageViewUrlMap.get(imagen2).equals("1")){
                imageViewUrlMap.put(imagen2,imageViewUrlMap.get(imagen3));
                Picasso.get().load(imageViewUrlMap.get(imagen3)).into(imagen2);
                imagen2.setVisibility(View.VISIBLE);
                imagen3.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen3,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen6).equals("1")){
                imageViewUrlMap.put(imagen6,imageViewUrlMap.get(imagen3));
                Picasso.get().load(imageViewUrlMap.get(imagen3)).into(imagen6);
                imagen6.setVisibility(View.VISIBLE);
                imagen3.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen3,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen1).equals(Url1)
                    && imageViewUrlMap.get(imagen2).equals(Url2)
                    && imageViewUrlMap.get(imagen3).equals(Url3)
                    && imageViewUrlMap.get(imagen4).equals(Url4)
                    && imageViewUrlMap.get(imagen5).equals(Url5)
                    && imageViewUrlMap.get(imagen6).equals(Url6)
                    && imageViewUrlMap.get(imagen7).equals(Url7)
                    && imageViewUrlMap.get(imagen8).equals(Url8)){
                Toast.makeText(this, "Culminaste el juego", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        });

        imagen4.setOnClickListener(view -> {
            if(imageViewUrlMap.get(imagen1).equals("1")){
                imageViewUrlMap.put(imagen1,imageViewUrlMap.get(imagen4));
                Picasso.get().load(imageViewUrlMap.get(imagen4)).into(imagen1);
                imagen1.setVisibility(View.VISIBLE);
                imagen4.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen4,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen5).equals("1")){
                imageViewUrlMap.put(imagen5,imageViewUrlMap.get(imagen4));
                Picasso.get().load(imageViewUrlMap.get(imagen4)).into(imagen5);
                imagen5.setVisibility(View.VISIBLE);
                imagen4.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen4,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen7).equals("1")){
                imageViewUrlMap.put(imagen7,imageViewUrlMap.get(imagen4));
                Picasso.get().load(imageViewUrlMap.get(imagen4)).into(imagen7);
                imagen7.setVisibility(View.VISIBLE);
                imagen4.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen4,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen1).equals(Url1)
                    && imageViewUrlMap.get(imagen2).equals(Url2)
                    && imageViewUrlMap.get(imagen3).equals(Url3)
                    && imageViewUrlMap.get(imagen4).equals(Url4)
                    && imageViewUrlMap.get(imagen5).equals(Url5)
                    && imageViewUrlMap.get(imagen6).equals(Url6)
                    && imageViewUrlMap.get(imagen7).equals(Url7)
                    && imageViewUrlMap.get(imagen8).equals(Url8)){
                Toast.makeText(this, "Culminaste el juego", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        });


        imagen5.setOnClickListener(view -> {
            if(imageViewUrlMap.get(imagen2).equals("1")){
                imageViewUrlMap.put(imagen2,imageViewUrlMap.get(imagen5));
                Picasso.get().load(imageViewUrlMap.get(imagen5)).into(imagen2);
                imagen2.setVisibility(View.VISIBLE);
                imagen5.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen5,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen4).equals("1")){
                imageViewUrlMap.put(imagen4,imageViewUrlMap.get(imagen5));
                Picasso.get().load(imageViewUrlMap.get(imagen5)).into(imagen4);
                imagen4.setVisibility(View.VISIBLE);
                imagen5.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen5,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen6).equals("1")){
                imageViewUrlMap.put(imagen6,imageViewUrlMap.get(imagen5));
                Picasso.get().load(imageViewUrlMap.get(imagen5)).into(imagen6);
                imagen6.setVisibility(View.VISIBLE);
                imagen5.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen5,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen8).equals("1")){
                imageViewUrlMap.put(imagen8,imageViewUrlMap.get(imagen5));
                Picasso.get().load(imageViewUrlMap.get(imagen5)).into(imagen8);
                imagen8.setVisibility(View.VISIBLE);
                imagen5.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen5,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen1).equals(Url1)
                    && imageViewUrlMap.get(imagen2).equals(Url2)
                    && imageViewUrlMap.get(imagen3).equals(Url3)
                    && imageViewUrlMap.get(imagen4).equals(Url4)
                    && imageViewUrlMap.get(imagen5).equals(Url5)
                    && imageViewUrlMap.get(imagen6).equals(Url6)
                    && imageViewUrlMap.get(imagen7).equals(Url7)
                    && imageViewUrlMap.get(imagen8).equals(Url8)){
                Toast.makeText(this, "Culminaste el juego", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        });

        imagen6.setOnClickListener(view -> {
            if(imageViewUrlMap.get(imagen3).equals("1")){
                imageViewUrlMap.put(imagen3,imageViewUrlMap.get(imagen6));
                Picasso.get().load(imageViewUrlMap.get(imagen6)).into(imagen3);
                imagen3.setVisibility(View.VISIBLE);
                imagen6.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen6,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen5).equals("1")){
                imageViewUrlMap.put(imagen5,imageViewUrlMap.get(imagen6));
                Picasso.get().load(imageViewUrlMap.get(imagen6)).into(imagen5);
                imagen5.setVisibility(View.VISIBLE);
                imagen6.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen6,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen9).equals("1")){
                imageViewUrlMap.put(imagen9,imageViewUrlMap.get(imagen6));
                Picasso.get().load(imageViewUrlMap.get(imagen6)).into(imagen9);
                imagen9.setVisibility(View.VISIBLE);
                imagen6.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen6,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen1).equals(Url1)
                    && imageViewUrlMap.get(imagen2).equals(Url2)
                    && imageViewUrlMap.get(imagen3).equals(Url3)
                    && imageViewUrlMap.get(imagen4).equals(Url4)
                    && imageViewUrlMap.get(imagen5).equals(Url5)
                    && imageViewUrlMap.get(imagen6).equals(Url6)
                    && imageViewUrlMap.get(imagen7).equals(Url7)
                    && imageViewUrlMap.get(imagen8).equals(Url8)){
                Toast.makeText(this, "Culminaste el juego", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        });

        imagen7.setOnClickListener(view -> {
            if(imageViewUrlMap.get(imagen4).equals("1")){
                imageViewUrlMap.put(imagen4,imageViewUrlMap.get(imagen7));
                Picasso.get().load(imageViewUrlMap.get(imagen7)).into(imagen4);
                imagen4.setVisibility(View.VISIBLE);
                imagen7.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen7,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen8).equals("1")){
                imageViewUrlMap.put(imagen8,imageViewUrlMap.get(imagen7));
                Picasso.get().load(imageViewUrlMap.get(imagen7)).into(imagen8);
                imagen8.setVisibility(View.VISIBLE);
                imagen7.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen7,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen1).equals(Url1)
                    && imageViewUrlMap.get(imagen2).equals(Url2)
                    && imageViewUrlMap.get(imagen3).equals(Url3)
                    && imageViewUrlMap.get(imagen4).equals(Url4)
                    && imageViewUrlMap.get(imagen5).equals(Url5)
                    && imageViewUrlMap.get(imagen6).equals(Url6)
                    && imageViewUrlMap.get(imagen7).equals(Url7)
                    && imageViewUrlMap.get(imagen8).equals(Url8)){
                Toast.makeText(this, "Culminaste el juego", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        });

        imagen8.setOnClickListener(view -> {
            if(imageViewUrlMap.get(imagen9).equals("1")){
                imageViewUrlMap.put(imagen9,imageViewUrlMap.get(imagen8));
                Picasso.get().load(imageViewUrlMap.get(imagen8)).into(imagen9);
                imagen9.setVisibility(View.VISIBLE);
                imagen8.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen8,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen7).equals("1")){
                imageViewUrlMap.put(imagen7,imageViewUrlMap.get(imagen8));
                Picasso.get().load(imageViewUrlMap.get(imagen8)).into(imagen7);
                imagen7.setVisibility(View.VISIBLE);
                imagen8.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen8,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen5).equals("1")){
                imageViewUrlMap.put(imagen5,imageViewUrlMap.get(imagen8));
                Picasso.get().load(imageViewUrlMap.get(imagen8)).into(imagen5);
                imagen5.setVisibility(View.VISIBLE);
                imagen8.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen8,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));

            }
            if(imageViewUrlMap.get(imagen1).equals(Url1)
                    && imageViewUrlMap.get(imagen2).equals(Url2)
                    && imageViewUrlMap.get(imagen3).equals(Url3)
                    && imageViewUrlMap.get(imagen4).equals(Url4)
                    && imageViewUrlMap.get(imagen5).equals(Url5)
                    && imageViewUrlMap.get(imagen6).equals(Url6)
                    && imageViewUrlMap.get(imagen7).equals(Url7)
                    && imageViewUrlMap.get(imagen8).equals(Url8)){
                Toast.makeText(this, "Culminaste el juego", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        });

        imagen9.setOnClickListener(view -> {
            if(imageViewUrlMap.get(imagen8).equals("1")){
                imageViewUrlMap.put(imagen8,imageViewUrlMap.get(imagen9));
                Picasso.get().load(imageViewUrlMap.get(imagen9)).into(imagen8);
                imagen8.setVisibility(View.VISIBLE);
                imagen9.setVisibility(View.INVISIBLE);
                imageViewUrlMap.put(imagen9,"1");
                contadorjuego.getAndIncrement();
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen6).equals("1")){
                Log.d("tmr","A");
                imageViewUrlMap.put(imagen6,imageViewUrlMap.get(imagen9));
                Log.d("tmr","b");
                Picasso.get().load(imageViewUrlMap.get(imagen9)).into(imagen6);
                Log.d("tmr","c");
                imagen6.setVisibility(View.VISIBLE);
                Log.d("tmr","d");
                imagen9.setVisibility(View.INVISIBLE);
                Log.d("tmr","e");
                imageViewUrlMap.put(imagen9,"1");
                Log.d("tmr","f");
                contadorjuego.getAndIncrement();
                Log.d("tmr","g");
                contador.setText(String.valueOf(contadorjuego.get()));
            }
            if(imageViewUrlMap.get(imagen1).equals(Url1)
                    && imageViewUrlMap.get(imagen2).equals(Url2)
                    && imageViewUrlMap.get(imagen3).equals(Url3)
                    && imageViewUrlMap.get(imagen4).equals(Url4)
                    && imageViewUrlMap.get(imagen5).equals(Url5)
                    && imageViewUrlMap.get(imagen6).equals(Url6)
                    && imageViewUrlMap.get(imagen7).equals(Url7)
                    && imageViewUrlMap.get(imagen8).equals(Url8)){
                Toast.makeText(this, "Culminaste el juego", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();
        return data;
    }

    public void splitAndUploadImage(Bitmap image, int rows, int cols) {
        int pieceWidth = image.getWidth() / cols;
        int pieceHeight = image.getHeight() / rows;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                int offsetX = y * pieceWidth;
                int offsetY = x * pieceHeight;
                Bitmap piece = Bitmap.createBitmap(image, offsetX, offsetY, pieceWidth, pieceHeight);
                byte[] data = bitmapToByteArray(piece);
                String path = "foto/piece_" + x + "_" + y + ".png";
                uploadImagePiece(data, path);
            }
        }
    }

    public void uploadImagePiece(byte[] data, String path) {
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pieceRef = storageRef.child(path);
        UploadTask uploadTask = pieceRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
            }
        });
    }
    private void uploadPhoto() {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i, COD_SEL_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if (requestCode == COD_SEL_IMAGE){
                image_url = data.getData();
                subirPhoto(image_url);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void subirPhoto(Uri image_url) {
        progressDialog.setMessage("Subiendo foto");
        progressDialog.show();
        String rute_storage_photo = storage_path+"a";
        Log.d("abssd", rute_storage_photo);
        StorageReference reference = storageReference.child(rute_storage_photo);
        reference.putFile(image_url).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());
                if (uriTask.isSuccessful()){
                    uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            download_uri = uri.toString();
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("url",download_uri);
                            db.collection("foto").document("1").set(map);
                            Toast.makeText(juego1.this, "Foto actualizada", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(juego1.this, "Error al cargar foto", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void juego1(Map<ImageView, String> imageViewUrlMap){
        ImageView imagen1 = findViewById(R.id.div0);
        ImageView imagen2 = findViewById(R.id.div1);
        ImageView imagen3 = findViewById(R.id.div2);
        ImageView imagen4 = findViewById(R.id.div3);
        ImageView imagen5 = findViewById(R.id.div4);
        ImageView imagen6 = findViewById(R.id.div5);
        ImageView imagen7 = findViewById(R.id.div6);
        ImageView imagen8 = findViewById(R.id.div7);
        ImageView imagen9 = findViewById(R.id.div8);
        Picasso.get().load(Url1).into(imagen1);
        Picasso.get().load(Url2).into(imagen2);
        Picasso.get().load(Url3).into(imagen3);
        Picasso.get().load(Url7).into(imagen4);
        Picasso.get().load(Url6).into(imagen5);
        Picasso.get().load(Url5).into(imagen7);
        Picasso.get().load(Url4).into(imagen8);
        Picasso.get().load(Url8).into(imagen9);
        imageViewUrlMap.put(imagen1, Url1);
        imageViewUrlMap.put(imagen2, Url2);
        imageViewUrlMap.put(imagen3, Url3);
        imageViewUrlMap.put(imagen4, Url7);
        imageViewUrlMap.put(imagen5, Url6);
        imageViewUrlMap.put(imagen6, "1");
        imageViewUrlMap.put(imagen7, Url5);
        imageViewUrlMap.put(imagen8, Url4);
        imageViewUrlMap.put(imagen9, Url8);
        TextView contador = findViewById(R.id.contador);
        contador.setText("0");
    }
    private void juego2(Map<ImageView, String> imageViewUrlMap){
        ImageView imagen1 = findViewById(R.id.div0);
        ImageView imagen2 = findViewById(R.id.div1);
        ImageView imagen3 = findViewById(R.id.div2);
        ImageView imagen4 = findViewById(R.id.div3);
        ImageView imagen5 = findViewById(R.id.div4);
        ImageView imagen6 = findViewById(R.id.div5);
        ImageView imagen7 = findViewById(R.id.div6);
        ImageView imagen8 = findViewById(R.id.div7);
        ImageView imagen9 = findViewById(R.id.div8);
        Picasso.get().load(Url1).into(imagen1);
        Picasso.get().load(Url5).into(imagen2);
        Picasso.get().load(Url2).into(imagen3);
        Picasso.get().load(Url7).into(imagen4);
        Picasso.get().load(Url4).into(imagen5);
        Picasso.get().load(Url8).into(imagen7);
        Picasso.get().load(Url3).into(imagen6);
        Picasso.get().load(Url6).into(imagen9);
        imageViewUrlMap.put(imagen1, Url1);
        imageViewUrlMap.put(imagen2, Url5);
        imageViewUrlMap.put(imagen3, Url2);
        imageViewUrlMap.put(imagen4, Url7);
        imageViewUrlMap.put(imagen5, Url4);
        imageViewUrlMap.put(imagen6, Url3);
        imageViewUrlMap.put(imagen7, Url8);
        imageViewUrlMap.put(imagen8, "1");
        imageViewUrlMap.put(imagen9, Url6);
        TextView contador = findViewById(R.id.contador);
        contador.setText("0");
    }
    private void juego3(Map<ImageView, String> imageViewUrlMap){
        ImageView imagen1 = findViewById(R.id.div0);
        ImageView imagen2 = findViewById(R.id.div1);
        ImageView imagen3 = findViewById(R.id.div2);
        ImageView imagen4 = findViewById(R.id.div3);
        ImageView imagen5 = findViewById(R.id.div4);
        ImageView imagen6 = findViewById(R.id.div5);
        ImageView imagen7 = findViewById(R.id.div6);
        ImageView imagen8 = findViewById(R.id.div7);
        ImageView imagen9 = findViewById(R.id.div8);
        Picasso.get().load(Url5).into(imagen1);
        Picasso.get().load(Url8).into(imagen8);
        Picasso.get().load(Url2).into(imagen3);
        Picasso.get().load(Url1).into(imagen4);
        Picasso.get().load(Url4).into(imagen5);
        Picasso.get().load(Url7).into(imagen7);
        Picasso.get().load(Url3).into(imagen6);
        Picasso.get().load(Url6).into(imagen9);
        imageViewUrlMap.put(imagen1, Url5);
        imageViewUrlMap.put(imagen2, "1");
        imageViewUrlMap.put(imagen3, Url2);
        imageViewUrlMap.put(imagen4, Url1);
        imageViewUrlMap.put(imagen5, Url4);
        imageViewUrlMap.put(imagen6, Url3);
        imageViewUrlMap.put(imagen7, Url7);
        imageViewUrlMap.put(imagen8, Url8);
        imageViewUrlMap.put(imagen9, Url6);
        TextView contador = findViewById(R.id.contador);
        contador.setText("0");
    }

    private void juego4(Map<ImageView, String> imageViewUrlMap){
        ImageView imagen1 = findViewById(R.id.div0);
        ImageView imagen2 = findViewById(R.id.div1);
        ImageView imagen3 = findViewById(R.id.div2);
        ImageView imagen4 = findViewById(R.id.div3);
        ImageView imagen5 = findViewById(R.id.div4);
        ImageView imagen6 = findViewById(R.id.div5);
        ImageView imagen7 = findViewById(R.id.div6);
        ImageView imagen8 = findViewById(R.id.div7);
        ImageView imagen9 = findViewById(R.id.div8);
        Picasso.get().load(Url8).into(imagen1);
        Picasso.get().load(Url5).into(imagen8);
        Picasso.get().load(Url2).into(imagen3);
        Picasso.get().load(Url1).into(imagen4);
        Picasso.get().load(Url4).into(imagen5);
        Picasso.get().load(Url7).into(imagen7);
        Picasso.get().load(Url3).into(imagen6);
        Picasso.get().load(Url6).into(imagen9);
        imageViewUrlMap.put(imagen1, Url8);
        imageViewUrlMap.put(imagen2, "1");
        imageViewUrlMap.put(imagen3, Url2);
        imageViewUrlMap.put(imagen4, Url1);
        imageViewUrlMap.put(imagen5, Url4);
        imageViewUrlMap.put(imagen6, Url3);
        imageViewUrlMap.put(imagen7, Url7);
        imageViewUrlMap.put(imagen8, Url5);
        imageViewUrlMap.put(imagen9, Url6);
        TextView contador = findViewById(R.id.contador);
        contador.setText("0");
    }

    private void juego5(Map<ImageView, String> imageViewUrlMap){
        ImageView imagen1 = findViewById(R.id.div0);
        ImageView imagen2 = findViewById(R.id.div1);
        ImageView imagen3 = findViewById(R.id.div2);
        ImageView imagen4 = findViewById(R.id.div3);
        ImageView imagen5 = findViewById(R.id.div4);
        ImageView imagen6 = findViewById(R.id.div5);
        ImageView imagen7 = findViewById(R.id.div6);
        ImageView imagen8 = findViewById(R.id.div7);
        ImageView imagen9 = findViewById(R.id.div8);
        Picasso.get().load(Url5).into(imagen1);
        Picasso.get().load(Url8).into(imagen8);
        Picasso.get().load(Url1).into(imagen3);
        Picasso.get().load(Url2).into(imagen4);
        Picasso.get().load(Url4).into(imagen5);
        Picasso.get().load(Url7).into(imagen7);
        Picasso.get().load(Url3).into(imagen6);
        Picasso.get().load(Url6).into(imagen9);
        imageViewUrlMap.put(imagen1, Url5);
        imageViewUrlMap.put(imagen2, "1");
        imageViewUrlMap.put(imagen3, Url1);
        imageViewUrlMap.put(imagen4, Url2);
        imageViewUrlMap.put(imagen5, Url4);
        imageViewUrlMap.put(imagen6, Url3);
        imageViewUrlMap.put(imagen7, Url7);
        imageViewUrlMap.put(imagen8, Url8);
        imageViewUrlMap.put(imagen9, Url6);
        TextView contador = findViewById(R.id.contador);
        contador.setText("0");
    }
}