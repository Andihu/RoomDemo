package com.hujian.roomdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Word> allWord = new ArrayList<>();


    public void setAllWord(List<Word> allWord) {
        this.allWord = allWord;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).bind(allWord.get(position));
    }

    @Override
    public int getItemCount() {
        return allWord.size();
    }

    class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView id,english , chinses;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id= itemView.findViewById(R.id.id);
            english= itemView.findViewById(R.id.english);
            chinses = itemView.findViewById(R.id.chinese);
        }
        public void bind(Word word){
            id.setText(String.valueOf(word.getId()));
            english.setText(word.getWord());
            chinses.setText(word.getChineseMeaning());
        }
    }

}
