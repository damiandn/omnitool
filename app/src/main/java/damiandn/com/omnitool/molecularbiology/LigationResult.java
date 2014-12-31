package damiandn.com.omnitool.molecularbiology;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import damiandn.com.omnitool.R;

/**
 * Created by Angela on 12/21/2014.
 */
public class LigationResult extends Activity {

    TextView tv1to1result, tv1to2result, tv1to4result;
    Double DilutionOneToOne;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setStatusBarColor(0xFF303F9F);

        setContentView(R.layout.ligationresult);

        tv1to1result = (TextView) findViewById(R.id.tv1to1LigationResult);
        tv1to2result = (TextView) findViewById(R.id.tv1to2LigationResult);
        tv1to4result = (TextView) findViewById(R.id.tv1to4LigationResult);

        Bundle Ligationparameters = getIntent().getExtras();


        //test
        DilutionOneToOne = Ligationparameters.getDouble("ligation_dilution");

        tv1to1result.setText(String.format("%.2f", DilutionOneToOne));                  //round all down to 2 decimal places
        tv1to2result.setText(String.format("%.2f", (DilutionOneToOne * 2)));
        tv1to4result.setText(String.format("%.2f", (DilutionOneToOne * 4)));
    }
}