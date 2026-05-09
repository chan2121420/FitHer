package com.fitherapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.fitherapp.models.User;

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(User user);
    @Update
    void update(User user);
    @Query("SELECT * FROM users LIMIT 1")
    LiveData<User> getCurrentUser();
    @Query("SELECT * FROM users LIMIT 1")
    User getCurrentUserSync();
    @Query("DELETE FROM users")
    void deleteAll();
}