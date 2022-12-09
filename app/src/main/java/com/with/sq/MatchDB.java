package com.with.sq;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Match.class}, version = 2)
public abstract class MatchDB extends RoomDatabase {
    public abstract MatchDao matchDao();
}
