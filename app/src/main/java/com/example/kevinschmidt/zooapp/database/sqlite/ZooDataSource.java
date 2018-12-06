package com.example.kevinschmidt.zooapp.database.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.kevinschmidt.zooapp.classes.AnimalEntry;
import com.example.kevinschmidt.zooapp.classes.CageEntry;
import com.example.kevinschmidt.zooapp.classes.CategoryEntry;
import com.example.kevinschmidt.zooapp.classes.EmployeeEntry;
import com.example.kevinschmidt.zooapp.classes.FoodEntry;
import com.example.kevinschmidt.zooapp.fragment.AnimalInsertFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 15.03.2016.
 */
public class ZooDataSource {
    Context context;
    private   ContentValues values;
    long newRowID;
    private SQLiteDatabase database;
    private ZooDbHelper dbhelper;
    private ZooDataSource zooDataSource;
    String[] projection;
    String selection;
    String[] selectionargs;
    String sortOrder;
    private boolean DBFull =true;

    public ZooDataSource(Context context){
        dbhelper = new ZooDbHelper(context);
        database = dbhelper.getWritableDatabase();
    }
    public ZooDataSource(){
        dbhelper = new ZooDbHelper(context);
        database = dbhelper.getWritableDatabase();
    }

    public boolean isDBFull() {
        return DBFull;
    }
    public void setDBFull(boolean DBFull) {
        this.DBFull = DBFull;
    }

    public void close()    {
        dbhelper.close();
    }
    //db creation
    public void createDB() {
        dbhelper.onCreate(database);
    }

    //insert items
    public void InsertDB()
    {
       Fill_Default_DB fill_default_db = new Fill_Default_DB();
       fill_default_db.Fill_Default_Values_DB(database);
    }
    public void InsertCage(AnimalEntry animalEntry)    {
        dbhelper.InsertAnimal(animalEntry, database);
    }
    public void InsertCage(CageEntry cageEntry)    {
        dbhelper.InsertCage(cageEntry, database);
    }
    public void InsertFood(FoodEntry foodEntry){
        dbhelper.InsertFood(foodEntry, database);
    }
    //delete items, animal
    public void DeleteAnimal(String id){
        dbhelper.DeleteAnimal(id, database);
    }

    public void DeleteCage(String id){
        dbhelper.DeleteCage(id, database);
    }

    //update items, animals
    public void UpdateAnimal(String id, AnimalEntry animalEntry){
        dbhelper.UpdateAnimal(id, database, animalEntry);
    }
    public void UpdateCage(String id, CageEntry cagesEntry){
        dbhelper.UpdateCage(id, database, cagesEntry);
    }

