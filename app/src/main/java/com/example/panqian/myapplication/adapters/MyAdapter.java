package com.example.panqian.myapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.panqian.myapplication.R;
import com.example.panqian.myapplication.model.Found;
import com.example.panqian.myapplication.model.Lost;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by panqian on 2016/6/8.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List< BmobObject> mData=new ArrayList<>();

    private final static int ITEM_LOST=0;
    private final static int ITEM_FOUND=1;
    private Context mContext;
    public MyAdapter(Context context){
        mContext=context;
    }
    public void setData(List<?extends BmobObject> data ,boolean needFresh){
        if (needFresh){
            mData.clear();
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder=null;
        switch (viewType){
            case ITEM_LOST:{
                View view= LayoutInflater.from(mContext).inflate(R.layout.item_lost,parent,false);
                viewHolder=new MyViewHolder(view);
                break;
            }
            case ITEM_FOUND:
            {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_found,parent,false);
                viewHolder=new MyViewHolder(view);
                break;
            }
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).update((BmobObject) mData.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position)instanceof Lost){
            return ITEM_LOST;
        }
        else if (mData.get(position) instanceof Found){
            return ITEM_FOUND;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView desc;
        private TextView time;
        private TextView phone;
        public MyViewHolder(View view){
            super(view);
            title=(TextView)view.findViewById(R.id.title);
            desc=(TextView)view.findViewById(R.id.desc);
            time=(TextView)view.findViewById(R.id.time);
            phone=(TextView)view.findViewById(R.id.phone);
        }
        public void update(BmobObject data){
            if (data instanceof Lost){
                Lost lost=(Lost)data;
                title.setText(lost.getTitle());
                desc.setText(lost.getDesc());
                phone.setText(lost.getPhone());
                time.setText(lost.getUpdatedAt());
            }else   if (data instanceof Found){
                Found found=(Found)data;
                title.setText(found.getTitle());
                desc.setText(found.getDesc());
                phone.setText(found.getPhone());
                time.setText(found.getUpdatedAt());
            }
        }
    }
}
