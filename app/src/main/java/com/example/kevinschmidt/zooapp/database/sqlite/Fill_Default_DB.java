package com.example.kevinschmidt.zooapp.database.sqlite;

import android.database.sqlite.SQLiteDatabase;

import com.example.kevinschmidt.zooapp.classes.AnimalEntry;
import com.example.kevinschmidt.zooapp.classes.CageEntry;
import com.example.kevinschmidt.zooapp.classes.CategoryEntry;
import com.example.kevinschmidt.zooapp.classes.EmployeeEntry;
import com.example.kevinschmidt.zooapp.classes.FoodEntry;
import com.example.kevinschmidt.zooapp.database.queries.ZooInsert_Animal;
import com.example.kevinschmidt.zooapp.database.queries.ZooInsert_Cage;
import com.example.kevinschmidt.zooapp.database.queries.ZooInsert_Category;
import com.example.kevinschmidt.zooapp.database.queries.ZooInsert_Employee;
import com.example.kevinschmidt.zooapp.database.queries.ZooInsert_Food;

/**
 * Created by chris on 26.03.2016.
 */
public class Fill_Default_DB {

    public Fill_Default_DB() {
    }
    //fill the database after the creation with default values
    public void Fill_Default_Values_DB(SQLiteDatabase db)//employee and animals
    {
        ZooInsert_Employee myEmploye = new ZooInsert_Employee();
        ZooInsert_Animal myAnimal = new ZooInsert_Animal();
        ZooInsert_Cage myCage = new ZooInsert_Cage();
        ZooInsert_Category myCategory = new ZooInsert_Category();
        ZooInsert_Food myFood = new ZooInsert_Food();

        //Employee
        EmployeeEntry employeeEntry = new EmployeeEntry("Stefan", "pw", "Stefan", "Meier", "8.10.1992");
        db.insert(ZooContract.EmployeeEntry.TABLE_NAME, null,  myEmploye.Zoo_Insert_Employee(employeeEntry));
        employeeEntry = new EmployeeEntry("chris", "chris", "Christoph", "Meier", "12.11.1991");
        db.insert(ZooContract.EmployeeEntry.TABLE_NAME, null,  myEmploye.Zoo_Insert_Employee(employeeEntry));
        employeeEntry = new EmployeeEntry("kevin", "kevin", "Kevn", "Müller", "18.10.1989");
        db.insert(ZooContract.EmployeeEntry.TABLE_NAME, null,  myEmploye.Zoo_Insert_Employee(employeeEntry));
        employeeEntry = new EmployeeEntry("frank", "frank", "Frank", "Amstalden", "18.10.1986");
        db.insert(ZooContract.EmployeeEntry.TABLE_NAME, null,  myEmploye.Zoo_Insert_Employee(employeeEntry));

        //Category
        CategoryEntry categoryEntry = new CategoryEntry("Elefant Category");
        db.insert(ZooContract.CategorieEntry.TABLE_NAME, null,  myCategory.Zoo_Insert_Category(categoryEntry));
        categoryEntry = new CategoryEntry("Tiger Category");
        db.insert(ZooContract.CategorieEntry.TABLE_NAME, null,  myCategory.Zoo_Insert_Category(categoryEntry));
        categoryEntry = new CategoryEntry("Giraffe Category");
        db.insert(ZooContract.CategorieEntry.TABLE_NAME, null,  myCategory.Zoo_Insert_Category(categoryEntry));
        categoryEntry = new CategoryEntry("Leopard Category");
        db.insert(ZooContract.CategorieEntry.TABLE_NAME, null,  myCategory.Zoo_Insert_Category(categoryEntry));

        //Cages step alway for all categories
        CageEntry cagesEntry = new CageEntry("Elefanten Cage",150, 1);
        db.insert(ZooContract.CagesEntry.TABLE_NAME, null,    myCage.Zoo_Insert_Cage(cagesEntry));
        cagesEntry = new CageEntry("Tiger Cage",250, 2);
        db.insert(ZooContract.CagesEntry.TABLE_NAME, null,    myCage.Zoo_Insert_Cage(cagesEntry));
        cagesEntry = new CageEntry("Giraffe Cage",350, 3);
        db.insert(ZooContract.CagesEntry.TABLE_NAME, null,    myCage.Zoo_Insert_Cage(cagesEntry));
        cagesEntry = new CageEntry("Leopard Cage",450, 4);
        db.insert(ZooContract.CagesEntry.TABLE_NAME, null,    myCage.Zoo_Insert_Cage(cagesEntry));

         cagesEntry = new CageEntry("Elefanten Cage 2",150, 1);
        db.insert(ZooContract.CagesEntry.TABLE_NAME, null,    myCage.Zoo_Insert_Cage(cagesEntry));
        cagesEntry = new CageEntry("Tiger Cage 2",250, 2);
        db.insert(ZooContract.CagesEntry.TABLE_NAME, null,    myCage.Zoo_Insert_Cage(cagesEntry));
        cagesEntry = new CageEntry("Giraffe Cage 2",350, 3);
        db.insert(ZooContract.CagesEntry.TABLE_NAME, null,    myCage.Zoo_Insert_Cage(cagesEntry));
        cagesEntry = new CageEntry("Leopard Cage 2",450, 4);
        db.insert(ZooContract.CagesEntry.TABLE_NAME, null,    myCage.Zoo_Insert_Cage(cagesEntry));

         cagesEntry = new CageEntry("Elefanten Cage 3",150, 1);
        db.insert(ZooContract.CagesEntry.TABLE_NAME, null,    myCage.Zoo_Insert_Cage(cagesEntry));
        cagesEntry = new CageEntry("Tiger Cage 3",250, 2);
        db.insert(ZooContract.CagesEntry.TABLE_NAME, null,    myCage.Zoo_Insert_Cage(cagesEntry));
        cagesEntry = new CageEntry("Giraffe Cage 3",350, 3);
        db.insert(ZooContract.CagesEntry.TABLE_NAME, null,    myCage.Zoo_Insert_Cage(cagesEntry));
        cagesEntry = new CageEntry("Leopard Cage3 ",450, 4);
        db.insert(ZooContract.CagesEntry.TABLE_NAME, null,    myCage.Zoo_Insert_Cage(cagesEntry));

        //Animal
        AnimalEntry animalEntry = new AnimalEntry("Elefant 1", "Afrika", 1, 1);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Elefant 2", "Afrika", 1, 1);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Elefant 3", "Antarktis", 1, 1);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Elefant 4", "Indien", 1, 1);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Elefant 5", "Südamerika", 1, 1);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));

        animalEntry = new AnimalEntry("Tiger 1", "Europe", 2, 2);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Tiger 2", "Afrika", 2, 2);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Tiger 3", "Europa", 2, 2);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Tiger 4", "North Afrika", 2, 2);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Tiger 5", "China", 2, 2);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));

        animalEntry = new AnimalEntry("Giraffe 1", "USA", 3, 3);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Giraffe 2", "Taiwan", 3, 3);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Giraffe 3", "China", 3, 3);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Giraffe 4", "Japan", 3, 3);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Giraffe 5", "South Korea",  3, 3);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Giraffe 6", "USA", 3, 3);

        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Leopard 1", "Europa",4, 4);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Leopard 2", "Afrika",4, 4);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Leopard 3", "USA",4, 4);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Leopard 4", "China",4, 4);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        animalEntry = new AnimalEntry("Leopard 5", "Japan",4, 4);
        db.insert(ZooContract.AnimalEntry.TABLE_NAME, null,    myAnimal.Zoo_Insert_AnimalEntry(animalEntry));
        //food
        FoodEntry foodEntry = new FoodEntry("Gras", 1, 1);
        db.insert(ZooContract.FoodEntry.TABLE_NAME, null,  myFood.Zoo_InsertFoodEntry(foodEntry));
        foodEntry = new FoodEntry("Water", 2, 2);
        db.insert(ZooContract.FoodEntry.TABLE_NAME, null,  myFood.Zoo_InsertFoodEntry(foodEntry));
        foodEntry = new FoodEntry("Milk", 3, 3);
        db.insert(ZooContract.FoodEntry.TABLE_NAME, null,  myFood.Zoo_InsertFoodEntry(foodEntry));
        foodEntry = new FoodEntry("Cereals", 4, 4);
        db.insert(ZooContract.FoodEntry.TABLE_NAME, null,  myFood.Zoo_InsertFoodEntry(foodEntry));
    }
}
