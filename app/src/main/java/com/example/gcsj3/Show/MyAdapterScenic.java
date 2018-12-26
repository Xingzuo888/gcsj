package com.example.gcsj3.Show;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gcsj3.Bean.ResultBean;
import com.example.gcsj3.R;
import com.example.gcsj3.TangyActivity.DetailScenic;
import com.example.gcsj3.util.HttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyAdapterScenic extends BaseAdapter {
    private Context myContext;
    private List<ResultBean> datas;
    private Handler handler;
    private LayoutInflater myInflater;

    public MyAdapterScenic(Context context, List<ResultBean> datas, Handler handler){
        this.myContext = context;
        this.datas = datas;
        this.handler = handler;
        myInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        MyHolderView myHolderView = null;
        if (convertView == null){
            myHolderView = new MyHolderView();
            view = myInflater.inflate(R.layout.scenic_item, null);
            myHolderView.scenicId = (TextView) view.findViewById(R.id.scenicId);
            myHolderView.image = (ImageView) view.findViewById(R.id.scenic_item_image);
            myHolderView.scenicName = (TextView) view.findViewById(R.id.scenic_item_name);
            myHolderView.scenicPrice = (TextView) view.findViewById(R.id.scenic_item_price);
            myHolderView.openTime = (TextView) view.findViewById(R.id.scenic_item_time);
            myHolderView.addr = (TextView) view.findViewById(R.id.scenic_item_addre);
            myHolderView.layout = view.findViewById(R.id.scenic_layout);
            view.setTag(myHolderView);
        }else {
            view = convertView;
            myHolderView = (MyHolderView) view.getTag();
        }
        final  String scenicId = datas.get(position).getScenicId();
        myHolderView.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(myContext,DetailScenic.class);
                intent.putExtra("scenicId", scenicId);
                myContext.startActivity(intent);
            }
        });
        ResultBean bean = datas.get(position);
        myHolderView.scenicId.setText(bean.getScenicId());
        myHolderView.scenicName.setText(bean.getScenicName());
        myHolderView.scenicPrice.setText(String.valueOf(bean.getSalePrice()));
        myHolderView.openTime.setText(bean.getBizTime());
        myHolderView.addr.setText(bean.getAddress());
        final String picUrl = bean.getNewPicUrl();
        myHolderView.image.setTag(picUrl);
        final MyHolderView finalMyHolderView1 = myHolderView;
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtil.SendOkHttpRequest(picUrl, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Bitmap bit = BitmapFactory.decodeResource(myContext.getResources(), R.drawable.scenic);
                        finalMyHolderView1.image.setImageBitmap(bit);
                    }
                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        byte[] bytes = response.body().bytes();
                        final Bitmap bit = BitmapFactory.decodeByteArray(bytes,0, bytes.length);
                        finalMyHolderView1.image.post(new Runnable() {
                            @Override
                            public void run() {
                                finalMyHolderView1.image.setImageBitmap(bit);
                            }
                        });
                    }
                });
            }
        }).start();
        return view;
    }
    class MyHolderView{
        public View layout;
        public TextView scenicId;
        public ImageView image;
        public TextView scenicName;
        public TextView scenicPrice;
        public TextView openTime;
        public TextView addr;
    }
    public void addData(List<ResultBean> data){
        datas = data;
        notifyDataSetChanged();
    }
}
