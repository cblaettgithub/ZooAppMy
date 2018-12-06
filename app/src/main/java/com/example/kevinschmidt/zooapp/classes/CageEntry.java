package com.example.kevinschmidt.zooapp.classes;

import com.example.kevinschmidt.zooapp.database.sqlite.ZooContract;

/**
 * Created by chris on 20.03.2016.
 */
public class CageEntry {
    private int idCage;
    private String CageName;
    private int cageSize;
    private int IdCategory;

    public CageEntry(String cageName, int cageSize, int idCategory) {
        CageName = cageName;
        this.cageSize = cageSize;
        IdCategory = idCategory;
    }

    public CageEntry() {
    }

    public int getIdCage() {
        return idCage;
    }

    public void setIdCage(int idCage) {
        this.idCage = idCage;
    }

    public String getCageName() {
        return CageName;
    }

    public void setCageName(String cageName) {
        CageName = cageName;
    }

    public int getCageSize() {
        return cageSize;
    }

    public void setCageSize(int cageSize) {
        this.cageSize = cageSize;
    }

    public int getIdCategory() {
        return IdCategory;
    }

    public void setIdCategory(int idCategory) {
        IdCategory = idCategory;
    }



}
