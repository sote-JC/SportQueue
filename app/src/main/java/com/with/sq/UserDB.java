package com.with.sq;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 2)
public abstract class UserDB extends RoomDatabase {
    public abstract UserDao userDao();
}
