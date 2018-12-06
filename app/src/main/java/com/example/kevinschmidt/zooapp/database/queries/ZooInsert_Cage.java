package com.example.kevinschmidt.zooapp.database.queries;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.kevinschmidt.zooapp.classes.CageEntry;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooContract;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooDbHelper;

/**
 * Created by chris on 20.03.2016.
 */
public class ZooInsert_Cage {
    Context context;
    private ContentValues values;
    long newRowID;
    private SQLiteDatabase database;
    private ZooDbHelper dbhelper;

    public ZooInsert_Cage(){};
    public ZooInsert_Cage(Context context){
        dbhelper = new ZooDbHelper(context);
    }
    public void open()    {
        database = dbhelper.getWritableDatabase();
    }
    public void close()    {
        dbhelper.close();
    }
    public ContentValues Zoo_Insert_Cage(CageEntry cagesEntry)    {
            values = new ContentValues();
            values.put(ZooContract.CagesEntry.COLUMN_NAME_Name, cagesEntry.getCageName());
            values.put(ZooContract.CagesEntry.COLUMN_NAME_CAGESIZE, cagesEntry.getCageSize());
            values.put(ZooContract.CagesEntry.COLUMN_NAME_CATEGORY_ID, cagesEntry.getIdCategory());
       return values;
    }
}
