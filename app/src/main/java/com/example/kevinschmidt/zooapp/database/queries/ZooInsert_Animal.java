package com.example.kevinschmidt.zooapp.database.queries;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.kevinschmidt.zooapp.classes.AnimalEntry;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooContract;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooDbHelper;

/**
 * Created by chris on 17.03.2016.
 */
public class ZooInsert_Animal {

        Context context;
        private ContentValues values;
        long newRowID;
        private SQLiteDatabase database;
        private ZooDbHelper dbhelper;
        String[] selectionArgs={};
        String selection;

        public ZooInsert_Animal(){};
        public ZooInsert_Animal(Context context){
            dbhelper = new ZooDbHelper(context);
        }
        public void open()    {
            database = dbhelper.getWritableDatabase();
        }
        public void close()    {
            dbhelper.close();
        }
        public ContentValues Zoo_Insert_AnimalEntry(AnimalEntry animalEntry)    {
            values = new ContentValues();
            values.put(ZooContract.AnimalEntry.COLUMN_NAME_COUNTRY, animalEntry.getCountry());
            values.put(ZooContract.AnimalEntry.COLUMN_NAME_NAME, animalEntry.getName());
            values.put(ZooContract.AnimalEntry.COLUMN_NAME_CATEGORY_ID, animalEntry.getIdCategory());
            values.put(ZooContract.AnimalEntry.COLUMN_NAME_CAGE_ID, animalEntry.getIdCage());
            return values;
        }
}
