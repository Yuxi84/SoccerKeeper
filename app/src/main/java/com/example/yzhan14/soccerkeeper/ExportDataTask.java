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
import java.security.spec.EncodedKeySpec;

/**
 * Created by yzhan14 on 12/6/2016.
 */

public class ExportDataTask extends AsyncTask<Void, Void, Boolean>{

    private Context ctx = null;
    private String DBName;
    public ExportDataTask(Context actx, String aDBName){
        ctx = actx;
        DBName = aDBName;
    }
    @Override
    protected Boolean doInBackground(Void... voids) {

        //checks the availability of external Storage
        if (!isExternalStorageWritable()){
            return false;
        }

        //from http://www.techrepublic.com/blog/software-engineer/export-sqlite-data-from-your-android-device/
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();

        FileChannel source = null;
        FileChannel destination = null;

        //TODO: check path
/*        String currentDBPath = "/data/" + "com.example.yzhan14.soccerkeeper" + "/databases/" + DBName;*/
        String backupDBPath = DBName;

        //TODO
/*        File currentDB = new File(data, currentDBPath);*/
        File currentDB = ctx.getDatabasePath(DBName);
        File backupDB = new File(sd, backupDBPath);

        try{
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean Exported) {
        if (Exported){
            Toast.makeText(ctx, "Data for this game is exported!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(ctx, "readable and writable external storage is unavailable", Toast.LENGTH_LONG).show();

        }
    }

    public boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state));

    }
}
