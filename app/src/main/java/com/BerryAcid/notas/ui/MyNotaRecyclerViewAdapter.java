package com.BerryAcid.notas.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.BerryAcid.notas.NuevaNotaDialogViewModel;
import com.BerryAcid.notas.db.entity.NotaEntity;
import com.BerryAcid.notas.R;

import java.util.List;

public class MyNotaRecyclerViewAdapter extends RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder> {

    private List<NotaEntity> mValues;
    private Context ctx;
    private NuevaNotaDialogViewModel viewModel;

    public MyNotaRecyclerViewAdapter(List<NotaEntity> items, Context ctx) {
        mValues = items;
        this.ctx = ctx;
        viewModel = ViewModelProviders.of((AppCompatActivity)ctx).get(NuevaNotaDialogViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.txtViewTitulo.setText(holder.mItem.getTitulo());
        holder.txtViewContenido.setText(holder.mItem.getContenido());


        if(holder.mItem.isFavorita()){
            holder.ivFavoritta.setImageResource(R.drawable.ic_baseline_star_24);
        }

        holder.ivFavoritta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(holder.mItem.isFavorita()){
                    holder.mItem.setFavorita(false);
                    holder.ivFavoritta.setImageResource(R.drawable.ic_baseline_star_border_24);
                }else {
                    holder.mItem.setFavorita(true);
                    holder.ivFavoritta.setImageResource(R.drawable.ic_baseline_star_24);
                }

                viewModel.updateNota(holder.mItem);
            }
        });


        holder.ivDeleteNote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                holder.mItem.getId();
                viewModel.deleteNota(holder.mItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNuevasNotas(List<NotaEntity> nuevasNotas){
        this.mValues = nuevasNotas;
        notifyDataSetChanged();;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtViewTitulo;
        public final TextView txtViewContenido;
        public final ImageView ivFavoritta, ivDeleteNote;
        public NotaEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtViewTitulo = view.findViewById(R.id.textViewTitulo);
            txtViewContenido = view.findViewById(R.id.textViewContenido);
            ivFavoritta = view.findViewById(R.id.imageViewFavorita);
            ivDeleteNote = view.findViewById(R.id.imageViewDeleteNote);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + txtViewTitulo.getText() + "'";
        }
    }
}