package damiandn.com.omnitool.General;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import damiandn.com.omnitool.R;

/**
 * Created by Desktop on 11/26/2014.
 */
public class GeneralMenu extends Activity implements View.OnClickListener{

    String[] generalMenuItems = new String[]{"Molarity Calculator", "Wash Timer"};
    ListView generalItemsList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setExitTransition(new Fade());
        getWindow().setEnterTransition(new Fade());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.generalmenu);

        generalItemsList = (ListView) findViewById(R.id.lvGeneralMenu);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, generalMenuItems);

        generalItemsList.setAdapter(adapter);

        generalItemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0:

                        Intent i = new Intent(GeneralMenu.this, MolarityMenu.class);
                        startActivity(i);

                    break;

                    case 1:

                        Intent j = new Intent(GeneralMenu.this, SetupWashTimers.class);
                        startActivity(j);

                    break;


                }


            }

        });

    }




    @Override
    public void onClick(View v) {

    }
}
