package damiandn.com.omnitool.General;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import damiandn.com.omnitool.R;

/**
 * Created by Desktop on 11/26/2014.
 */
public class MolarityMenu extends Activity implements View.OnClickListener {

    ListView molarityListView;
    String[] molarityItemsArray = new String[]{"Volume from mass and concentration", "Volume from molar amount and concentration", "concentration from mass and volume", "dilute a solution"};
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setStatusBarColor(0xFF00796B);

        setContentView(R.layout.molaritymenu);

        molarityListView = (ListView) findViewById(R.id.molarityListView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, molarityItemsArray);
        molarityListView.setAdapter(adapter);

        molarityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            switch (position) {

                case 0:

                    Intent i = new Intent(MolarityMenu.this, VolumeFromMass.class);
                    startActivity(i);

                break;

                case 1:

                    Intent a = new Intent(MolarityMenu.this, VolumeFromMoles.class);
                    startActivity(a);

                    break;

                case 2:

                    Intent j = new Intent(MolarityMenu.this, ConcentrationFromMass.class);
                    startActivity(j);

                    break;

                case 3:

                    Intent k = new Intent(MolarityMenu.this, DiluteSolution.class);
                    startActivity(k);

                    break;
            }



        };
    });

    }


    @Override
    public void onClick(View v) {

    }
}
