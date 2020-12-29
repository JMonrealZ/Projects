package com.example.recyclerview;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

//import com.example.recyclerview.dummy.DummyContent.DummyItem;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Restaurante}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyRestaurateRecyclerViewAdapter extends RecyclerView.Adapter<MyRestaurateRecyclerViewAdapter.ViewHolder> {

    private final List<Restaurante> mValues;
    private Context context;

    public MyRestaurateRecyclerViewAdapter(Context context, List<Restaurante> items) {
        mValues = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {//TODO: Elemento que hace el dibujado de cada uno de la lista
        holder.mItem = mValues.get(position); //Variable tipo Restaurante

        holder.tvNombre.setText(holder.mItem.getNombre());
        //holder.tvDireccion.setText(holder.mItem.getDireccion().toString());
        holder.tvDireccion.setText(holder.mItem.getDireccion());
        holder.rbCalificacion.setRating(holder.mItem.getValoracion());
        //TODO: Para la carga de imagenes se usará una librería "glide".
        //Glide.with(this).load("http://goo.gl/gEgYUd").into(imageView);
        Glide.with(context).load(holder.mItem.getUrlPhoto()).centerCrop().into(holder.ivPhoto); //Todo: center crop para que se centre y no se distorsione
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvNombre;
        public final TextView tvDireccion;
        public final ImageView ivPhoto;
        public final RatingBar rbCalificacion;
        public Restaurante mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvNombre = view.findViewById(R.id.tvNombre);
            tvDireccion = view.findViewById(R.id.tvDireccion);
            ivPhoto = view.findViewById(R.id.ivPhoto);
            rbCalificacion = view.findViewById(R.id.rbCalificacion);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvNombre.getText() + "'";
        }
    }
}