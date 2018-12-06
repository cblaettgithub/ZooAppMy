package com.example.kevinschmidt.zooapp.fragment;

import android.content.Context;
import android.os.Bundle;
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
import com.example.kevinschmidt.zooapp.classes.CageEntry;
import com.example.kevinschmidt.zooapp.classes.CategoryEntry;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooDataSource;

import java.util.Iterator;
import java.util.List;

/**
 * Created by kevinschmidt,cblättler on 11.03.16, 28.3.2016
 */
public class CageFragment extends Fragment  implements AdapterView.OnItemSelectedListener{

    private Context context;
    private View view;
    private int IdCage;
    public int getIdCage() {
        return IdCage;
    }
    public void setIdCage(int idCage) {
        IdCage = idCage;
    }
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }
    public View getView() {
        return view;
    }
    public void setView(View view) {
        this.view = view;
    }
    private ZooDataSource dataSource;
    CageInsertFragment cageInsertFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cage, container, false);
        setContext(container.getContext());
        Fill_ScrollView_Cage(view,1);

        //add event to the button,  adding an cage
        final Button button = (Button) view.findViewById(R.id.button_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Replace_Fragment(0);
            }
        });

        //add event to the button update from the choosen cage
        final Button button_update = (Button) view.findViewById(R.id.button_show);
        button_update.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Fill_ScrollView_Cage(getView(), position);
                Replace_Fragment(getIdCage());
            }
        });

        //add event to the button, to del the choosen cage in the scrollview
        final Button button_del = (Button) view.findViewById(R.id.button_remove);
        button_del.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //android.support.v4.app.DialogFragment dialog = new MyDialogFragment();
                //dialog.show(getFragmentManager(), "NoticeDialogFragment");
                Fill_ScrollView_Cage(getView(), position);
                Delete_Cage(getIdCage());
            }
        });

        Spinner CageSpinner = (Spinner) view.findViewById(R.id.spinner_category);
        CageSpinner.setOnItemSelectedListener(this);
        FillSpinner_Category();
        setView(view);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String item = parent.getItemAtPosition(position).toString();
        setPosition(position+1);
        Fill_ScrollView_Cage(getView(), position + 1);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
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
    }

    public void Fill_ScrollView_Cage(View view, int pos) {
        this.dataSource = new ZooDataSource(getContext());
        List<CageEntry> CageMemoList = this.dataSource.ReadDB_Cage_Souce(pos);
        int size = CageMemoList.size();

        ArrayAdapter<CageEntry> AnimalMemoArrayAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_multiple_choice,
                CageMemoList);
        ScrollView lview = (ScrollView) view.findViewById(R.id.scroll_view_cage);
        LinearLayout linearLayout = (LinearLayout) view.findViewById((R.id.linear_layout_text_cage));
        linearLayout.removeAllViewsInLayout();

        Iterator iterator = (Iterator) CageMemoList.iterator();
        while(iterator.hasNext()){
            final TextView textView = new TextView(getContext());
            CageEntry cageEntry=(CageEntry) iterator.next();
            textView.setId(cageEntry.getIdCage());
            textView.setText(cageEntry.getCageName() + " :Surface : " + cageEntry.getCageSize() + " m2 ");
            linearLayout.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    setIdCage(textView.getId());
                    //Delete_Animal(String.valueOf(textView.getId()));
                }
                ;
            });
        }
        this.dataSource.close();
    }
    public void Replace_Fragment(int CageID){
        cageInsertFragment = new CageInsertFragment();
        Bundle args= new Bundle();
        args.putInt("Test", 1);
        cageInsertFragment.setIDCage(CageID);
        cageInsertFragment.setArguments(args);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, cageInsertFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void Delete_Cage(int id){
        ZooDataSource dataSource = new ZooDataSource(getContext());
        dataSource.DeleteCage(String.valueOf(id));
        Fill_ScrollView_Cage(getView(), position);
        this.dataSource.close();
    }
}
