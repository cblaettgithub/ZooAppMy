package com.example.kevinschmidt.zooapp.database.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kevinschmidt.zooapp.classes.AnimalEntry;
import com.example.kevinschmidt.zooapp.classes.CageEntry;
import com.example.kevinschmidt.zooapp.classes.CategoryEntry;
import com.example.kevinschmidt.zooapp.classes.EmployeeEntry;
import com.example.kevinschmidt.zooapp.classes.FoodEntry;
import com.example.kevinschmidt.zooapp.database.queries.ZooInsert_Cage;
import com.example.kevinschmidt.zooapp.database.queries.ZooInsert_Category;
import com.example.kevinschmidt.zooapp.database.queries.ZooInsert_Food;
import com.example.kevinschmidt.zooapp.database.queries.ZooInsert_Employee;
import com.example.kevinschmidt.zooapp.database.queries.ZooInsert_Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 15.03.2016.
 */
public class ZooDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "ZooReader.db";
    String[] selectionArgs={"",""};
    String selection;
    private SQLiteDatabase database;
    private Cursor c;
    private EmployeeEntry employeeEntry;
    private AnimalEntry animalEntry;
    private FoodEntry foodEntry;
    private List<AnimalEntry> AnimalEntryList;
    private List<CageEntry> CagesEntryList;
    private List<CategoryEntry> categoryEntries;
    private List<FoodEntry> FoodEntryEntries;

    public List<FoodEntry> getFoodEntryEntries() {
        return FoodEntryEntries;
    }

    private boolean DBFull =true;
    public boolean isDBFull() {
        return DBFull;
    }
    public void setDBFull(boolean DBFull) {
        this.DBFull = DBFull;
    }
    public List<CategoryEntry> getCategoryEntries() {
        return categoryEntries;
    }
    public List<CageEntry> getCagesEntryList() {
        return CagesEntryList;
    }
    public List<AnimalEntry> getAnimalEntryList() {
        return AnimalEntryList;
    }
    private CageEntry cageEntry;
    private CategoryEntry categoryEntry;

    private Context context;
    public CageEntry getCageEntry() {return cageEntry;}
    public void setCageEntry(CageEntry cageEntry) {this.cageEntry = cageEntry;}
    public CategoryEntry getCategoryEntry() {return categoryEntry;}
    public void setCategoryEntry(CategoryEntry categoryEntry){this.categoryEntry = categoryEntry;}
    public AnimalEntry getAnimalEntry() {
        return animalEntry;
    }
    public EmployeeEntry getEmployeeEntry() {
        return employeeEntry;
    }
    public Context getContext() {
        return context;
    }

    public ZooDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context= context;  }

    //db creation
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ZooContract.EmployeeEntry.SQL_CREATE_ENTRIES_EMPLOYEES);
        db.execSQL( ZooContract.AnimalEntry.SQL_CREATE_ENTRIES_ANIMAL);
        db.execSQL(ZooContract.CagesEntry.SQL_CREATE_ENTRIES_CAGES);
        db.execSQL(ZooContract.CategorieEntry.SQL_CREATE_ENTRIES_CATEGORIES);
        db.execSQL(ZooContract.FoodEntry.SQL_CREATE_ENTRIES_CATEGORIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    //insert animals,cage,  food
    public void InsertAnimal(AnimalEntry animalEntry,SQLiteDatabase db){
        ZooInsert_Animal myAnimal = new ZooInsert_Animal();
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
    }
    public void InsertCage(CageEntry cageEntry,SQLiteDatabase db){
        ZooInsert_Cage myCage = new ZooInsert_Cage();
        db.insert(ZooContract.CagesEntry.TABLE_NAME, null,    myCage.Zoo_Insert_Cage(cageEntry));
    }
    public void InsertFood(FoodEntry foodEntry, SQLiteDatabase db){
        ZooInsert_Food zooInsert_food= new ZooInsert_Food();
        db.insert(ZooContract.FoodEntry.TABLE_NAME, null, zooInsert_food.Zoo_InsertFoodEntry(foodEntry));
    }
    //delete animals, Cage
    public void DeleteAnimal(String id, SQLiteDatabase db){
        String selection=ZooContract.AnimalEntry.COLUMN_NAME_ENTRY_ID + " Like  ?";
        String[] selectionArgs = {id};
        db.delete(ZooContract.AnimalEntry.TABLE_NAME, selection, selectionArgs);
    }
    public void DeleteCage(String id, SQLiteDatabase db){
        String selection=ZooContract.CagesEntry.COLUMN_NAME_ENTRY_ID + " Like  ?";
        String[] selectionArgs = {id};
        db.delete(ZooContract.CagesEntry.TABLE_NAME, selection, selectionArgs);
    }
    //update animals, cage
    public void UpdateAnimal(String id, SQLiteDatabase db, AnimalEntry animalEntry) {
        String selection = ZooContract.AnimalEntry.COLUMN_NAME_ENTRY_ID + " Like  ?";
        String[] selectionArgs = {id};
        ZooInsert_Animal myAnimal = new ZooInsert_Animal();
        db.update(ZooContract.AnimalEntry.TABLE_NAME, myAnimal.Zoo_Insert_AnimalEntry(animalEntry), selection, selectionArgs);
    }
    public void UpdateCage(String id, SQLiteDatabase db, CageEntry cagesEntry) {
        String selection = ZooContract.CagesEntry.COLUMN_NAME_ENTRY_ID + " Like  ?";
        String[] selectionArgs = {id};
        ZooInsert_Cage zooInsert_cage= new ZooInsert_Cage();
        db.update(ZooContract.CagesEntry.TABLE_NAME, zooInsert_cage.Zoo_Insert_Cage(cagesEntry), selection, selectionArgs);
    }

    //read all tables, employee, animal, cage, categorie, food
    public void Read_Table_Set(SQLiteDatabase db,String tablename, String[] projection, String selection, String[] selectionargs, String sortOrder)
    {
        database=db;
        c = database.query(tablename,projection,selection,selectionargs,null, null, null);
        if (!c.moveToFirst()){
            setDBFull(false);
            return;
        }
        switch (tablename)
        {
            case ZooContract.EmployeeEntry.TABLE_NAME:
                employeeEntry = new EmployeeEntry();
                do  {
                    employeeEntry.setLastname(c.getString(c.getColumnIndexOrThrow
                            (ZooContract.EmployeeEntry.COLUMN_NAME_LASTNAME)));
                    employeeEntry.setFirstname(c.getString((c.getColumnIndexOrThrow
                            (ZooContract.EmployeeEntry.COLUMN_NAME_FIRSTNAME))));
                    employeeEntry.setBirthdate(c.getString((c.getColumnIndexOrThrow
                            (ZooContract.EmployeeEntry.COLUMN_NAME_BIRTHDATE))));
                }while (c.moveToNext());
                break;
            case ZooContract.AnimalEntry.TABLE_NAME:
                AnimalEntryList= new ArrayList<AnimalEntry>();
              do {
                  animalEntry = new AnimalEntry();
                  animalEntry.setIdAnimal(c.getInt(c.getColumnIndexOrThrow
                          (ZooContract.AnimalEntry.COLUMN_NAME_ENTRY_ID)));
                    animalEntry.setName(c.getString(c.getColumnIndexOrThrow
                            (ZooContract.AnimalEntry.COLUMN_NAME_NAME)));
                    animalEntry.setCountry(c.getString(c.getColumnIndexOrThrow
                            (ZooContract.AnimalEntry.COLUMN_NAME_COUNTRY)));
                  animalEntry.setIdCategory(c.getInt(c.getColumnIndexOrThrow
                          (ZooContract.AnimalEntry.COLUMN_NAME_CATEGORY_ID)));
                  AnimalEntryList.add(animalEntry);
                }while (c.moveToNext());
                break;
            case ZooContract.CagesEntry.TABLE_NAME:
                CagesEntryList = new ArrayList<CageEntry>();
                do {
                    cageEntry = new CageEntry();
                    cageEntry.setIdCage(c.getInt(c.getColumnIndexOrThrow
                            (ZooContract.CagesEntry.COLUMN_NAME_ENTRY_ID)));
                    cageEntry.setCageName(c.getString(c.getColumnIndexOrThrow
                            (ZooContract.CagesEntry.COLUMN_NAME_Name)));
                    cageEntry.setCageSize(c.getInt(c.getColumnIndexOrThrow
                            (ZooContract.CagesEntry.COLUMN_NAME_CAGESIZE)));
                    cageEntry.setIdCategory(c.getInt(c.getColumnIndexOrThrow
                            (ZooContract.CagesEntry.COLUMN_NAME_CATEGORY_ID)));
                    CagesEntryList.add(cageEntry);
                }while (c.moveToNext());
                break;
            case ZooContract.CategorieEntry.TABLE_NAME:
                categoryEntries = new ArrayList<CategoryEntry>();
            do {
                categoryEntry = new CategoryEntry();
                categoryEntry.setIdCategorie(c.getColumnIndexOrThrow
                        (ZooContract.CategorieEntry.COLUMN_NAME_ENTRY_ID));
                categoryEntry.setNomCategorie(c.getString(c.getColumnIndexOrThrow
                        (ZooContract.CategorieEntry.COLUMN_NAME_NOMCATEGORIE)));
                categoryEntries.add(categoryEntry);
            }while (c.moveToNext());
            break;
            case ZooContract.FoodEntry.TABLE_NAME:
                FoodEntryEntries = new ArrayList<FoodEntry>();
                do {
                    foodEntry = new FoodEntry();

                    foodEntry.setIdFood(c.getInt(c.getColumnIndexOrThrow
                            (ZooContract.FoodEntry.COLUMN_NAME_ENTRY_ID)));
                    foodEntry.setNomFood(c.getString(c.getColumnIndexOrThrow
                            (ZooContract.FoodEntry.COLUMN_NAME_NOMFood)));
                    FoodEntryEntries.add(foodEntry);
                }while (c.moveToNext());
                break;
        }
    }
}
