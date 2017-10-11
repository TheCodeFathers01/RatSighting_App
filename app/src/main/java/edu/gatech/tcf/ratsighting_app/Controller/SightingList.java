package edu.gatech.tcf.ratsighting_app.Controller;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.tcf.ratsighting_app.Model.RatSighting;
import edu.gatech.tcf.ratsighting_app.R;

public class SightingList extends AppCompatActivity {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference("server/saving-data/sightingData");
    ListView listView;
    ArrayList<RatSighting> list;
    int listPlace = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Poop", "Got Hereo");
        setContentView(R.layout.activity_sighting_list);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listPlace = position;
                launchLogin();
            }
        });
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                initList(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initList(DataSnapshot dataSnapshot) {
        Iterable<DataSnapshot> ds = dataSnapshot.getChildren();
        list = new ArrayList<RatSighting>();
        RatSighting newSighting;
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        Log.d("Poop", "Got here");
        int counter = 0;
        for (DataSnapshot sighting : ds) {
            newSighting = new RatSighting();
            newSighting.setAddress(sighting.getValue(RatSighting.class).getAddress());
            newSighting.setCity(sighting.getValue(RatSighting.class).getCity());
            newSighting.setCoordinates(sighting.getValue(RatSighting.class).getCoordinates());
            newSighting.setmBorough(sighting.getValue(RatSighting.class).getBorough());
            newSighting.setKey(sighting.getValue(RatSighting.class).getKey());
            newSighting.setmDate(sighting.getValue(RatSighting.class).getDate());
            newSighting.setZipCode(sighting.getValue(RatSighting.class).getZipCode());
            newSighting.setmLocationType(sighting.getValue(RatSighting.class).getLocationType());
            list.add(newSighting);
            Log.d("Poop", newSighting + "");
            adapter.notifyDataSetChanged();
            if (counter > 50) {
                break;
            }
            counter++;
        }
    }

    private void launchLogin() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("Sighting", (Serializable) list.get(listPlace));
        Intent goInfo = new Intent(this, SightingInfo.class);
        goInfo.putExtras(bundle);
        startActivity(goInfo);
    }
}
