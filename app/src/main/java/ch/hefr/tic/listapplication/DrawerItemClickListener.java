package ch.hefr.tic.listapplication;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;



/**
 * Created by jérémy on 03.03.2017.
 */

public class DrawerItemClickListener implements ListView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                Toast.makeText(view.getContext(),"Must create an Intent", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(),SimpleListActivity.class));
                break;
            case 1:
                Toast.makeText(view.getContext(),"Must open an activity", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "Must open an Activity", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(view.getContext(),"Do nothing", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "Do nothing", Toast.LENGTH_SHORT).show();
        }
    }



}
