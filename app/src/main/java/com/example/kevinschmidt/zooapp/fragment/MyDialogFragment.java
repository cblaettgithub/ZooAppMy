package com.example.kevinschmidt.zooapp.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.AlertDialog;
import android.support.v4.app.Fragment;

import com.example.kevinschmidt.zooapp.database.sqlite.ZooDataSource;

import java.util.Objects;

/**
 * Created by chris on 26.03.2016.
 */
public class MyDialogFragment extends android.support.v4.app.DialogFragment {
    private ZooDataSource dataSource;
    private int IDAnimal;
    public int getIDAnimal() {       return IDAnimal;    }
    public void setIDAnimal(int IDAnimal) {
        this.IDAnimal = IDAnimal;
    }

    private NoticeDialogListener mListener= new NoticeDialogListener() {
        @Override
        public void onDialogPositiveClick(DialogFragment dialogFragment) {
            Delete_item();
        }

        @Override
        public void onDialogNegativeClick(DialogFragment dialogFragment) {

        }
    };

    public interface NoticeDialogListener{
        public void onDialogPositiveClick(DialogFragment dialogFragment);
        public void onDialogNegativeClick(DialogFragment dialogFragment);
    }
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        /*int value=savedInstanceState.getInt("AnimalID");*/
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Deleting Animal:");
        builder.setMessage("Do you want to delete the item ?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mListener.onDialogPositiveClick(MyDialogFragment.this);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mListener.onDialogNegativeClick(MyDialogFragment.this);
                // You don't have to do anything here if you just want it dismissed when clicked
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    /*@Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            mListener = (NoticeDialogListener) activity;
        }
        catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
        }
    }*/
    public void Delete_item()
    {
        ZooDataSource dataSource = new ZooDataSource(getContext());
        dataSource.DeleteAnimal("3");
        //this.dataSource.close();
    }
}