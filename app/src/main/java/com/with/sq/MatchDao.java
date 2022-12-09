package com.with.sq;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MatchDao {
    @Insert
    void insertMatch(Match match);

    @Update
    void updateMatch(Match match);

    @Delete
    void deleteMatch(Match match);

    @Query("SELECT * FROM `match`")
    List<Match> getMatchAll();

}
