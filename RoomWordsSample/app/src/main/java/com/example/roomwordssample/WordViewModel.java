package com.example.roomwordssample;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomwordssample.data_structures.Word;
import com.example.roomwordssample.data_structures.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

    public WordViewModel (Application application){
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public void insert(Word word) {mRepository.insert(word);}

    public LiveData<List<Word>> getAllWords() { return mAllWords; }

    public void deleteAll() {mRepository.deleteAll();}

    public void deleteWord(Word word) {mRepository.deleteWord(word);}
}
