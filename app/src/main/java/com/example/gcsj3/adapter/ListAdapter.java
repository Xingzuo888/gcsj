package com.example.gcsj3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gcsj3.R;
import com.example.gcsj3.gson.hotel.HotelList;

import java.util.List;

/**
 * Created by Administrator on 2018/12/23.
 */

public class ListAdapter extends ArrayAdapter<HotelList> {
    private int resourceId;

    public ListAdapter(Context context, int resource, List<HotelList> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HotelList hotelList = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.cardView = (CardView) view;
            viewHolder.hotelImage = (ImageView) view.findViewById(R.id.hotel_image);
            viewHolder.hotelName = (TextView) view.findViewById(R.id.hotel_name);
            viewHolder.hotelAddress = (TextView) view.findViewById(R.id.hotel_address);
            viewHolder.hotelStar = (TextView) view.findViewById(R.id.hotel_star);
            viewHolder.hotelPrice = (TextView) view.findViewById(R.id.hotel_price);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.hotelName.setText(hotelList.chineseName);
        viewHolder.hotelAddress.setText(hotelList.address);
        viewHolder.hotelStar.setText(hotelList.starName);
        viewHolder.hotelPrice.setText("￥ "+hotelList.price);
        Glide.with(getContext()).load(hotelList.picture).into(viewHolder.hotelImage);
        return view;
    }

    class ViewHolder{
        CardView cardView;
        ImageView hotelImage;
        TextView hotelName;
        TextView hotelAddress;
        TextView hotelStar;
        TextView hotelPrice;
    }

}
