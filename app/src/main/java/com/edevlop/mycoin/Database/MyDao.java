package com.edevlop.mycoin.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {

    @Query("SELECT * FROM DataModel")
    List<DataModel> getAllUsers();

    @Insert
    void insertUser(DataModel... dataModels);

    @Delete
    void delete(DataModel dataModel);
}
