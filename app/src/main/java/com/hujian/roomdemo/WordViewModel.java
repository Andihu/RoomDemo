package com.hujian.roomdemo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class WordViewModel extends AndroidViewModel {

    WordRepository repository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        repository =new WordRepository(application);
    }
    public LiveData<List<Word>> getAllWordLive() {
        return repository.getAllWordLive();
    }


    public void insertWord(Word... words) {
        repository.insertWord(words);
    }

    public void upDateWord(Word... words) {
        repository.upDateWord();
    }

    public void DeleteAllWord() {
        repository.DeleteAllWord();
    }

    public void DeleteWord(Word... words) {
        repository.DeleteWord(words);
    }
}
