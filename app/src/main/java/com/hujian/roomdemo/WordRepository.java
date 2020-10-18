package com.hujian.roomdemo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private LiveData<List<Word>> allWordLive;

    WordDao wordDao;


    public WordRepository(Context context) {
        wordDao = WordDataBase.getDatabase(context).getWordDao();
        allWordLive = wordDao.getAllWorlds();
    }

    public LiveData<List<Word>> getAllWordLive() {
        return wordDao.getAllWorlds();
    }

    public void insertWord(Word... words) {
        new insertAsyncTask(wordDao).execute(words);
    }

    public void upDateWord(Word... words) {
        new updateAsyncTask(wordDao).execute(words);
    }

    public void DeleteAllWord() {
        new deleteAllAsyncTask(wordDao).execute();
    }

    public void DeleteWord(Word... words) {
        new deleteAsyncTask(wordDao).execute(words);
    }

    static class insertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        public insertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            return wordDao.insertWord(words);
        }
    }

    static class updateAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        public updateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updateWord(words);
            return null;
        }
    }

    static class deleteAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        public deleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.DeleteWord(words);
            return null;
        }
    }

    static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordDao wordDao;

        public deleteAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAllWorld();
            return null;
        }
    }
}
