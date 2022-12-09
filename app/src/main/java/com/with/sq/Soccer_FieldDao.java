package com.with.sq;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface Soccer_FieldDao {
    @Insert
    void insertSoccerField(Soccer_Field soccer_field);

    @Update
    void updateSoccerField(Soccer_Field soccer_field);

    @Delete
    void deleteSoccerField(Soccer_Field soccer_field);

}
