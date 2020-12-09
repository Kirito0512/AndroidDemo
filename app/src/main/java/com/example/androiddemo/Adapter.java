package com.example.androiddemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> mList;
    private ItemClickListener itemClickListener;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TextViewHolder.getInstance(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TextViewHolder) holder).onBindViewHolder(mList.get(position), position, getItemClickListener());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setmList(List<String> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    private static class TextViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private TextViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }

        public static TextViewHolder getInstance(ViewGroup parent) {
            return new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.text_vh_layout, parent, false));
        }

        public void onBindViewHolder(String text, int position, final ItemClickListener itemClickListener) {
            tv.setTag(position);
            tv.setText(text);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClickItem(v);
                }
            });
        }
    }

    public interface ItemClickListener {
        void onClickItem(View view);
    }

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
