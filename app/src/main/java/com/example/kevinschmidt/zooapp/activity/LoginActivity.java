package com.example.kevinschmidt.zooapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.kevinschmidt.zooapp.R;
import com.example.kevinschmidt.zooapp.classes.EmployeeEntry;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooDataSource;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooDbHelper;

import java.util.List;

/**
 * Created by kevinschmidt on 10.03.16.
 */
public class LoginActivity  extends AppCompatActivity{

    private Button button_login;
    private Intent intent;
    private ZooDataSource dataSource;
    private ZooDbHelper dbHelper;
    private EditText edit_user;
    private EditText edit_pw;
    private EmployeeEntry myEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //for the first time to create the db and fill the data
        //the second time disabled db_create and db_insert
        DB_Create();
        if(CheckDbEmpty()) DB_Insert();

        button_login = (Button)findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check_Login();
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void DB_Create()
    {
        this.dataSource = new ZooDataSource(this);
        this.dataSource.createDB();
        this.dataSource.close();
    }
    //all inserted values can be viewed in the fill_default_db class
    //also the username and pw in the EmployeeEntry there
    // to get a new database with entry, just delete wipe the data in (Tools)AVD Manager

    public void DB_Insert()
    {
        this.dataSource = new ZooDataSource(this);
        this.dataSource.InsertDB();//Employee and Animal
        this.dataSource.close();
    }

    public void Fill_ScrollView_Employee(){
        this.dataSource = new ZooDataSource(this);
        List<EmployeeEntry> AnimalMemoList =  (List<EmployeeEntry> )this.dataSource.ReadDB_Employee_Source();
        ArrayAdapter<EmployeeEntry> AnimalMemoArrayAdapter = new ArrayAdapter<> (
                this,
                android.R.layout.simple_list_item_multiple_choice,
                AnimalMemoList);
        ScrollView CageScroller = (ScrollView) findViewById(R.id.scroll_view_animals);
        this.dataSource.close();
    }
    public void Check_Login(){
        TextView textView_username =(TextView) findViewById(R.id.edit_text_username);
        TextView textView_pw =(TextView) findViewById(R.id.edit_text_password);
        this.dataSource = new ZooDataSource(this);
        List<EmployeeEntry> EmployeeMemoList = this.dataSource.ReadDB_Employee_User(
                String.valueOf("'"+textView_username.getText())+"'",  "'"+String.valueOf(textView_pw.getText())+"'");
        if (EmployeeMemoList!=null){ //if not null the user exist in the database
            EmployeeEntry employeeEntry = EmployeeMemoList.get(0);
            textView_username.setText("Erfolgreich");
            //for after having the data of the user i saved it in the global class
            //so I can get from every frame, in our theme i get it back in the account
            //fragment

            final Globals g = (Globals) getApplicationContext();
            g.setFirstname(employeeEntry.getFirstname());
            g.setLastname(employeeEntry.getLastname());
            g.setBirthdate(employeeEntry.getBirthdate());
        }
        else //the user doesn't exist
            textView_username.setText("Nicht vorhanden");

    }
    //it checks if the db exist, if not or just has been deletet
    //it creates a new one and fill it with the data of the classe
    //Fill Default Db
    public boolean CheckDbEmpty()
    {
        this.dataSource = new ZooDataSource(this);
        List<EmployeeEntry> EmployeeMemoList = this.dataSource.ReadDB_Employee_User("'Stefan'",  "'pw'");
        if(!this.dataSource.isDBFull())
            return true;
        else
            return false;
    }
}
