package com.example.yzhan14.soccerkeeper;

import android.provider.BaseColumns;

/**
 * Created by yzhan14 on 12/1/2016.
 */

public class EventsContract {

    private EventsContract(){}

    public static class EventEntry implements BaseColumns{
        public static final String TABLE_NAME = "events";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_POSITION = "position";
        public static final String COLUMN_NAME_TEAM = "team";
        public static final String COLUMN_NAME_PLAYER = "player";
        public static final String COLUMN_NAME_ACTION = "action";
    }
}
