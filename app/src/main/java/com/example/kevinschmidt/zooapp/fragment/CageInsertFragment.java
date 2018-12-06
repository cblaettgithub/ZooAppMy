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
import com.example.kevinschmidt.zooapp.classes.CageEntry;
import com.example.kevinschmidt.zooapp.classes.CategoryEntry;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooDataSource;

import java.util.Iterator;
import java.util.List;

/**
 * Created by chris on 23.03.2016.
 */
public class CageInsertFragment extends Fragment implements   AdapterView.OnItemSelectedListener
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
     private int IDCage;
     public int getIDCage() {
         return IDCage;
     }
     public void setIDCage(int IDCage) {
         this.IDCage = IDCage;
     }
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
        view = inflater.inflate(R.layout.fragment_insert_cage, container, false);
        setContext(container.getContext());
       setSelectedCategorie(1);
        //then update
        if (getIDCage()!=0){
            Show_Data_cage();
        }

        //event for the add button
        Button button = (Button) view.findViewById(R.id.button_add_animal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Cage();
            }
        });
        //Fill DAta in animal textview
        Button button_upd = (Button) view.findViewById(R.id.button_update_animal);
        button_upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Updat_Cage();
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
        int i = 0;
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
     public void Add_Cage() {
         CageEntry cageEntry = getTextfieldValues();
         Add_Update_Cage(1, cageEntry);
     }
     public void Updat_Cage(){
         CageEntry cageEntry = getTextfieldValues();
         Add_Update_Cage(2, cageEntry);
     }

     @NonNull
     private CageEntry getTextfieldValues() {
         TextView textViewA = (TextView) view.findViewById(R.id.textview_cage);
         TextView textViewC = (TextView) view.findViewById(R.id.textview_cagesize);
         return new CageEntry(String.valueOf(textViewA.getText()),
                 Integer.parseInt(String.valueOf(textViewC.getText())), getSelectedCategorie());
     }
     public void Add_Update_Cage(int value, CageEntry cageEntry){
         switch (value){
             //add
             case 1:
                 dataSource.InsertCage(cageEntry);
                 break;
             //update
             case 2:
                 dataSource.UpdateCage(String.valueOf(getIDCage()), cageEntry);
                 break;
             default:
         }
     }
     public void Show_Data_cage()
     {
         int id = getIDCage();
         dataSource = new ZooDataSource(getContext());
         List<CageEntry> AnimalMemoList=this.dataSource.ReadDB_Cage_Souce_ID(id);
         CageEntry cageEntry=AnimalMemoList.get(0);
         TextView textViewA = (TextView) view.findViewById(R.id.textview_cage);
         TextView textViewC = (TextView) view.findViewById(R.id.textview_cagesize);
         textViewA.setText(String.valueOf(cageEntry.getCageName()));
         textViewC.setText(String.valueOf(cageEntry.getCageSize()));
         this.dataSource.close();
     }
}
