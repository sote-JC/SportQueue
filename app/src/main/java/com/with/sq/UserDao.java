package com.with.sq;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void setInsertUser(User user);

    @Update
    void setUpdateUser(User user);

    @Delete
    void setDeleteUser(User user);

    @Query("SELECT * FROM User")
    List<User> getUserAll();

    @Query("UPDATE User SET introduce=:intro WHERE nickname=:nick")
    void updateIntroduce(String intro, String nick);

    @Query("SELECT introduce FROM User WHERE nickname=:nick")
    String getUserIntroduce(String nick);
}
