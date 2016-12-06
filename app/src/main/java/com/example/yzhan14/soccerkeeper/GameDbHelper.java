package com.example.yzhan14.soccerkeeper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yzhan14 on 12/1/2016.
 */

public class GameDbHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Game.db";
    public static final int DATABASE_VERSION = 1;//change the version when changing rhe database schema


    private static final String SQL_CREATE_EVENTS_TABLE =
            "CREATE TABLE " +
                    EventsContract.EventEntry.TABLE_NAME + " (" +
                    EventsContract.EventEntry._ID + " int not null, " +
                    EventsContract.EventEntry.COLUMN_NAME_TIME + " varchar not null, " +
                    EventsContract.EventEntry.COLUMN_NAME_POSITION + " varchar not null, " +
                    EventsContract.EventEntry.COLUMN_NAME_TEAM + " varchar not null, " +
                    EventsContract.EventEntry.COLUMN_NAME_PLAYER + " varchar not null, " +
                    EventsContract.EventEntry.COLUMN_NAME_ACTION + " varchar not null, " +
                    "PRIMARY KEY (" + EventsContract.EventEntry._ID + "))";

    private static final String SQL_DROP_EVENTS_TABLE =
            "DROP TABLE IF EXISTS " + EventsContract.EventEntry.TABLE_NAME;




    public GameDbHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DROP_EVENTS_TABLE);
        onCreate(db);
    }
}
