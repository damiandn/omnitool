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
public class AminoAcidView extends Activity {

    ImageView structure;
    TextView Mw, COOH, NH2, sidechain, classification, name, sidechainname;


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

        String aaname = aminoaciddata.getString("aminoacidname");
        String aatitle = aminoaciddata.getString("aminoacidtitle");
        String type = aminoaciddata.getString("class");
        double COOHPKA = aminoaciddata.getDouble("COOHPKA");
        double NH2PKA = aminoaciddata.getDouble("NH2PKA");
        double scPKA = aminoaciddata.getDouble("SidechainPKA");
        double mass = aminoaciddata.getDouble("mass");

        name.setText(aatitle);
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
}
