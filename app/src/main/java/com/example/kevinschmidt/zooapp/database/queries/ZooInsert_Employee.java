package com.example.kevinschmidt.zooapp.database.queries;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.kevinschmidt.zooapp.classes.EmployeeEntry;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooContract;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooDbHelper;

/**
 * Created by chris on 15.03.2016.
 */
public class ZooInsert_Employee {
    Context context;
    private   ContentValues values;
    long newRowID;
    private SQLiteDatabase database;
    private ZooDbHelper dbhelper;
    String[] selectionArgs={};
    String selection;

    public ZooInsert_Employee(){};
    public ZooInsert_Employee(Context context){
        dbhelper = new ZooDbHelper(context);
    }
    public void open()    {
        database = dbhelper.getWritableDatabase();
    }
    public void close()    {
        dbhelper.close();
    }
    public ContentValues Zoo_Insert_Employee(EmployeeEntry employeeEntry) {
        values = new ContentValues();
        values.put(ZooContract.EmployeeEntry.COLUMN_NAME_FIRSTNAME, employeeEntry.getFirstname());
        values.put(ZooContract.EmployeeEntry.COLUMN_NAME_LASTNAME, employeeEntry.getLastname());
        values.put(ZooContract.EmployeeEntry.COLUMN_NAME_USERNAME, employeeEntry.getUsername());
        values.put(ZooContract.EmployeeEntry.COLUMN_NAME_PASSWORD, employeeEntry.getPassword());
        values.put(ZooContract.EmployeeEntry.COLUMN_NAME_BIRTHDATE, employeeEntry.getBirthdate());
        return values;
    }
}
