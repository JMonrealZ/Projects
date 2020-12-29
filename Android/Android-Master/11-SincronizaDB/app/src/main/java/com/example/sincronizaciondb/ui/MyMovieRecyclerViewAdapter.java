package com.example.sincronizaciondb.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sincronizaciondb.R;
import com.example.sincronizaciondb.data.local.entity.MovieEntity;
import com.example.sincronizaciondb.data.remote.ApiConstants;

import java.util.List;

public class MyMovieRecyclerViewAdapter extends RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder> {
    private List<MovieEntity> mValues;
    private Context ctx;

    public MyMovieRecyclerViewAdapter(Context context, List<MovieEntity> items) {
        mValues = items;
        ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Glide.with(ctx)
                .load(ApiConstants.IMG_API_PREFIX + holder.mItem.getBackdropPath())
                .into(holder.ivCover);
//        holder.mIdView.setText(mValues.get(position).id);
//        holder.mContentView.setText(mValues.get(position).content);
    }

    public void setData(List<MovieEntity> movies){
        this.mValues = movies;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mValues != null) return mValues.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
//        public final TextView mIdView;
//        public final TextView mContentView;
        public final ImageView ivCover;
        public MovieEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivCover = (ImageView) view.findViewById(R.id.ivCover);
//            mIdView = (TextView) view.findViewById(R.id.item_number);
//            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() /*+ " '" + mContentView.getText() + "'"*/;
        }
    }
}