package damiandn.com.omnitool.molecularbiology;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import damiandn.com.omnitool.R;

/**
 * Created by Desktop on 12/12/2014.
 */
public class CodonLookup extends Activity implements View.OnClickListener {

    Button A, T, C, G, del;
    ListView codonList;
    TextView currentcodon;
    ArrayAdapter<String> adapter;
    String[] codonlistitems = new String[]{"TTT   F   Phe", "TCT   S   Ser", "TAT   Y   Tyr", "TGT   C   Cys",
            "CTT   L   Leu", "CCT   P   Pro", "CAT   H   His", "CGT   R   Arg",
            "ATT   I   Ile", "ACT   T   Thr", "AAT   N   Asn", "AGT   S   Ser",
            "GTT   V   Val", "GCT   A   Ala", "GAT   D   Asp", "GGT   G   Gly",
            "TTC   F   Phe", "TCC   S   Ser", "TAC   Y   Tyr", "TGC   C   Cys",
            "CTC   L   Leu", "CCC   P   Pro", "CAC   H   His", "CGC   R   Arg",
            "ATC   I   Ile", "ACC   T   Thr", "AAC   N   Asn", "AGC   S   Ser",
            "GTC   V   Val", "GCC   A   Ala", "GAC   D   Asp", "GGC   G   Gly",
            "TTA   L   Leu", "TCA   S   Ser", "TAA   *   Ter", "TGA   *   Ter",
            "CTA   L   Leu", "CCA   P   Pro", "CAA   Q   Gln", "CGA   R   Arg",
            "ATA   I   Ile", "ACA   T   Thr", "AAA   K   Lys", "AGA   R   Arg",
            "GTA   V   Val", "GCA   A   Ala", "GAA   E   Glu", "GGA   G   Gly",
            "TTG   L   Leu", "TCG   S   Ser", "TAG   *   Ter", "TGG   W   Trp",
            "CTG   L   Leu", "CCG   P   Pro", "CAG   Q   Gln", "CGG   R   Arg",
            "ATG   M   Met", "ACG   T   Thr", "AAG   K   Lys", "AGG   R   Arg",
            "GTG   V   Val", "GCG   A   Ala", "GAG   E   Glu", "GGG   G   Gly",};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setExitTransition(new Fade());
        getWindow().setEnterTransition(new Fade());
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setStatusBarColor(0xFF303F9F);
        setContentView(R.layout.codonlookup);

        A = (Button) findViewById(R.id.codonadenine);
        T = (Button) findViewById(R.id.codonthymine);
        C = (Button) findViewById(R.id.codoncytosine);
        G = (Button) findViewById(R.id.codonguanine);
        del = (Button) findViewById(R.id.codonclear);

        A.setOnClickListener(this);
        T.setOnClickListener(this);
        C.setOnClickListener(this);
        G.setOnClickListener(this);
        del.setOnClickListener(this);


