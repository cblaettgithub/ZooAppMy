package com.example.kevinschmidt.zooapp.fragment;

import android.content.Context;
import android.os.Bundle;
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
import com.example.kevinschmidt.zooapp.classes.CageEntry;
import com.example.kevinschmidt.zooapp.classes.CategoryEntry;
import com.example.kevinschmidt.zooapp.classes.FoodEntry;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooDataSource;

import org.w3c.dom.Text;

import java.util.Iterator;
import java.util.List;

/**
 * Created by kevinschmidt, cblättler on 11.03.16, 22.3.2016
 */
public class FoodFragment extends Fragment
        {
    private View view;
    private ZooDataSource dataSource;
    private int positionCat;
    private int positionCage;
            private Context context;
    public int getPositionCage() {       return positionCage;    }
    public void setPositionCage(int positionCage) {
        this.positionCage = positionCage;    }
    public int getPositionCat() {        return positionCat;    }
    public void setPositionCat(int positionCat) {
        this.positionCat = positionCat;
    }
    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_food, container, false);
        setContext(container.getContext());

        //add event add to the button,
        final Button button = (Button) view.findViewById(R.id.button_add_food);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insert_Food();
            }
        });
        //add event to the spinnercat
        Spinner spinnercateg = (Spinner) view.findViewById(R.id.spinner_category);
        spinnercateg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setPositionCat(position + 1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //add event to the spinnercage
        final Spinner spinnercage = (Spinner)view.findViewById(R.id.spinner_cage);
        spinnercage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setPositionCage(position + 1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return view;

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        Button button = (Button) view.findViewById(R.id.button_add_food);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insert_Food();
            }
        });
        super.onViewCreated(view, savedInstanceState);
        FillSpinner_Category();
        FillSpinner_Cage();
    }

    public void FillSpinner_Category(){
        this.dataSource = new ZooDataSource(getContext());
        List<CategoryEntry> CategoryMemoList =  this.dataSource.ReadDB_Category_Souce();
        String[] myarray = new String[CategoryMemoList.size()];int i=0;
        Iterator iterator = (Iterator) CategoryMemoList.iterator();
        while(iterator.hasNext()) {
            CategoryEntry categoryEntry=(CategoryEntry) iterator.next();
            myarray[i]=categoryEntry.getNomCategorie();
            i++;
        }
        ArrayAdapter<String> CategoryMemoArrayAdapter = new ArrayAdapter<String> (
                getContext(), android.R.layout.simple_spinner_dropdown_item, myarray
        );
        Spinner CageSpinner  = (Spinner) view.findViewById(R.id.spinner_category);
        CategoryMemoArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        CageSpinner.setAdapter(CategoryMemoArrayAdapter);
        this.dataSource.close();
    }
    public void FillSpinner_Cage(){
        this.dataSource = new ZooDataSource(getContext());
        List<CageEntry> CategoryMemoList =  this.dataSource.ReadDB_Cage_Souce(0);
        String[] myarray = new String[CategoryMemoList.size()];int i=0;
        Iterator iterator = (Iterator) CategoryMemoList.iterator();
        while(iterator.hasNext()) {
            CageEntry cageEntry=(CageEntry) iterator.next();
            myarray[i]=String.valueOf(cageEntry.getCageName());
            i++;
        }
        ArrayAdapter<String> CategoryMemoArrayAdapter = new ArrayAdapter<String> (
                getContext(), android.R.layout.simple_spinner_dropdown_item, myarray
        );
        Spinner CageSpinner  = (Spinner) view.findViewById(R.id.spinner_cage);
        CategoryMemoArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        CageSpinner.setAdapter(CategoryMemoArrayAdapter);
        this.dataSource.close();
    }
    public void Insert_Food()
    {
        dataSource = new ZooDataSource(getContext());
        TextView    textView = (TextView)view.findViewById(R.id.textview_foodname);
        FoodEntry foodEntry = new FoodEntry(String.valueOf(textView.getText()), getPositionCat(), getPositionCage());
        dataSource.InsertFood(foodEntry);
    }
  }
