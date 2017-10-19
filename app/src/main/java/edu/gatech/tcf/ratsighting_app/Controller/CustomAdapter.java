package edu.gatech.tcf.ratsighting_app.Controller;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import edu.gatech.tcf.ratsighting_app.Model.RatSighting;
import edu.gatech.tcf.ratsighting_app.Model.SightingListContainer;
import edu.gatech.tcf.ratsighting_app.R;

/**
 * Created by taint on 10/19/2017.
 */

public class CustomAdapter extends ArrayAdapter<RatSighting> {

    public CustomAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CustomAdapter(Context context, int resource, List<RatSighting> items) {
        super(context, resource, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_view_sighting, null);
        TextView text = (TextView) convertView.findViewById(R.id.sightingID);
        text.setText("    Sighting " + SightingListContainer.list.get(position).getKey());
        return convertView;
    }

    public int getCount() {
        return super.getCount();
    }
}

