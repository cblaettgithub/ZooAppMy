package com.example.kevinschmidt.zooapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.kevinschmidt.zooapp.R;



/**
 * Created by kevinschmidt on 10.03.16.
 */
public class LogoutFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_logout, container, false);
        return view;
    }

}
