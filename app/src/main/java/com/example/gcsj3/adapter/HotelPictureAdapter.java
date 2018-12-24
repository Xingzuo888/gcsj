package com.example.gcsj3.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gcsj3.R;
import com.example.gcsj3.gson.hoteldetails.PicturesList;

import java.util.List;

/**
 * Created by Administrator on 2018/12/23.
 */

public class HotelPictureAdapter extends RecyclerView.Adapter<HotelPictureAdapter.ViewHolder> {
    private Context mContext;
    private List<PicturesList> picturesLists;

    public HotelPictureAdapter(List<PicturesList> picturesLists) {
        this.picturesLists = picturesLists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.hotel_picture_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.picnameText.setText(picturesLists.get(position).name);
        Glide.with(mContext).load(picturesLists.get(position).path).into(holder.picImage);
    }

    @Override
    public int getItemCount() {
        return picturesLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView picImage;
        TextView picnameText;

        public ViewHolder(View view) {
            super(view);
            cardView= (CardView) view;
            picImage = (ImageView) view.findViewById(R.id.hotel_pic);
            picnameText = (TextView) view.findViewById(R.id.hotel_picname);
        }
    }

}
