package com.example.lab6_20190212;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    private List<foto> mdata;
    private LayoutInflater minflater;
    private Context context;

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

        ViewHolder(View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imagen);
            elimina = itemView.findViewById(R.id.txt);
            elimina.setOnClickListener(v -> {
                int position = getAdapterPosition();
                foto dona = mdata.get(position);
            });
        }

        void bindData(final foto item) {
            if (item != null) {
                Picasso.get().load(item.getUrl()).into(imagen);
            }
        }

    }
}
