package com.hujian.roomdemo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    Void insertWord(Word... word);

    @Update
    int updateWord(Word... words);

    @Delete
    int DeleteWord(Word... words);

    @Query("DELETE FROM WORD")
    void deleteAllWorld();

    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    //List<Word> getAllWorlds();
    LiveData<List<Word>> getAllWorlds();
}
