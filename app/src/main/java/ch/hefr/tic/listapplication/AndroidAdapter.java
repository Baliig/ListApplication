package ch.hefr.tic.listapplication;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by jérémy on 25.02.2017.
 */

public class AndroidAdapter extends ArrayAdapter<AndroidVersion> {

    private ArrayList<AndroidVersion> androidVersionsList;
    private Context context;
    private int viewRes;
    private Resources res;


    public AndroidAdapter(Context context, int textViewResourceId, ArrayList<AndroidVersion> versions){
        super(context,textViewResourceId,versions);
        this.androidVersionsList = versions;
        this.context = context;
        this.viewRes = textViewResourceId;
        this.res = context.getResources();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes,parent, false);
        }

        final AndroidVersion androidVersion = androidVersionsList.get(position);
        if(androidVersion != null){
            final ImageView imageView = (ImageView) view.findViewById(R.id.android_icon);
            final TextView title = (TextView) view.findViewById(R.id.android_name);
            final TextView description = (TextView) view.findViewById(R.id.android_version);

            imageView.setImageResource(R.mipmap.android_icon);
            //title.setText(androidVersion.getVersionName());
            //description.setText(androidVersion.getVersionNumber());
            final String versionName = String.format(res.getString(R.string.list_title) + " " + androidVersion.getVersionName());
            title.setText(versionName);
            final String versionNumber = String.format(res.getString(R.string.list_desc) + " " + androidVersion.getVersionNumber());
            description.setText(versionNumber);

        }

        return view;
    }
}
