package com.example.lab6_20190212;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class juego2 extends AppCompatActivity {

    private static final int COD_SEL_STORAGE = 200;
    private static final int COD_SEL_IMAGE = 300;

    FirebaseFirestore db;
    StorageReference storageReference;
    String storage_path = "foto/*";

    private Uri image_url;
    String photo = "photo";
    String idd;
    ImageView foto;

    String download_uri = "";
    ProgressDialog progressDialog;

    List<foto> fotitos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego2);
        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        Button agregar =findViewById(R.id.button6);
        ProgressBar progressBar= findViewById(R.id.progress_bar);
        fotitos = new ArrayList<>();
        Adaptador listaAdapter = new Adaptador(fotitos, this);
        RecyclerView recyclerView = findViewById(R.id.fotito);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listaAdapter);

        // Establecer el listener para Firestore una sola vez, no cada vez que se haga clic
        db.collection("segunda").addSnapshotListener((collection, error) -> {
            if (error != null) {
                Log.d("lectura", "Error listening for document changes.");
                return;
            }
            if (collection != null && !collection.isEmpty()) {
                fotitos.clear(); // Limpiar la lista antes de agregar los nuevos datos
                for (QueryDocumentSnapshot document : collection) {
                    foto foto = document.toObject(foto.class);
                    fotitos.add(foto);
                    Log.d("fotitos", foto.getUrl());
                }
                listaAdapter.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
                TextView textView = findViewById(R.id.textView1);
                textView.setText("Total de imagenes seleccionadas: "+fotitos.toArray().length);
            }
        });

        // Listener para el botón de agregar
        agregar.setOnClickListener(view -> {
            uploadPhoto(); // Esta función debería manejar la subida de fotos
        });

        Button jugar = findViewById(R.id.button7);
        jugar.setOnClickListener(view -> {
            Intent intent = new Intent(this, juego23.class);
            startActivity(intent);
        });
    }

    private void uploadPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "Selecciona las fotos"), COD_SEL_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == COD_SEL_IMAGE) {
            ClipData clipData = data.getClipData();
            if (clipData != null) {
                ProgressBar progressBar= findViewById(R.id.progress_bar);
                progressBar.setMax(clipData.getItemCount());
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    Uri imageUri = clipData.getItemAt(i).getUri();
                    subirPhoto(imageUri, i, clipData.getItemCount());
                }
            } else if (data.getData() != null) {
                Uri imageUri = data.getData();
                subirPhoto(imageUri, 0, 1);
            }
        }
    }

    private void subirPhoto(Uri imageUri, int index, int total) {
        ProgressBar progressBar= findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        String fileName = "IMG_" + UUID.randomUUID().toString() + ".jpg";
        String rute_storage_photo = "photos/" + fileName;
        StorageReference reference = storageReference.child(rute_storage_photo);

        reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String downloadUri = uri.toString();
                        Map<String, Object> map = new HashMap<>();
                        map.put("url", downloadUri);
                        db.collection("segunda").document(fileName).set(map);
                        if (index == total - 1) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(juego2.this, "Todas las fotos han sido subidas", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(juego2.this, "Error al cargar foto", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(snapshot -> {
            int currentProgress = (int) (100 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
            progressBar.setProgress(index * (100 / total) + currentProgress / total);
        });
    }

}