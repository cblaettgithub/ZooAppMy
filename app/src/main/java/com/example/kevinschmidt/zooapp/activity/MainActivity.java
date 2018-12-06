package com.example.kevinschmidt.zooapp.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;

import com.example.kevinschmidt.zooapp.R;
import com.example.kevinschmidt.zooapp.classes.EmployeeEntry;
import com.example.kevinschmidt.zooapp.classes.AnimalEntry;
import com.example.kevinschmidt.zooapp.database.sqlite.ZooDataSource;
import com.example.kevinschmidt.zooapp.fragment.AccountFragment;
import com.example.kevinschmidt.zooapp.fragment.AnimalFragment;
import com.example.kevinschmidt.zooapp.fragment.CageFragment;
import com.example.kevinschmidt.zooapp.fragment.FoodFragment;
import com.example.kevinschmidt.zooapp.fragment.LogoutFragment;
import com.example.kevinschmidt.zooapp.fragment.MapFragment;
import com.example.kevinschmidt.zooapp.fragment.MyDialogFragment;

import java.util.List;

/**
 * Created by kevinschmidt, Cblättler on 10.03.16.
 */
public class MainActivity extends AppCompatActivity implements  MyDialogFragment.NoticeDialogListener{

    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private ZooDataSource dataSource;
    private View mview;

    public View getMview() {
        return mview;
    }
    public void setMview(View mview) {
        this.mview = mview;
    }
    private Context context;
    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_layout_open, R.string.drawer_layout_open);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                navigationItem(item);
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    //Method used to navigate into the different fragments
    private void navigationItem(MenuItem menuItem)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;

        switch (menuItem.getItemId()){
            case R.id.fragment_account:
                fragment = new AccountFragment();
                break;
            case R.id.fragment_animals:
                fragment = new AnimalFragment();
                break;
            case R.id.fragment_cage:
                fragment = new CageFragment();
                break;
            case R.id.fragment_food:
                fragment = new FoodFragment();
                break;
            case R.id.fragment_maps:
                fragment = new MapFragment();
                break;
            case R.id.fragment_logout:
                fragment = new LogoutFragment();
                break;

        }
        //With this code it will replace the container with the selected fragment
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }
    public void ZooInsert(){
    }
    public void ZooUpdate(){
    }
    /*public void FillSpinner_Animal(){
        this.dataSource = new ZooDataSource(getContext());
        List<AnimalEntry> AnimalMemoList =  this.dataSource.ReadDB_Animal_Souce();
        ArrayAdapter<AnimalEntry> AnimalMemoArrayAdapter = new ArrayAdapter<> (
                this,
                android.R.layout.simple_list_item_multiple_choice,
                AnimalMemoList);
        Spinner CageSpinner  = (Spinner) getMview().findViewById(R.id.spinner_cage);
        //Spinner CageSpinner = (Spinner) findViewById(R.id.spinner_cage);
        CageSpinner.setAdapter(AnimalMemoArrayAdapter);
    }*/
    public void Delete_Spinner(){
    }

    public void Fill_ScrollView_Employee(){
        this.dataSource = new ZooDataSource(this);
        List<EmployeeEntry> EmployeeMemoList = this.dataSource.ReadDB_Employee_Source();
        ArrayAdapter<EmployeeEntry> EmployeeMemoArrayAdapter = new ArrayAdapter<> (
                this,
                android.R.layout.simple_list_item_multiple_choice,
                EmployeeMemoList);
        //ListView view = (ListView)findViewById(R.id.scroll_view_animals);
       // view.setAdapter(EmployeeMemoArrayAdapter);
        this.dataSource.close();
    }
    public void Fill_ScrollView_Animal(){
        this.dataSource = new ZooDataSource(this);
        List<AnimalEntry> AnimalMemoList = this.dataSource.ReadDB_Animal_Souce(1);
        ArrayAdapter<AnimalEntry> AnimalMemoArrayAdapter = new ArrayAdapter<> (
                this,
                android.R.layout.simple_list_item_multiple_choice,
                AnimalMemoList);

        ScrollView sview = (ScrollView) mview.findViewById(R.id.scroll_view_animals);
        //sview.setAdapter(AnimalMemoArrayAdapter);
        this.dataSource.close();
    }

    public void Update_ScrollView(){
    }
    public void Delete_SrollView(){
    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialogFragment) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialogFragment) {

    }
}

