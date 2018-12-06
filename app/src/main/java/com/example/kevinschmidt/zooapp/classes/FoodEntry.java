package com.example.kevinschmidt.zooapp.classes;

/**
 * Created by chris on 23.03.2016.
 */
public class FoodEntry {
    public  int idFood;
    public  String NomFood;
    public int IdCategory;
    public int IdCage;

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public String getNomFood() {
        return NomFood;
    }

    public void setNomFood(String nomFood) {
        NomFood = nomFood;
    }

    public int getIdCategory() {
        return IdCategory;
    }

    public void setIdCategory(int idCategory) {
        IdCategory = idCategory;
    }

    public int getIdCage() {
        return IdCage;
    }

    public void setIdCage(int idCage) {
        IdCage = idCage;
    }

    public FoodEntry(String nomFood, int idCategory, int idCage) {
        NomFood = nomFood;
        IdCategory = idCategory;
        IdCage = idCage;
    }

    public FoodEntry() {
    }


}
