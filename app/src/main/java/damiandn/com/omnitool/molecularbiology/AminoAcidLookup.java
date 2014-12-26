package damiandn.com.omnitool.molecularbiology;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;

import damiandn.com.omnitool.R;

/**
 * Created by Angela on 12/24/2014.
 */
public class AminoAcidLookup extends Activity implements View.OnClickListener{

    ListView lvAminoAcidList;
    ArrayAdapter<String> adapter;
    //String[] aalistitems = new String[]{"Alanine", "arginine", "asparagine", "aspartic acid", "cysteine", "glutamine", "glutamic acid", "glycine", "histidine", "isoleucine", "leucine", "lysine", "methionine", "phenylalanine", "proline", "serine", "threonine", "tryptophan", "tyrosine", "valine"};
    String[] aalistitems = new String[]{};
    String[] aaThreeLetter = new String[]{};

    EditText aaFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setStatusBarColor(0xFF303F9F);
        setContentView(R.layout.aminoacidlookup);


        Resources res = getResources();
        aalistitems = res.getStringArray(R.array.amino_acid_full_names);
        aaThreeLetter = res.getStringArray(R.array.amino_acid_3_letter);

        aaFilter = (EditText) findViewById(R.id.etFilterAminoAcids);

        lvAminoAcidList = (ListView) findViewById(R.id.lvAminoAcidLookup);
        lvAminoAcidList.setTextFilterEnabled(true);


        adapter = new ArrayAdapter<String>(this, R.layout.listitem, R.id.tvListItem, aalistitems);

        lvAminoAcidList.setAdapter(adapter);


        lvAminoAcidList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String stg = lvAminoAcidList.getItemAtPosition(position).toString(); //get the amino acid name from the entry

                int pos = java.util.Arrays.asList(aalistitems).indexOf(stg);         //find the index position in the full array

                String stg3letter = Array.get(aaThreeLetter, pos).toString();       //use that index position to find the three letter code from that array

                Intent i = new Intent(AminoAcidLookup.this, AminoAcidView_new.class);       //pass in the 3 letter code and start the intent
                Bundle aaExtras = new Bundle();
                aaExtras.putString("aminoacidname", stg3letter);
                i.putExtras(aaExtras);
                startActivity(i);
            }


        });


        aaFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AminoAcidLookup.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }








    @Override
    public void onClick(View v) {

    }
}
