package com.with.sq;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Soccer_Field.class}, version=1)
public abstract class Soccer_FieldDB extends RoomDatabase {
    public abstract Soccer_FieldDao soccer_fieldDao();
}
