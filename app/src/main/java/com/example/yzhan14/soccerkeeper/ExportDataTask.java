package com.example.yzhan14.soccerkeeper;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by yzhan14 on 12/6/2016.
 */

public class ExportDataTask extends AsyncTask<Void, Void, Void>{

    private Context ctx = null;
    private String DBName;
    public ExportDataTask(Context actx, String aDBName){
        ctx = actx;
        DBName = aDBName;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        //from http://www.techrepublic.com/blog/software-engineer/export-sqlite-data-from-your-android-device/
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();

        FileChannel source = null;
        FileChannel destination = null;

        //TODO: check path
        String currentDBPath = "/data/" + "com.example.yzhan14.soccerkeeper" + "/databases/" + DBName;
        String backupDBPath = DBName;

        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);

        try{
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            Toast.makeText(ctx, "Data for this game is exported!", Toast.LENGTH_LONG).show();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
