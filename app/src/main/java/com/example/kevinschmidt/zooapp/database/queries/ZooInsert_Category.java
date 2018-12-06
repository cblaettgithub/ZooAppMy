package com.example.kevinschmidt.zooapp.database.queries;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.kevinschmidt.zooapp.classes.CategoryEntry;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooContract;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooDbHelper;

/**
 * Created by chris on 20.03.2016.
 */
public class ZooInsert_Category {
    Context context;
    private ContentValues values;
    long newRowID;
    private SQLiteDatabase database;
    private ZooDbHelper dbhelper;

    public ZooInsert_Category(){};
    public ZooInsert_Category(Context context){
        dbhelper = new ZooDbHelper(context);
    }
    public void open()    {
        database = dbhelper.getWritableDatabase();
    }
    public void close()    {
        dbhelper.close();
    }
    public ContentValues Zoo_Insert_Category(CategoryEntry categoryEntry)    {
        values = new ContentValues();
        values.put(ZooContract.CategorieEntry.COLUMN_NAME_NOMCATEGORIE, categoryEntry.getNomCategorie());
        return values;
    }
}
