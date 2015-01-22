package damiandn.com.omnitool.Imaging;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import damiandn.com.omnitool.R;

public class NyquistResult extends Activity

{

    TextView nyquistXY, nyquistZ;
    Double XY, Z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setStatusBarColor(0xFF388E3C);
        setContentView(R.layout.nyquistresult);


        nyquistXY = (TextView) findViewById(R.id.tvNyquistResultXY);
        nyquistZ = (TextView) findViewById(R.id.tvNyquistResultZ);

        Bundle nyquistResults = getIntent().getExtras();
        XY = nyquistResults.getDouble("XY");
        Z = nyquistResults.getDouble("Z");

        Log.v("passed XY", Double.toString(XY));

        Log.v("passed Z", Double.toString(Z));

        String sXY = String.format("%.2f", XY);
        String sZ = String.format("%.2f", Z);


        nyquistXY.setText("XY = " + sXY + "nm");
        nyquistZ.setText("Z = " + sZ + "nm");



    }



}
