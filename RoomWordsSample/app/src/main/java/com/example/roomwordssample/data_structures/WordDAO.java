package com.example.roomwordssample.data_structures;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> showAllWords();

    @Query("SELECT * from word_table LIMIT 1")
    Word[] getAnyWord();

    @Delete
    void deleteWord(Word word);

    @Update
    void update(Word... word);
}
