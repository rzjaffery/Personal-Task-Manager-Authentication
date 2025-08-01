// db/TaskDatabase.java
package com.rzjaffery.personaltaskmanagerapp.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.rzjaffery.personaltaskmanagerapp.model.Task;
@Database(entities = {Task.class}, version = 4) // increment version!
@TypeConverters({Converters.class})
public abstract class TaskDatabase extends RoomDatabase {
    private static TaskDatabase instance;
    public abstract TaskDao taskDao();
    public static synchronized TaskDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDatabase.class, "task_database")
                    .fallbackToDestructiveMigration() // 👈 Add this line
                    .build();
        }
        return instance;
    }

}
