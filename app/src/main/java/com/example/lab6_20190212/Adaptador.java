package com.example.lab6_20190212;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    private List<foto> mdata;
    private LayoutInflater minflater;
    private Context context;
    FirebaseFirestore db;

    public Adaptador(List<foto> itemlist, Context context) {
        this.minflater = LayoutInflater.from(context);
        this.context = context;
        this.mdata = itemlist;
    }

    @Override
    public Adaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = minflater.inflate(R.layout.fotito, null);
        return new Adaptador.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    @Override
    public void onBindViewHolder(final Adaptador.ViewHolder holder, final int position) {
        holder.bindData(mdata.get(position));
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView elimina;
        ImageView imagen;
        String docu;
        ViewHolder(View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imagen);
            elimina = itemView.findViewById(R.id.txt);
            elimina.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    foto item = mdata.get(position);
                    db = FirebaseFirestore.getInstance();
                    Query query = db.collection("segunda").whereEqualTo("url", item.getUrl());

                    query.get().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            QuerySnapshot queryDocumentSnapshot = task.getResult();
                            if (!queryDocumentSnapshot.isEmpty()) {
                                // Suponiendo que la URL es única y solo retorna un documento
                                DocumentSnapshot document = queryDocumentSnapshot.getDocuments().get(0);
                                String docId = document.getId();
                                Log.d("ViewHolder",docId);
                                eliminarFotoFirestore(docId, position);  // Llama aquí
                            }
                        } else {
                            Log.d("ViewHolder", "Error obteniendo documentos: ", task.getException());
                        }
                    });
                }
            });
        }

        void bindData(final foto item) {
            if (item != null) {
                Picasso.get().load(item.getUrl()).into(imagen);
            }
        }

    }

    private void eliminarFotoFirestore(String documentId, int position) {
        db = FirebaseFirestore.getInstance();
        db.collection("segunda").document(documentId)
                .delete()
                .addOnSuccessListener(aVoid -> {
                    // Eliminar el elemento de la lista y notificar al adaptador
                    mdata.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, mdata.size());
                    Toast.makeText(context, "Elemento eliminado", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> Toast.makeText(context, "Error al eliminar el elemento", Toast.LENGTH_SHORT).show());
    }
}
