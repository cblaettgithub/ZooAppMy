package com.example.kevinschmidt.zooapp.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kevinschmidt.zooapp.R;
import com.example.kevinschmidt.zooapp.classes.AnimalEntry;
import com.example.kevinschmidt.zooapp.classes.CategoryEntry;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooDataSource;

import java.util.Iterator;
import java.util.List;

/**
 * Created by kevinschmidt, cblättler on 11.03.16.
 */
public class AnimalFragment extends Fragment implements
        AdapterView.OnItemSelectedListener
{
    private View view;
    @Nullable
    @Override
    public View getView() {
        return view;
    }
    public void setView(View view) {
        this.view = view;
    }
    private ZooDataSource dataSource;
    private Context context;
    private FragmentManager me;
    private int idanimal;
    public int getIdanimal() {       return idanimal;    }
    public void setIdanimal(int idanimal) {
        this.idanimal = idanimal;
    }

    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    AnimalInsertFragment animalInsertFragment;

    private int position;
    public FragmentManager getMe() {
        return me;
    }
    public void setMe(FragmentManager me) {
        this.me = me;
    }
    @Override
    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_animal, container, false);
        setContext(container.getContext());
        Fill_ScrollView_Animal(view, 1);

        //add event to the button, replace fragment with adding an animal
        final Button button = (Button) view.findViewById(R.id.button_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Replace_Fragment(0);
            }
        });

        //add event to the button update from the choosen animal
        final Button button_update = (Button) view.findViewById(R.id.button_show);
        button_update.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Fill_ScrollView_Animal(getView(), position);
                Replace_Fragment(getIdanimal());
            }
        });

        //add event to the button, to del the choosen animal in the scrollview
        final Button button_del = (Button) view.findViewById(R.id.button_remove);
        button_del.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //i did't work with the dialog form, it shows it after deleting
                //android.support.v4.app.DialogFragment dialog = new MyDialogFragment();
                //dialog.show(getFragmentManager(), "NoticeDialogFragment");
                Fill_ScrollView_Animal(getView(), position);
                Delete_Animal(getIdanimal());

            }
        });

        //add evento to the spinner
        Spinner CageSpinner = (Spinner) view.findViewById(R.id.spinner_category);
        CageSpinner.setOnItemSelectedListener(this);
        FillSpinner_Category();
        setView(view);
        return view ;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
    }
    //getting with the id of the category of its animal
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //CategoryEntry categoryEntry= (CategoryEntry)parent.getItemAtPosition(position);
        //String text = categoryEntry.getNomCategorie();
        setPosition(position + 1);
        Fill_ScrollView_Animal(getView(), position + 1);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
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

        Spinner CageSpinner = (Spinner) view.findViewById(R.id.spinner_category);
        CategoryMemoArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        CageSpinner.setAdapter(CategoryMemoArrayAdapter);
    }
    public void Fill_ScrollView_Animal(View view, int pos) {
        this.dataSource = new ZooDataSource(getContext());
        List<AnimalEntry> AnimalMemoList = this.dataSource.ReadDB_Animal_Souce(pos);
        int size = AnimalMemoList.size();

        ArrayAdapter<AnimalEntry> AnimalMemoArrayAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_multiple_choice,
                AnimalMemoList);
        ScrollView lview = (ScrollView) view.findViewById(R.id.scroll_view_animals);
        LinearLayout linearLayout = (LinearLayout) view.findViewById((R.id.linear_layout_text));
        linearLayout.removeAllViewsInLayout();
        Iterator iterator = (Iterator) AnimalMemoList.iterator();

        //setting click listen for every item in the scrollview + setting id of animal
        // in the textview as textview.setid
        //for after deleting and update i need the id, so therefore i set it her
        //after the updating, deleting is started it gets the id form the textfield
        while (iterator.hasNext()) {
            AnimalEntry animalEntry = (AnimalEntry) iterator.next();
            final TextView textView = new TextView(getContext());
            textView.setText(animalEntry.getName() + " : " + animalEntry.getCountry());
            textView.setId(animalEntry.getIdAnimal());
            linearLayout.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    setIdanimal(textView.getId());
                    //Delete_Animal(String.valueOf(textView.getId()));
                }  ;
            });
            this.dataSource.close();
        }
    }
    public void Fill_ScrollView_Animal_Test(String content) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById((R.id.linear_layout_text));
        linearLayout.removeAllViewsInLayout();
        TextView textView = new TextView(getContext());
        textView.setText("Content: " + content);
        linearLayout.addView(textView);
    }
    public void Replace_Fragment(int animalID){
        animalInsertFragment = new AnimalInsertFragment();
        Bundle args= new Bundle();
        args.putInt("Test", 1);
        animalInsertFragment.setIDanimal(animalID);
        animalInsertFragment.setArguments(args);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, animalInsertFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void Delete_Animal(int id){
        ZooDataSource dataSource = new ZooDataSource(getContext());
        dataSource.DeleteAnimal(String.valueOf(id));
        Fill_ScrollView_Animal(getView(), position);
        this.dataSource.close();
    }



}