    ///Read items Employee, animals, Cage,
    public List<EmployeeEntry> ReadDB_Employee_Source() {
        String[] projection = {
                ZooContract.EmployeeEntry.COLUMN_NAME_FIRSTNAME,
                ZooContract.EmployeeEntry.COLUMN_NAME_LASTNAME,
                ZooContract.EmployeeEntry.COLUMN_NAME_BIRTHDATE};
        selection = ZooContract.EmployeeEntry.COLUMN_NAME_FIRSTNAME;
        sortOrder = ZooContract.EmployeeEntry.COLUMN_NAME_LASTNAME + " DESC";
        dbhelper.Read_Table_Set(database, ZooContract.EmployeeEntry.TABLE_NAME, projection, null, null, null);
        List<EmployeeEntry> temp = new ArrayList<EmployeeEntry>();
        temp.add(dbhelper.getEmployeeEntry());
        return  temp;
    }
    public List<EmployeeEntry> ReadDB_Employee_User(String username, String password) {
        String[] projection = {
                ZooContract.EmployeeEntry.COLUMN_NAME_FIRSTNAME,
                ZooContract.EmployeeEntry.COLUMN_NAME_LASTNAME,
                ZooContract.EmployeeEntry.COLUMN_NAME_BIRTHDATE};
        selection = ZooContract.EmployeeEntry.COLUMN_NAME_USERNAME + "= "+ username +" AND "+
                ZooContract.EmployeeEntry.COLUMN_NAME_PASSWORD+ "= "+password  ;
        String[] selectionargs={username, password};
        sortOrder = ZooContract.EmployeeEntry.COLUMN_NAME_LASTNAME + " DESC";
        dbhelper.Read_Table_Set(database, ZooContract.EmployeeEntry.TABLE_NAME, projection, selection, null, null);
        if (!dbhelper.isDBFull()){
            setDBFull(false);
        }
        List<EmployeeEntry> temp = new ArrayList<EmployeeEntry>();
        temp.add(dbhelper.getEmployeeEntry());
        return  temp;
    }
    public List<AnimalEntry> ReadDB_Animal_Souce(int CategoryID) {
        String[] projection= {
                ZooContract.AnimalEntry.COLUMN_NAME_ENTRY_ID,
                ZooContract.AnimalEntry.COLUMN_NAME_NAME,
                ZooContract.AnimalEntry.COLUMN_NAME_COUNTRY,
                ZooContract.AnimalEntry.COLUMN_NAME_CATEGORY_ID,
               };
        selection=  ZooContract.AnimalEntry.COLUMN_NAME_CATEGORY_ID + " = "+ String.valueOf(CategoryID);
        sortOrder = ZooContract.AnimalEntry.COLUMN_NAME_COUNTRY + " DESC";
        dbhelper.Read_Table_Set(database, ZooContract.AnimalEntry.TABLE_NAME, projection, selection, null, null);
        List<AnimalEntry> temp = new ArrayList<AnimalEntry>();
        temp.add(dbhelper.getAnimalEntry());
        return dbhelper.getAnimalEntryList();
        //return temp;
    }
    public List<AnimalEntry> ReadDB_Animal_Souce_id(int AnimalID) {
        String[] projection= {
                ZooContract.AnimalEntry.COLUMN_NAME_ENTRY_ID,
                ZooContract.AnimalEntry.COLUMN_NAME_NAME,
                ZooContract.AnimalEntry.COLUMN_NAME_COUNTRY,
                ZooContract.AnimalEntry.COLUMN_NAME_CATEGORY_ID,
        };
        selection=  ZooContract.AnimalEntry.COLUMN_NAME_ENTRY_ID + " = "+ String.valueOf(AnimalID);
        sortOrder = ZooContract.AnimalEntry.COLUMN_NAME_COUNTRY + " DESC";
        dbhelper.Read_Table_Set(database, ZooContract.AnimalEntry.TABLE_NAME, projection, selection, null, null);
        List<AnimalEntry> temp = new ArrayList<AnimalEntry>();
        temp.add(dbhelper.getAnimalEntry());
        return dbhelper.getAnimalEntryList();
        //return temp;
    }
    public List<CageEntry> ReadDB_Cage_Souce(int CategoryID) {
        String[] projection= {
                ZooContract.CagesEntry.COLUMN_NAME_ENTRY_ID,
                ZooContract.CagesEntry.COLUMN_NAME_Name,
                ZooContract.CagesEntry.COLUMN_NAME_CAGESIZE,
                ZooContract.CagesEntry.COLUMN_NAME_CATEGORY_ID};
        if (CategoryID==0) //get all Cages
            selection=null;
        else
            selection=  ZooContract.CagesEntry.COLUMN_NAME_CATEGORY_ID+ " = "+ String.valueOf(CategoryID);;
        //sortOrder = ZooContract.CagesEntry.COLUMN_NAME_LASTNAME + " DESC";
        dbhelper.Read_Table_Set(database,  ZooContract.CagesEntry.TABLE_NAME,projection, selection,  null, null);
        List<CageEntry> temp = new ArrayList<CageEntry>();
        temp.add(dbhelper.getCageEntry());
        return dbhelper.getCagesEntryList();
        //return temp;
    }
    public List<CageEntry> ReadDB_Cage_Souce_ID(int CategoryID) {
        String[] projection= {
                ZooContract.CagesEntry.COLUMN_NAME_ENTRY_ID,
                ZooContract.CagesEntry.COLUMN_NAME_Name,
                ZooContract.CagesEntry.COLUMN_NAME_CAGESIZE,
                ZooContract.CagesEntry.COLUMN_NAME_CATEGORY_ID};
        selection=  ZooContract.CagesEntry.COLUMN_NAME_ENTRY_ID + " = "+ String.valueOf(CategoryID);
              //sortOrder = ZooContract.CagesEntry.COLUMN_NAME_LASTNAME + " DESC";
        dbhelper.Read_Table_Set(database,  ZooContract.CagesEntry.TABLE_NAME,projection, selection,  null, null);
        List<CageEntry> temp = new ArrayList<CageEntry>();
        temp.add(dbhelper.getCageEntry());
        return dbhelper.getCagesEntryList();
        //return temp;
    }
    public List<CategoryEntry> ReadDB_Category_Souce() {
        String[] projection= {
                ZooContract.CategorieEntry.COLUMN_NAME_ENTRY_ID,
                ZooContract.CategorieEntry.COLUMN_NAME_NOMCATEGORIE};
        //selection=  ZooContract.CategorieEntry.COLUMN_NAME_FIRSTNAME;
        //sortOrder = ZooContract.CategorieEntry.COLUMN_NAME_LASTNAME + " DESC";
        dbhelper.Read_Table_Set(database, ZooContract.CategorieEntry.TABLE_NAME, projection, null, null, null);
        List<CategoryEntry> temp = new ArrayList<CategoryEntry>();
        temp.add(dbhelper.getCategoryEntry());
        return dbhelper.getCategoryEntries();
        //return temp;
    }


}
