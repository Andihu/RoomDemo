package com.hujian.roomdemo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button insert , update, getAll,delete;
    RecyclerView date;
    WordViewModel wordViewModel;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        insert = findViewById(R.id.insert);
        update = findViewById(R.id.uodate);
        getAll = findViewById(R.id.getall);
        delete = findViewById(R.id.delete);
        date = findViewById(R.id.recyclerview);
        adapter =new MyAdapter();
        date.setLayoutManager(new LinearLayoutManager(this));
        date.setAdapter(adapter);

        wordViewModel.getAllWordLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
//                StringBuilder stringBuilder =new StringBuilder();
//                for (Word word : words) {
//                    stringBuilder.append(word);
//                }
//                date.setText(stringBuilder);
                adapter.setAllWord(words);
            }
        });


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("hollow","hi好");
                Word word1 = new Word("hollow","hi好");
                Word word2 = new Word("hollow","hi好");
                Word word3 = new Word("hollow","hi好");
                wordViewModel.insertWord(word,word1,word2,word3);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordViewModel.DeleteAllWord();
            }
        });

    }



}