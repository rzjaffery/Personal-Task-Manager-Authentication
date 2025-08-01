// db/TaskDao.java
package com.rzjaffery.personaltaskmanagerapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import com.rzjaffery.personaltaskmanagerapp.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM task_table ORDER BY date ASC")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM task_table WHERE is_completed = 0 ORDER BY date ASC")
    LiveData<List<Task>> getPendingTasks();

    @Query("SELECT * FROM task_table WHERE is_completed = 1 ORDER BY date ASC")
    LiveData<List<Task>> getCompletedTasks();

}
