package com.example.kevinschmidt.zooapp.database.sqlite;

import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by kevinschmidt, cblaettler on 15.03.16.
 */
public class ZooContract {

    public ZooContract(){}

    public static abstract class EmployeeEntry implements BaseColumns {
        public static final String TABLE_NAME = "Employee";
        public static final String COLUMN_NAME_ENTRY_ID= "IdEmployee";
        public static final String COLUMN_NAME_USERNAME = "Username" ;
        public static final String COLUMN_NAME_PASSWORD = "Password" ;
        public static final String COLUMN_NAME_FIRSTNAME = "Firstname" ;
        public static final String COLUMN_NAME_LASTNAME = "Lastname" ;
        public static final String COLUMN_NAME_BIRTHDATE = "Birthdate" ;

        public static final String SQL_CREATE_ENTRIES_EMPLOYEES =

                "CREATE TABLE IF NOT EXISTS " + EmployeeEntry.TABLE_NAME +
                        " ("
                        + EmployeeEntry.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + EmployeeEntry.COLUMN_NAME_USERNAME + " TEXT NOT NULL,"
                        + EmployeeEntry.COLUMN_NAME_PASSWORD + " TEXT NOT NULL,"
                        + EmployeeEntry.COLUMN_NAME_FIRSTNAME + " TEXT NOT NULL,"
                        + EmployeeEntry.COLUMN_NAME_LASTNAME + " TEXT NOT NULL,"
                        + EmployeeEntry.COLUMN_NAME_BIRTHDATE + " TEXT NOT NULL"
                        + " )";
    }
    public static abstract class AnimalEntry implements BaseColumns {

        public static final String TABLE_NAME = "Animal";
        public static final String COLUMN_NAME_ENTRY_ID= "IdAnimal";
        public static final String COLUMN_NAME_NAME = "Name" ;
        public static final String COLUMN_NAME_COUNTRY = "Country" ;
        public static final String COLUMN_NAME_CATEGORY_ID= "IdCategory";
        public static final String COLUMN_NAME_CAGE_ID= "IdCage";

        public static final String SQL_CREATE_ENTRIES_ANIMAL =
                "CREATE TABLE IF NOT EXISTS " + AnimalEntry.TABLE_NAME +
                        " ("
                        + AnimalEntry.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + AnimalEntry.COLUMN_NAME_NAME + " TEXT NOT NULL,"
                        + AnimalEntry.COLUMN_NAME_COUNTRY + " TEXT NOT NULL, "
                        + AnimalEntry.COLUMN_NAME_CATEGORY_ID + " INTEGER NOT NULL, "
                        + AnimalEntry.COLUMN_NAME_CAGE_ID + " INTEGER NOT NULL "
                        + " )";
    }
    public static abstract class CagesEntry implements BaseColumns {
        public static final String TABLE_NAME = "Cages";
        public static final String COLUMN_NAME_ENTRY_ID = "idCage";
        public static final String COLUMN_NAME_Name = "CageName";
        public static final String COLUMN_NAME_CATEGORY_ID= "IdCategory";
        public static final String COLUMN_NAME_CAGESIZE = "CageSize";

        public static final String SQL_CREATE_ENTRIES_CAGES =
                "CREATE TABLE IF NOT EXISTS " + CagesEntry.TABLE_NAME +
                        " ("
                        + CagesEntry.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + CagesEntry.COLUMN_NAME_Name + " Text NOT NULL , "
                        + CagesEntry.COLUMN_NAME_CATEGORY_ID + " INTEGER NOT NULL, "
                        + CagesEntry.COLUMN_NAME_CAGESIZE + " INTEGER NOT NULL"
                        + " )";
    }
    public static abstract class CategorieEntry implements BaseColumns {
        public static final String TABLE_NAME = "Categorie";
        public static final String COLUMN_NAME_ENTRY_ID = "idCategorie";
        public static final String COLUMN_NAME_NOMCATEGORIE = "NomCategorie";

        public static final String SQL_CREATE_ENTRIES_CATEGORIES =
                "CREATE TABLE IF NOT EXISTS " + CategorieEntry.TABLE_NAME +
                        " ("
                        + CategorieEntry.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + CategorieEntry.COLUMN_NAME_NOMCATEGORIE + " TEXT NOT NULL"
                        + " )";
    }
    public static abstract class FoodEntry implements BaseColumns {
        public static final String TABLE_NAME = "Food";
        public static final String COLUMN_NAME_ENTRY_ID = "idFood";
        public static final String COLUMN_NAME_NOMFood = "NomFood";
        public static final String COLUMN_NAME_CATEGORY_ID= "IdCategory";
        public static final String COLUMN_NAME_CAGE_ID= "IdCage";

        public static final String SQL_CREATE_ENTRIES_CATEGORIES =
                "CREATE TABLE IF NOT EXISTS " + FoodEntry.TABLE_NAME +
                        " ("
                        + FoodEntry.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + FoodEntry.COLUMN_NAME_NOMFood + " TEXT NOT NULL,"
                        + FoodEntry.COLUMN_NAME_CATEGORY_ID + " INTEGER NOT NULL,"
                        + FoodEntry.COLUMN_NAME_CAGE_ID + " INTEGER NOT NULL"
                        + " )";
    }
}
