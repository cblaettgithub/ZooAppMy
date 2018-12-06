package com.example.kevinschmidt.zooapp.database.queries;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.kevinschmidt.zooapp.classes.FoodEntry;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooContract;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooDbHelper;

/**
 * Created by chris on 17.03.2016.
 */
public class ZooInsert_Food {

        Context context;
        private ContentValues values;
        long newRowID;
        private SQLiteDatabase database;
        private ZooDbHelper dbhelper;
        String[] selectionArgs={};
        String selection;

        public ZooInsert_Food(){};
        public ZooInsert_Food(Context context){
            dbhelper = new ZooDbHelper(context);
        }
        public void open()    {
            database = dbhelper.getWritableDatabase();
        }
        public void close()    {
            dbhelper.close();
        }
        public ContentValues Zoo_InsertFoodEntry(FoodEntry foodEntry)    {
            values = new ContentValues();
            values.put(ZooContract.FoodEntry.COLUMN_NAME_NOMFood, foodEntry.getNomFood());
            values.put(ZooContract.FoodEntry.COLUMN_NAME_CATEGORY_ID, foodEntry.getIdCategory());
            values.put(ZooContract.FoodEntry.COLUMN_NAME_CAGE_ID, foodEntry.getIdCage());
            return values;
        }
}
