package com.example.roomwordssample.data_structures;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {

    private WordDAO mwordDAO;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase database = WordRoomDatabase.getDatabase(application);
        mwordDAO = database.wordDAO();
        mAllWords = mwordDAO.showAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word){
        new insertAsyncTask(mwordDAO).execute(word);
    }

    public void deleteAll() {new deleteAsyncTask(mwordDAO).execute();}

    public void deleteWord(Word word){new deleteWordAsyncTask(mwordDAO).execute(word);}

    private static class insertAsyncTask extends AsyncTask<Word,Void,Void>{

        private WordDAO mAsyncDAO;

        insertAsyncTask(WordDAO DAO){
            mAsyncDAO = DAO;
        }
        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncDAO.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Void,Void,Void> {

        private WordDAO mAsyncDAO;

        deleteAsyncTask(WordDAO DAO) { mAsyncDAO = DAO;}

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncDAO.deleteAll();
            return null;
        }
    }

    private class deleteWordAsyncTask extends AsyncTask<Word,Void,Void>{

        private WordDAO mAsyncDAO;

        deleteWordAsyncTask(WordDAO DAO) {
            mAsyncDAO = DAO;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncDAO.deleteWord(words[0]);
            return null;
        }
    }
}

