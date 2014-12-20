package damiandn.com.omnitool.molecularbiology;

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
 * Created by Desktop on 11/23/2014.
 */
public class MolBioMain extends Activity implements View.OnClickListener {


    String[] listitems = new String[]{"PCR", "Ligation", "Codon Lookup", "Amino Acid Lookup"};
    ListView molBioItems;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setExitTransition(new Fade());
        getWindow().setEnterTransition(new Fade());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setStatusBarColor(0xFF303F9F);                          //set status bar color to the dark blue
        setContentView(R.layout.molbiomain);



        molBioItems = (ListView) findViewById(R.id.lvMolBioItems);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listitems);
        //adapter = new ArrayAdapter<String>(this, R.layout.listitem, listitems);

        molBioItems.setAdapter(adapter);

        molBioItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0:
                        Intent pcr = new Intent(MolBioMain.this, PCR.class);
                        startActivity(pcr);
                    break;

                    case 1:
                        Intent ligation = new Intent(MolBioMain.this, Ligation.class);
                        startActivity(ligation);
                    break;

                    case 2:
                        Intent codonlookup = new Intent(MolBioMain.this, CodonLookup.class);
                        startActivity(codonlookup);
                    break;

                    case 3:
                        Intent reversecodonlookup = new Intent(MolBioMain.this, PCR.class);
                        startActivity(reversecodonlookup);
                    break;


                }










            }
        });





    }

    @Override
    public void onClick(View v) {

    }

}
