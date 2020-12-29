package com.example.contraintlayout.Adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.contraintlayout.Interfaces.EntrenadorInteractionListener;
import com.example.contraintlayout.Models.Entrenador;
import com.example.contraintlayout.R;

import java.util.List;

public class EntrenadorViewAdapter extends RecyclerView.Adapter<EntrenadorViewAdapter.ViewHolder> {

    private final List<Entrenador> mValues;
    private Context context;
    private final EntrenadorInteractionListener mListener;

    public EntrenadorViewAdapter(Context context, List<Entrenador> items, EntrenadorInteractionListener listener) {
        mValues = items;
        this.context = context;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        /*holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);*/
        //holder.mItem = mValues.get(position);
        holder.mItem = mValues.get(position);

        Glide.with(context).load(holder.mItem.getFotoURL()).centerCrop().into(holder.ivFoto);

        holder.tvMatriculaEnt.setText(String.valueOf(holder.mItem.getMatricula()));
        holder.tvNombreEnt.setText(holder.mItem.getNombre());
        holder.tvAniosExpEnt.setText(holder.mItem.getExperiencia() + "Año(s)");
        holder.tvSucursalEnt.setText(holder.mItem.getSucursal());

        if(holder.mItem.getEstatus() == 1){
            holder.ivEstatusEnt.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
            holder.tvEstatusEnt.setText("AUTORIZADO");
        }
        else {
            holder.ivEstatusEnt.setImageResource(R.drawable.ic_baseline_error_outline_24);
            holder.tvEstatusEnt.setText("EXPULSADO");
        }

        //TODO: PODEMOS DARLE FUNCIONALIDAD ASÍ
        /*holder.ivEstatusEnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.mItem.getEstatus() == 1){
                    holder.mItem.setEstatus(0);
                    holder.tvEstatusEnt.setText("EXPULSADO");
                    holder.ivEstatusEnt.setImageResource(R.drawable.ic_baseline_error_outline_24);
                }else{
                    holder.mItem.setEstatus(1);
                    holder.tvEstatusEnt.setText("AUTORIZADO");
                    holder.ivEstatusEnt.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
                }
            }
        });*/

        //TODO: O PODEMOS DARLE FUNCIONALIDAD UTILIZANDO LA INTERFAZ:
        holder.ivEstatusEnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.bloquearEntrenador(holder.mItem);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        //public final TextView mIdView;
        //public final TextView mContentView;
        //
        public ImageView ivFoto;
        public final TextView tvMatriculaEnt;
        public final TextView tvNombreEnt;
        public final TextView tvAniosExpEnt;
        public final TextView tvSucursalEnt;
        public final ImageView ivEstatusEnt;
        public final TextView tvEstatusEnt;
        public Entrenador mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mIdView = (TextView) view.findViewById(R.id.item_number);
            //mContentView = (TextView) view.findViewById(R.id.content);
            ivFoto = view.findViewById(R.id.ivFoto);
            tvMatriculaEnt = view.findViewById(R.id.tvMatriculaEnt);
            tvNombreEnt = view.findViewById(R.id.tvNombreEnt);
            tvAniosExpEnt = view.findViewById(R.id.tvAniosExpEnt);
            tvSucursalEnt = view.findViewById(R.id.tvSucursalEnt);
            ivEstatusEnt = view.findViewById(R.id.ivEstatusEnt);
            tvEstatusEnt = view.findViewById(R.id.tvEstatusEnt);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvNombreEnt.getText() + "'";
        }
    }
}