package com.jzhihui.recyclerviewcardview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 程 on 2016/3/7.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Test> data;

    public MyAdapter(List<Test> data) {
        super();
        this.data = data;
    }

    /**
     * 自定义点击事件的接口
     */
    private OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = View.inflate(parent.getContext(), R.layout.item, null);
        // 创建一个ViewHolder
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.iconIV.setImageResource(data.get(position).getResId());
        holder.nameTV.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTV;
        private ImageView iconIV;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTV = (TextView) itemView.findViewById(R.id.tv_name);
            iconIV = (ImageView) itemView.findViewById(R.id.iv_icon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClickListener!=null){
                        itemClickListener.onItemClick(v,getPosition());
                    }
                }
            });
        }
    }
}