        codonList = (ListView) findViewById(R.id.codonListView);
        codonList.setTextFilterEnabled(true);
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, codonlistitems);

        adapter = new ArrayAdapter<String>(this, R.layout.listitem, R.id.tvListItem, codonlistitems);

        codonList.setAdapter(adapter);


        codonList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                  String stg = codonList.getItemAtPosition(position).toString();
                String aminoacidcode = stg.substring(stg.length() - 3);

                Intent i = new Intent(CodonLookup.this, AminoAcidView_new.class);       //pass in the 3 letter code and start the intent
                Bundle aaExtras = new Bundle();
                aaExtras.putString("aminoacidname", aminoacidcode);
                i.putExtras(aaExtras);
                startActivity(i);

            }


            });


        currentcodon = (TextView) findViewById(R.id.tvcodoncurrent);

        currentcodon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                 }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CodonLookup.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });






    }




    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.codonadenine:
                if (currentcodon.getText().toString().trim().length() < 3) {

                    String stCurrentCodon = currentcodon.getText().toString();

                    currentcodon.setText(stCurrentCodon + "A");
                }

            break;

            case R.id.codonthymine:

                if (currentcodon.getText().toString().trim().length() < 3) {

                    String stCurrentCodon = currentcodon.getText().toString();

                    currentcodon.setText(stCurrentCodon + "T");
                }
            break;

            case R.id.codoncytosine:

                if (currentcodon.getText().toString().trim().length() < 3) {

                    String stCurrentCodon = currentcodon.getText().toString();

                    currentcodon.setText(stCurrentCodon + "C");
                }
            break;

            case R.id.codonguanine:

                if (currentcodon.getText().toString().trim().length() < 3) {

                    String stCurrentCodon = currentcodon.getText().toString();

                    currentcodon.setText(stCurrentCodon + "G");
                }
            break;

            case R.id.codonclear:

                if (currentcodon.getText().toString().trim().length() > 0) {

                    String stCurrentCodon = currentcodon.getText().toString();

                    String newStCurrentCodon = stCurrentCodon.substring(0, currentcodon.length()-1);

                    currentcodon.setText(newStCurrentCodon);
                    }


                    break;

        }


    }


    public void OpenAminoAcidView(String aminoacid) {               //method to open the amino acid view depending on the string passed in

        String aminoacidcode = aminoacid.substring(aminoacid.length() - 3);
        Log.v("amino acid", aminoacid);
        Log.v("amino acid short", aminoacidcode);

        //all this data should really be in an array that we reference, encoded in the resources.

        switch (aminoacidcode) {


            case "Ala":

                Intent openala = new Intent(getApplicationContext(), AminoAcidView.class);
                Bundle aladata = new Bundle();
                aladata.putString("aminoacidname", "alanine");
                aladata.putString("aminoacidtitle", "Alanine (Ala, A)");
                aladata.putDouble("COOHPKA", 2.4);
                aladata.putDouble("NH2PKA", 9.7);
                aladata.putDouble("SidechainPKA", 0);
                aladata.putDouble("mass", 89);
                aladata.putString("class", "polar uncharged");
                openala.putExtras(aladata);
                startActivity(openala);
            break;

            case "Arg":

                Intent openarg = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle argdata = new Bundle();
                argdata.putString("aminoacidname", "arginine");
                argdata.putString("aminoacidtitle", "Arginine (Arg, R)");
                argdata.putDouble("COOHPKA", 2.2);
                argdata.putDouble("NH2PKA", 9.0);
                argdata.putDouble("SidechainPKA", 12.5);
                argdata.putDouble("mass", 174);
                argdata.putString("class", "basic");
                openarg.putExtras(argdata);
                startActivity(openarg);
                break;


            case "Asn":

                Intent openasn = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle asndata = new Bundle();
                asndata.putString("aminoacidname", "asparagine");
                asndata.putString("aminoacidtitle", "Asparagine (Asn, N)");
                asndata.putDouble("COOHPKA", 2.0);
                asndata.putDouble("NH2PKA", 8.8);
                asndata.putDouble("SidechainPKA", 0);
                asndata.putDouble("mass", 132);
                asndata.putString("class", "polar uncharged");
                openasn.putExtras(asndata);
                startActivity(openasn);
                break;

            case "Asp":

                Intent openasp = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle aspdata = new Bundle();
                aspdata.putString("aminoacidname", "aspartic_acid");
                aspdata.putString("aminoacidtitle", "Aspartic Acid (Asp, D)");
                aspdata.putDouble("COOHPKA", 2.0);
                aspdata.putDouble("NH2PKA", 8.8);
                aspdata.putDouble("SidechainPKA", 0);
                aspdata.putDouble("mass", 133);
                aspdata.putString("class", "acidic");
                openasp.putExtras(aspdata);
                startActivity(openasp);
                break;

            case "Cys":

                Intent opencys = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle cysdata = new Bundle();
                cysdata.putString("aminoacidname", "cysteine");
                cysdata.putString("aminoacidtitle", "Cysteine (Cys, C)");
                cysdata.putDouble("COOHPKA", 1.7);
                cysdata.putDouble("NH2PKA", 10.8);
                cysdata.putDouble("SidechainPKA", 8.3);
                cysdata.putDouble("mass", 121);
                cysdata.putString("class", "polar uncharged");
                opencys.putExtras(cysdata);
                startActivity(opencys);
                break;

            case "Glu":

                Intent openglu = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle gludata = new Bundle();
                gludata.putString("aminoacidname", "glutamic_acid");
                gludata.putString("aminoacidtitle", "Glutamic Acid (Glu, E)");
                gludata.putDouble("COOHPKA", 2.2);
                gludata.putDouble("NH2PKA", 9.7);
                gludata.putDouble("SidechainPKA", 4.3);
                gludata.putDouble("mass", 147);
                gludata.putString("class", "acidic");
                openglu.putExtras(gludata);
                startActivity(openglu);
                break;

            case "Gln":

                Intent opengln = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle glndata = new Bundle();
                glndata.putString("aminoacidname", "glutamine");
                glndata.putString("aminoacidtitle", "Glutamine (Gln, Q)");
                glndata.putDouble("COOHPKA", 2.2);
                glndata.putDouble("NH2PKA", 9.1);
                glndata.putDouble("SidechainPKA", 0);
                glndata.putDouble("mass", 146);
                glndata.putString("class", "polar uncharged");
                opengln.putExtras(glndata);
                startActivity(opengln);
                break;

            case "Gly":

                Intent opengly = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle glydata = new Bundle();
                glydata.putString("aminoacidname", "glycine");
                glydata.putString("aminoacidtitle", "Glycine (Gly, G)");
                glydata.putDouble("COOHPKA", 2.3);
                glydata.putDouble("NH2PKA", 9.6);
                glydata.putDouble("SidechainPKA", 0);
                glydata.putDouble("mass", 75);
                glydata.putString("class", "polar uncharged");
                opengly.putExtras(glydata);
                startActivity(opengly);
                break;

            case "His":

                Intent openhis = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle hisdata = new Bundle();
                hisdata.putString("aminoacidname", "histidine");
                hisdata.putString("aminoacidtitle", "Histidine (His, H)");
                hisdata.putDouble("COOHPKA", 2.3);
                hisdata.putDouble("NH2PKA", 9.6);
                hisdata.putDouble("SidechainPKA", 0);
                hisdata.putDouble("mass", 155);
                hisdata.putString("class", "polar");
                openhis.putExtras(hisdata);
                startActivity(openhis);
                break;

            case "Ile":

                Intent openile = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle iledata = new Bundle();
                iledata.putString("aminoacidname", "isoleucine");
                iledata.putString("aminoacidtitle", "Isoleucine (Ile, I)");
                iledata.putDouble("COOHPKA", 2.4);
                iledata.putDouble("NH2PKA", 9.7);
                iledata.putDouble("SidechainPKA", 0);
                iledata.putDouble("mass", 131);
                iledata.putString("class", "nonpolar hydrophobic");
                openile.putExtras(iledata);
                startActivity(openile);
                break;

            case "Leu":

                Intent openleu = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle leudata = new Bundle();
                leudata.putString("aminoacidname", "leucine");
                leudata.putString("aminoacidtitle", "Leucine (Leu, L)");
                leudata.putDouble("COOHPKA", 2.4);
                leudata.putDouble("NH2PKA", 9.6);
                leudata.putDouble("SidechainPKA", 0);
                leudata.putDouble("mass", 131);
                leudata.putString("class", "nonpolar hydrophobic");
                openleu.putExtras(leudata);
                startActivity(openleu);
                break;

            case "Lys":

                Intent openlys = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle lysdata = new Bundle();
                lysdata.putString("aminoacidname", "lysine");
                lysdata.putString("aminoacidtitle", "Lysine (Lys, K)");
                lysdata.putDouble("COOHPKA", 2.2);
                lysdata.putDouble("NH2PKA", 9.0);
                lysdata.putDouble("SidechainPKA", 10.5);
                lysdata.putDouble("mass", 146);
                lysdata.putString("class", "basic");
                openlys.putExtras(lysdata);
                startActivity(openlys);
                break;

            case "Met":

                Intent openmet = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle metdata = new Bundle();
                metdata.putString("aminoacidname", "methionine");
                metdata.putString("aminoacidtitle", "Methionine (Met, M)");
                metdata.putDouble("COOHPKA", 2.3);
                metdata.putDouble("NH2PKA", 9.2);
                metdata.putDouble("SidechainPKA", 0);
                metdata.putDouble("mass", 149);
                metdata.putString("class", "nonpolar hydrophobic");
                openmet.putExtras(metdata);
                startActivity(openmet);
                break;

            case "Phe":







                Intent openphe = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle phedata = new Bundle();
                phedata.putString("aminoacidname", "phenylalanine");
                phedata.putString("aminoacidtitle", "Phenylalanine (Phe, F)");
                phedata.putDouble("COOHPKA", 1.8);
                phedata.putDouble("NH2PKA", 9.1);
                phedata.putDouble("SidechainPKA", 0);
                phedata.putDouble("mass", 165);
                phedata.putString("class", "nonpolar hydrophobic");
                openphe.putExtras(phedata);
                startActivity(openphe);
                break;

            case "Pro":

                Intent openpro = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle prodata = new Bundle();
                prodata.putString("aminoacidname", "proline");
                prodata.putString("aminoacidtitle", "Proline (Pro, P)");
                prodata.putDouble("COOHPKA", 1.1);
                prodata.putDouble("NH2PKA", 10.6);
                prodata.putDouble("SidechainPKA", 0);
                prodata.putDouble("mass", 115);
                prodata.putString("class", "nonpolar hydrophobic");
                openpro.putExtras(prodata);
                startActivity(openpro);
                break;

            case "Ser":

                Intent openser = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle serdata = new Bundle();
                serdata.putString("aminoacidname", "serine");
                serdata.putString("aminoacidtitle", "Serine (Ser, S)");
                serdata.putDouble("COOHPKA", 2.2);
                serdata.putDouble("NH2PKA", 9.2);
                serdata.putDouble("SidechainPKA", 13);
                serdata.putDouble("mass", 105);
                serdata.putString("class", "polar uncharged");
                openser.putExtras(serdata);
                startActivity(openser);
                break;

            case "Thr":

                Intent openthr = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle thrdata = new Bundle();
                thrdata.putString("aminoacidname", "threonine");
                thrdata.putString("aminoacidtitle", "Threonine (Thr, T)");
                thrdata.putDouble("COOHPKA", 2.6);
                thrdata.putDouble("NH2PKA", 10.4);
                thrdata.putDouble("SidechainPKA", 13);
                thrdata.putDouble("mass", 119);
                thrdata.putString("class", "polar uncharged");
                openthr.putExtras(thrdata);
                startActivity(openthr);
                break;

            case "Trp":

                Intent opentrp = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle trpdata = new Bundle();
                trpdata.putString("aminoacidname", "tryptophan");
                trpdata.putString("aminoacidtitle", "Tryptophan (Trp, W)");
                trpdata.putDouble("COOHPKA", 2.4);
                trpdata.putDouble("NH2PKA", 9.4);
                trpdata.putDouble("SidechainPKA", 0);
                trpdata.putDouble("mass", 204);
                trpdata.putString("class", "nonpolar hydrophobic");
                opentrp.putExtras(trpdata);
                startActivity(opentrp);
                break;

            case "Tyr":

                Intent opentyr = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle tyrdata = new Bundle();
                tyrdata.putString("aminoacidname", "tyrosine");
                tyrdata.putString("aminoacidtitle", "Tyrosine (Tyr, Y)");
                tyrdata.putDouble("COOHPKA", 2.2);
                tyrdata.putDouble("NH2PKA", 9.1);
                tyrdata.putDouble("SidechainPKA", 10.1);
                tyrdata.putDouble("mass", 181);
                tyrdata.putString("class", "polar uncharged");
                opentyr.putExtras(tyrdata);
                startActivity(opentyr);
                break;


            case "Val":

                Intent openval = new Intent(CodonLookup.this, AminoAcidView.class);
                Bundle valdata = new Bundle();
                valdata.putString("aminoacidname", "valine");
                valdata.putString("aminoacidtitle", "Valine (Val, V)");
                valdata.putDouble("COOHPKA", 2.3);
                valdata.putDouble("NH2PKA", 9.6);
                valdata.putDouble("SidechainPKA", 0);
                valdata.putDouble("mass", 117);
                valdata.putString("class", "nonpolar hydrophobic");
                openval.putExtras(valdata);
                startActivity(openval);
                break;
        }


    }

}
