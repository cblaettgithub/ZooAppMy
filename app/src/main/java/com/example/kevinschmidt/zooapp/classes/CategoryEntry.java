package com.example.kevinschmidt.zooapp.classes;

/**
 * Created by chris on 20.03.2016.
 */
public class CategoryEntry {

    private  int idCategorie;
    private  String NomCategorie;

    public CategoryEntry(String nomCategorie) {
        NomCategorie = nomCategorie;
    }
    public CategoryEntry(){};

    public int getIdCategorie() {
        return idCategorie;
    }
    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }
    public String getNomCategorie() {
        return NomCategorie;
    }
    public void setNomCategorie(String nomCategorie) {
        NomCategorie = nomCategorie;
    }
}
