package com.example.kevinschmidt.zooapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kevinschmidt.zooapp.R;
import com.example.kevinschmidt.zooapp.activity.Globals;
import com.example.kevinschmidt.zooapp.activity.MainActivity;
import com.example.kevinschmidt.zooapp.classes.EmployeeEntry;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooDataSource;

import java.util.List;

/**
 * Created by kevinschmidt on 10.03.16.
 */
public class AccountFragment extends Fragment {
    //Code that will be used to create a view of the Fragment - Just showing the information
    private View view;
    private ZooDataSource dataSource;
    private TextView edit_user;
    private EditText edit_pw;
    private EmployeeEntry myEmployee;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, container, false);
        //edit_user = (EditText)view.findViewById(R.id.text_view_showfirstname);
        context= container.getContext();
       return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // myEmployee = Read_Employee();
        //i have stored the information in the global class, that is availabe from
        //everywhere, so i get it here back and i fill then data of the
        // textfield in the frame account with global data
        final Globals g =(Globals)((MainActivity) getActivity()).getApplicationContext();

        edit_user = (TextView)view.findViewById(R.id.text_view_showfirstname);
        edit_user.setText(g.getFirstname());
        edit_user = (TextView)view.findViewById(R.id.text_view_showlastname);
        edit_user.setText(g.getLastname());
        edit_user = (TextView)view.findViewById(R.id.text_view_showbirthdate);
        edit_user.setText(g.getBirthdate());
    }
    public EmployeeEntry Read_Employee() {
        this.dataSource = new ZooDataSource(context);
        List<EmployeeEntry> EmployeeMemoList = this.dataSource.ReadDB_Employee_Source();
        return EmployeeMemoList.get(0);
    }

}
