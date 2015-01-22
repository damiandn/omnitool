package damiandn.com.omnitool.molecularbiology;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.transition.Fade;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import damiandn.com.omnitool.R;

/**
 * Created by Desktop on 12/13/2014.
 */
public class AminoAcidView_new extends Activity {

    ImageView structure;
    TextView Mw, COOH, NH2, sidechain, classification, name, sidechainname;
    String aaTitle, type, aaname;
    Double COOHPKA, NH2PKA, scPKA, mass;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.aminoacidview);

        getWindow().setExitTransition(new Fade());
        getWindow().setEnterTransition(new Fade());
        getWindow().setStatusBarColor(0xFF303F9F);

        setContentView(R.layout.aminoacidview);

        structure = (ImageView) findViewById(R.id.ivAminoAcid);
        Mw = (TextView) findViewById(R.id.tvAminoAcidViewMolarMassResult);
        COOH = (TextView) findViewById(R.id.tvAminoAcidViewPKaCOOHResult);
        NH2 = (TextView) findViewById(R.id.tvAminoAcidViewPKaNH3Result);
        sidechain = (TextView) findViewById(R.id.tvAminoAcidViewPKaSideChainResult);
        classification = (TextView) findViewById(R.id.tvAminoAcidViewClassResult);
        name = (TextView) findViewById(R.id.tvAminoAcidViewName);
        sidechainname = (TextView) findViewById(R.id.tvAminoAcidViewPKASidechain);

        Bundle aminoaciddata = getIntent().getExtras();

        String aathreelettername = aminoaciddata.getString("aminoacidname");  //retrieve the three letter code passed in

        PopulateAminoAcidView(aathreelettername);





       //set these below
        //String aatitle = aminoaciddata.getString("aminoacidtitle");
        //String type = aminoaciddata.getString("class");
        //double COOHPKA = aminoaciddata.getDouble("COOHPKA");
        //double NH2PKA = aminoaciddata.getDouble("NH2PKA");
        //double scPKA = aminoaciddata.getDouble("SidechainPKA");
        //double mass = aminoaciddata.getDouble("mass");

        name.setText(aaTitle);
        classification.setText(type);
        Mw.setText(Double.toString(mass));
        COOH.setText(Double.toString(COOHPKA));
        NH2.setText(Double.toString(NH2PKA));







        if (scPKA != 0) {
            sidechainname.setVisibility(View.VISIBLE);
            sidechain.setVisibility(View.VISIBLE);
            sidechain.setText(Double.toString(scPKA));
        }else {
            //sidechain.setText("N/A");
            sidechain.setVisibility(View.GONE);
            sidechainname.setVisibility(View.GONE);
        }


     String PACKAGE_NAME = getApplicationContext().getPackageName();
     int imgId = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+aaname , null, null);
     structure.setImageBitmap(BitmapFactory.decodeResource(getResources(), imgId));




        //get the height of the screen to scale the image
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Float density = getResources().getDisplayMetrics().density;
        Integer pxHeight = metrics.heightPixels;                        //height of the screen in pixels

        structure.getLayoutParams().height = pxHeight / 3;              //sets the imageview to 1/3 of the screen height

    }



    public void PopulateAminoAcidView(String aminoacid) {               //method to open the amino acid view depending on the string passed in


        switch (aminoacid) {


            case "Ala":

                aaname = "alanine";
                aaTitle = "Alanine (Ala, A)";
                COOHPKA = 2.4;
                NH2PKA = 9.7;
                scPKA = 0.0;
                mass = 89.0;
                type = "polar uncharged";

                break;



            case "Arg":

                aaname = "arginine";
                aaTitle = "Arginine (Arg, R)";
                COOHPKA = 2.2;
                NH2PKA = 9.0;
                scPKA = 12.5;
                mass = 174.0;
                type = "basic";

             break;


            case "Asn":

                aaname = "asparagine";
                aaTitle = "Asparagine (Asn, N)";
                COOHPKA = 2.0;
                NH2PKA = 8.8;
                scPKA = 0.0;
                mass = 132.0;
                type = "polar uncharged";

            break;

            case "Asp":

                aaname = "aspartic_acid";
                aaTitle = "Aspartic Acid (Asp, D)";
                COOHPKA = 2.0;
                NH2PKA = 8.8;
                scPKA = 3.86;
                mass = 133.0;
                type = "acidic";

            break;

            case "Cys":

                aaname = "cysteine";
                aaTitle = "Cysteine (Cys, C)";
                COOHPKA = 1.7;
                NH2PKA = 10.8;
                scPKA = 8.3;
                mass = 121.0;
                type = "polar uncharged";


                break;

            case "Glu":

                aaname = "glutamic_acid";
                aaTitle = "Glutamic Acid (Glu, E)";
                COOHPKA = 2.2;
                NH2PKA = 9.7;
                scPKA = 4.3;
                mass = 147.0;
                type = "acidic";


                break;

            case "Gln":

                aaname = "glutamine";
                aaTitle = "Glutamine (Gln, Q)";
                COOHPKA = 2.2;
                NH2PKA = 9.1;
                scPKA = 0.0;
                mass = 146.0;
                type = "polar uncharged";

                break;

            case "Gly":

                aaname = "glycine";
                aaTitle = "Glycine (Gly, G)";
                COOHPKA = 2.3;
                NH2PKA = 9.6;
                scPKA = 0.0;
                mass = 75.0;
                type = "polar uncharged";


                break;

            case "His":

                aaname = "histidine";
                aaTitle = "Histidine (His, H)";
                COOHPKA = 2.3;
                NH2PKA = 9.6;
                scPKA = 0.0;
                mass = 155.0;
                type = "polar";


                break;

            case "Ile":

                aaname = "isolucine";
                aaTitle = "Isoleucine (Ile, I)";
                COOHPKA = 2.4;
                NH2PKA = 9.7;
                scPKA = 0.0;
                mass = 131.0;
                type = "nonpolar hydrophobic";


                break;

            case "Leu":

                aaname = "leucine";
                aaTitle = "Leucine (Leu, L)";
                COOHPKA = 2.4;
                NH2PKA = 9.6;
                scPKA = 0.0;
                mass = 131.0;
                type = "nonpolar hydrophobic";


                break;

            case "Lys":

                aaname = "lysine";
                aaTitle = "(Lysine (Lys, K)";
                COOHPKA = 2.2;
                NH2PKA = 9.0;
                scPKA = 10.5;
                mass = 146.0;
                type = "basic";

                break;

            case "Met":

                aaname = "methionine";
                aaTitle = "(Methionine (Met, M)";
                COOHPKA = 2.3;
                NH2PKA = 9.2;
                scPKA = 0.0;
                mass = 149.0;
                type = "nonpolar hydrophobic";

                break;

            case "Phe":

                aaname = "phenylalanine";
                aaTitle = "Phenylalanine (Phe, F)";
                COOHPKA = 1.8;
                NH2PKA = 9.1;
                scPKA = 0.0;
                mass = 165.0;
                type = "nonpolar hydrophobic";

                break;

            case "Pro":

                aaname = "proline";
                aaTitle = "Proline (Pro, P)";
                COOHPKA = 1.1;
                NH2PKA = 10.6;
                scPKA = 0.0;
                mass = 115.0;
                type = "nonpolar hydrophobic";

                break;

            case "Ser":

                aaname = "serine";
                aaTitle = "Serine (Ser, S)";
                COOHPKA = 2.2;
                NH2PKA = 9.2;
                scPKA = 0.0;
                mass = 105.0;
                type = "polar uncharged";

                break;

            case "Thr":

                aaname = "threonine";
                aaTitle = "Threonine (Thr, T)";
                COOHPKA = 2.6;
                NH2PKA = 10.4;
                scPKA = 0.0;
                mass = 119.0;
                type = "polar uncharged";

                break;

            case "Trp":

                aaname = "tryptophan";
                aaTitle = "Tryptophan (Trp, W)";
                COOHPKA = 2.4;
                NH2PKA = 9.4;
                scPKA = 0.0;
                mass = 204.0;
                type = "nonpolar hydrophobic";

                break;

            case "Tyr":

                aaname = "tyrosine";
                aaTitle = "Tyrosine (Tyr, Y)";
                COOHPKA = 2.2;
                NH2PKA = 9.1;
                scPKA = 10.1;
                mass = 181.0;
                type = "polar uncharged";

                break;


            case "Val":

                aaname = "valine";
                aaTitle = "Valine (Val, V)";
                COOHPKA = 2.3;
                NH2PKA = 9.6;
                scPKA = 0.0;
                mass = 117.0;
                type = "nonpolar hydrophobic";

                break;


        }


    }
}
