package com.example.kevinschmidt.zooapp.database.queries;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.kevinschmidt.zooapp.database.sqlite.ZooContract;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooDbHelper;

/**
 * Created by chris on 15.03.2016.
 */
public class ZooDelete {
    Context context;
    private   ContentValues values;
    long newRowID;
    private SQLiteDatabase database;
    private ZooDbHelper dbhelper;
    String[] selectionArgs={};
    String selection;

    public ZooDelete(Context context){
        dbhelper = new ZooDbHelper(context);
    }
    public void open()    {
        database = dbhelper.getWritableDatabase();
    }
    public void close()    {
        dbhelper.close();
    }

    public void Zoo_Delete_Employee()    {
        int rowID=1;
        selection = ZooContract.EmployeeEntry.COLUMN_NAME_ENTRY_ID +
                " LIKE ?";
        String[] selectionArgs={String.valueOf(newRowID)};
        database.delete(ZooContract.EmployeeEntry.TABLE_NAME, selection, selectionArgs);
    }
    public void Zoo_Delete_Animal(String IdAnimal){
        selection = ZooContract.EmployeeEntry.COLUMN_NAME_ENTRY_ID +
                " = " + IdAnimal;
        database.delete(ZooContract.AnimalEntry.TABLE_NAME, selection, null);
    }
}
