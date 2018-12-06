package com.example.kevinschmidt.zooapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kevinschmidt.zooapp.R;
import com.example.kevinschmidt.zooapp.classes.AnimalEntry;
import com.example.kevinschmidt.zooapp.classes.CategoryEntry;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooDataSource;

import java.util.Iterator;
import java.util.List;

/**
 * Created by chris on 23.03.2016.
 */
public class AnimalInsertFragment extends Fragment implements   AdapterView.OnItemSelectedListener
 {
    private View view;
    public void setView(View view) {
        this.view = view;
    }
    private Context context;

    private ZooDataSource dataSource;
    private int selectedCategorie;
    int selectCat;
    private int AddUpdate;
     private int IDanimal;
     public int getIDanimal() {  return IDanimal;     }
     public void setIDanimal(int IDanimal) { this.IDanimal = IDanimal;   }

     public int getSelectedCategorie() {return selectedCategorie;   }
    public void setSelectedCategorie(int selectedCategorie) { this.selectedCategorie = selectedCategorie; }

    @Nullable
    @Override
    public View getView() {
        return view;
    }
    @Override
    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_insert_animal, container, false);
        setContext(container.getContext());
       setSelectedCategorie(1);
        //in that cause it is the updata process, !=0, instead the add process
        if (getIDanimal()!=0){
            Show_Data_animal();
        }

        //event for the add button
        Button button = (Button) view.findViewById(R.id.button_add_animal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Animal();
            }
        });
        //Fill DAta in animal textview
        Button button_upd = (Button) view.findViewById(R.id.button_update_animal);
        button_upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Updat_Animal();
            }
        });
        //event for the cagespinner
        Spinner CageSpinner = (Spinner) view.findViewById(R.id.spinner_category);
        CageSpinner.setOnItemSelectedListener(this);
        FillSpinner_Category();
        this.dataSource = new ZooDataSource(getContext());
        selectCat=getSelectedCategorie();
        setView(view);
        return view ;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
    }
    public void FillSpinner_Category() {
        this.dataSource = new ZooDataSource(getContext());
        List<CategoryEntry> CategoryMemoList = this.dataSource.ReadDB_Category_Souce();
        String[] myarray = new String[CategoryMemoList.size()];
        int i = 0; //i fill the data from the database in an array of string, myarray
        Iterator iterator = (Iterator) CategoryMemoList.iterator();
        while (iterator.hasNext()) {
            CategoryEntry categoryEntry = (CategoryEntry) iterator.next();
            myarray[i] = categoryEntry.getNomCategorie();
            i++;
        }
        ArrayAdapter<String> CategoryMemoArrayAdapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_dropdown_item, myarray
        );
       /* ArrayAdapter<CategoryEntry> CategoryMemoArrayAdapter1 = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                this.dataSource.ReadDB_Category_Souce());*/
        //fill the spinner with the data of the table categorie from the database
        Spinner CageSpinner = (Spinner) view.findViewById(R.id.spinner_category);
        CategoryMemoArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        CageSpinner.setAdapter(CategoryMemoArrayAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.selectedCategorie=position+1;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
     public void Add_Animal() {
         AnimalEntry animalEntry = getTextfieldValues();
         Add_update_Animal(1, animalEntry);
     }
     public void Updat_Animal(){
         AnimalEntry animalEntry = getTextfieldValues();
         Add_update_Animal(2, animalEntry);
     }

     //to fill first values of the textfield in the update process
     @NonNull
     private AnimalEntry getTextfieldValues() {
         TextView textViewA = (TextView) view.findViewById(R.id.textview_animal);
         TextView textViewC = (TextView) view.findViewById(R.id.textview_country);
         return new AnimalEntry(String.valueOf(textViewA.getText()),
                 String.valueOf(textViewC.getText()),getSelectedCategorie() ,selectCat);
     }
     public void Add_update_Animal(int value, AnimalEntry animalEntry){
         switch (value){
             //add a new animal
             case 1:
                 dataSource.InsertCage(animalEntry);
                 break;
             //update the animal
             case 2:
                 dataSource.UpdateAnimal(String.valueOf(getIDanimal()), animalEntry);
                 break;
             default:
         }
     }
     //to fill first values of the textfield in the update process
     public void Show_Data_animal()
     {
         int id = getIDanimal();
         dataSource = new ZooDataSource(getContext());
         List<AnimalEntry> AnimalMemoList=this.dataSource.ReadDB_Animal_Souce_id(id);
         AnimalEntry animalEntry=AnimalMemoList.get(0);
         TextView textViewA = (TextView) view.findViewById(R.id.textview_animal);
         TextView textViewC = (TextView) view.findViewById(R.id.textview_country);
         textViewA.setText(String.valueOf(animalEntry.getName()));
         textViewC.setText(String.valueOf(animalEntry.getCountry()));
         this.dataSource.close();
     }
}
