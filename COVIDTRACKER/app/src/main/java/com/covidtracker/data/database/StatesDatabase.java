package com.covidtracker.data.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.covidtracker.data.database.entity.States;

//@Database(entities = { YourEntity.class }, version = 1, exportSchema = false)
@Database(entities = {States.class}, version = 1, exportSchema = false)
public abstract class StatesDatabase extends RoomDatabase {

//public abstract class StatesDatabase  {

    private static final String LOG_TAG = StatesDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "states";


    private static final Object LOCK = new Object();
    private static StatesDatabase mInstance;

    public static StatesDatabase getInstance(Context context) {
        Log.d(LOG_TAG, "Getting " + DATABASE_NAME + " database");

        if (mInstance == null) {
            synchronized (LOCK) {
                mInstance = Room.databaseBuilder(context, StatesDatabase.class, DATABASE_NAME).build();
                Log.d(LOG_TAG, DATABASE_NAME + " database has been created.");
            }
        }
        return mInstance;
    }

    // associated DAOs for the database
    public abstract StateDao stateDao();

}
