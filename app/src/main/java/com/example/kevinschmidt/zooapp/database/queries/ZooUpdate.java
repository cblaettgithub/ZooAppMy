package com.example.kevinschmidt.zooapp.database.queries;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.kevinschmidt.zooapp.database.sqlite.ZooContract;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooDbHelper;

/**
 * Created by chris on 15.03.2016.
 */
public class ZooUpdate {
    Context context;
    private   ContentValues values;
    long newRowID;
    private SQLiteDatabase database;
    private ZooDbHelper dbhelper;
    String[] selectionArgs={};
    String selection;

    public ZooUpdate(Context context){
        dbhelper = new ZooDbHelper(context);
    }
    public void open()    {
        database = dbhelper.getWritableDatabase();
    }
    public void close()    {
        dbhelper.close();
    }
    public void Zoo_Update_Employee()   {
        String name="";
        values.put(ZooContract.EmployeeEntry.COLUMN_NAME_FIRSTNAME, name);
        selection = ZooContract.EmployeeEntry.COLUMN_NAME_ENTRY_ID +
                " Like ?";
        String[] selectionArgs ={String.valueOf(newRowID)};

        int count = database.update(
                ZooContract.EmployeeEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }
}
