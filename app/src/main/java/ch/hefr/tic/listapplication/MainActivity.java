package ch.hefr.tic.listapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<String> itemslistArray;
    private ArrayAdapter simpleAAdapter;
    private ArrayList<AndroidVersion> androidList;
    private AndroidAdapter adapter;
    private ListView simpleListView;

    private int position;

    //Drawer
    private String[] mMenuSections;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Create Simple list
        itemslistArray = new ArrayList<String>();
        itemslistArray.add("Item1");
        itemslistArray.add("Item2");
        itemslistArray.add("Item3");
        //Create adaptater
        simpleAAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itemslistArray);
        simpleListView = (ListView) findViewById(R.id.simple_list_view);
        simpleListView.setAdapter(simpleAAdapter);


        //Custom view adapter
        androidList = new ArrayList<AndroidVersion>();
        initList(androidList);
        adapter = new AndroidAdapter(this, R.layout.android_version_layout, androidList);
        final ListView list = (ListView) findViewById(R.id.custom_list_view);
        list.setAdapter(adapter);

        //Listener on floating button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manageItem("",0);
            }
        });

        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                position = i;
                manageItem(simpleAAdapter.getItem(position).toString(),position);
            }
        });

        //Create drawer
        mMenuSections = getResources().getStringArray(R.array.menu_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_menu);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.left_drawer,mMenuSections));



        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name,R.string.app_name){
            public void onDrawerClosed(View view){
                Log.d("HomeActivity" , "onDrawerClosed");
            }
            public void onDrawerOpened(View drawView){
                Log.d("HomeActivity" , "onDrawerOpened");
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean isNew;

        switch (item.getItemId()){
            case R.id.action_add:
                isNew = true;
                manageItem("",0);
                return true;
            case R.id.action_modify:
                isNew = false;
                String listItem = (String) simpleListView.getItemAtPosition(position);
                if(listItem == "")
                    Toast.makeText(this,"No item selected",Toast.LENGTH_SHORT).show();
                else
                    manageItem( (String) simpleListView.getItemAtPosition(position) , position);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    public void manageItem(final String item,int position){
        final boolean isNew;
        final int getPosition = position;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.input_dialog_layout, null);
        //Set other dialog properties


        final EditText newItemTextField = (EditText) dialogView.findViewById(R.id.edittext);
        if(item != ""){
            builder.setTitle("Edit item").setView(dialogView);
            newItemTextField.setText(item);
            isNew = false;
        }else{
            builder.setTitle("Add new item").setView(dialogView);
            isNew = true;
        }
        //Add the buttons
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int id) {
                if(isNew){
                    itemslistArray.add(String.valueOf(newItemTextField.getText()));
                }else{
                    itemslistArray.set(getPosition, newItemTextField.getText().toString());
                }
                simpleAAdapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int id) {
                //User cancelled the dialog
            }
        });

        //Create the AlerDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void initList(ArrayList<AndroidVersion> androidList) {
        AndroidVersion version = new AndroidVersion();
        version.setVersionName("Cupcake");
        version.setVersionNumber("1.5");
        androidList.add(version);

        AndroidVersion versionDonut = new AndroidVersion();
        versionDonut.setVersionName("Donut");
        versionDonut.setVersionNumber("1.6");
        androidList.add(versionDonut);

        AndroidVersion versionEclair = new AndroidVersion();
        versionEclair.setVersionName("Eclair");
        versionEclair.setVersionNumber("2.0.x");
        androidList.add(versionEclair);

        AndroidVersion versionFroyo = new AndroidVersion();
        versionFroyo.setVersionName("Froyo");
        versionFroyo.setVersionNumber("2.2.x");
        androidList.add(versionFroyo);

        AndroidVersion versionGinger = new AndroidVersion();
        versionGinger.setVersionName("Gingerbread");
        versionGinger.setVersionNumber("2.3.x");
        androidList.add(versionGinger);

        AndroidVersion versionHoneycomb = new AndroidVersion();
        versionHoneycomb.setVersionName("Honeycomb");
        versionHoneycomb.setVersionNumber("3.x");
        androidList.add(versionHoneycomb);

        AndroidVersion versionIcs = new AndroidVersion();
        versionIcs.setVersionName("Ice Cream Sandwich");
        versionIcs.setVersionNumber("4.0.x");
        androidList.add(versionIcs);

        AndroidVersion versionJellyBean = new AndroidVersion();
        versionJellyBean.setVersionName("Jelly Bean");
        versionJellyBean.setVersionNumber("4.1.x");
        androidList.add(versionJellyBean);

        AndroidVersion versionKitKat = new AndroidVersion();
        versionKitKat.setVersionName("KitKat");
        versionKitKat.setVersionNumber("4.4.x");
        androidList.add(versionKitKat);

        AndroidVersion versionLollipop = new AndroidVersion();
        versionLollipop.setVersionName("Lollipop");
        versionLollipop.setVersionNumber("5.x");
        androidList.add(versionLollipop);
    }

    public Context getApplicationContext(){
        return this;
    }



}
