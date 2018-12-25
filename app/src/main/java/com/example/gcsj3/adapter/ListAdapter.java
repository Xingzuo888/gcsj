package com.example.gcsj3.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gcsj3.R;
import com.example.gcsj3.gson.hotel.HotelList;

import java.util.List;

/**
 * Created by Administrator on 2018/12/25.
 */

public class ListAdapter extends BaseAdapter {
    private Context mContext;
    private int resourceId;
    private List<HotelList> hotelLists;

    public ListAdapter(Context mContext, int resourceId, List<HotelList> hotelLists) {
        this.hotelLists = hotelLists;
        this.mContext = mContext;
        this.resourceId = resourceId;
    }

    @Override
    public int getCount() {
        return hotelLists.size();
    }

    @Override
    public Object getItem(int position) {
        return hotelLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
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
        viewHolder.hotelName.setText(hotelLists.get(position).chineseName);
        viewHolder.hotelAddress.setText(hotelLists.get(position).address);
        viewHolder.hotelStar.setText(hotelLists.get(position).starName);
        viewHolder.hotelPrice.setText("￥ "+hotelLists.get(position).price);
        Glide.with(mContext).load(hotelLists.get(position).picture).into(viewHolder.hotelImage);
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
