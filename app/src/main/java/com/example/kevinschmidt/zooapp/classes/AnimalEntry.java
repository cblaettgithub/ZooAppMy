package com.example.kevinschmidt.zooapp.classes;

/**
 * Created by chris on 17.03.2016.
 */
public class AnimalEntry {

    private int IdAnimal;
    private String  Name;
    private String Country ;
    private int IdCategory;
    private int  IdCage;

    public AnimalEntry(String name, String country, int idCategory, int idCage) {
        Name = name;
        Country = country;
        IdCategory = idCategory;
        IdCage = idCage;
    }

    public AnimalEntry() {
    }

    public int getIdAnimal() {
        return IdAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        IdAnimal = idAnimal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
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
}
