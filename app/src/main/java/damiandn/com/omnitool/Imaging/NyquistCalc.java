package damiandn.com.omnitool.Imaging;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import damiandn.com.omnitool.R;




public class NyquistCalc extends Activity implements View.OnClickListener{

    TextView tvRef;
    EditText etRef, etEx, etEm, etNA;
    Double Ref, Ex, Em, NA, nyquistXY, nyquistZ;
    Button nyCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setStatusBarColor(0xFF388E3C);
        setContentView(R.layout.nyquist);

        tvRef = (TextView) findViewById(R.id.tvRefractiveIndex);
        tvRef.setOnClickListener(this);

        etRef = (EditText) findViewById(R.id.etRefractiveIndex);
        etEx = (EditText) findViewById(R.id.etExcitationWavelength);
        etEm = (EditText) findViewById(R.id.etEmissionWavelength);
        etNA = (EditText) findViewById(R.id.etNA);

        nyCalc = (Button) findViewById(R.id.bNyquistCalc);
        nyCalc.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tvRefractiveIndex:

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.refractiveindextoast,
                        (ViewGroup) findViewById(R.id.toast_layout_root));

                Toast toast_hint = new Toast(getApplicationContext());
                toast_hint.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast_hint.setDuration(Toast.LENGTH_LONG);
                toast_hint.setView(layout);
                toast_hint.show();

            break;

            case R.id.bNyquistCalc:

                if (isEmpty(etRef) || isEmpty(etEx) || isEmpty(etEm) || isEmpty(etNA)) {

                    Toast toast_fill = Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT);
                    toast_fill.show();
                }else {

                    Ref = Double.parseDouble(etRef.getText().toString());
                    Ex = Double.parseDouble(etEx.getText().toString());
                    Em = Double.parseDouble(etEm.getText().toString());
                    NA = Double.parseDouble(etNA.getText().toString());

                    Log.v("Ref", Double.toString(Ref));
                    Log.v("Ex", Double.toString(Ex));
                    Log.v("Em", Double.toString(Em));
                    Log.v("NA", Double.toString(NA));


                    if (Ref < NA) {

                        Toast toast_fill = Toast.makeText(this, "Numerical aperture is higher than refractive index", Toast.LENGTH_SHORT);
                        toast_fill.show();

                    } else {

                        double alphainput = (NA / Ref);

                        Log.v("NA / Ref", Double.toString(alphainput));

                        double alpha = (Math.asin(alphainput));


                        Log.v("alpha", Double.toString(alpha));


                        nyquistXY = (Ex / ((8 * Ref) * Math.sin(alpha)));

                        nyquistZ = (Ex / ((4 * Ref) * (1 - Math.cos(alpha))));

                        Log.v("Nyquist XY", nyquistXY.toString());
                        Log.v("Nyquist Z", nyquistZ.toString());

                        Intent i = new Intent(NyquistCalc.this, NyquistResult.class);
                        Bundle nyquistResults = new Bundle();
                        nyquistResults.putDouble("XY", nyquistXY);
                        nyquistResults.putDouble("Z", nyquistZ);
                        i.putExtras(nyquistResults);
                        startActivity(i);
                    }
                }
            break;


        }

    }


    private boolean isEmpty(EditText etText) {                          //returns TRUE if edittext is EMPTY
        return etText.getText().toString().trim().length() == 0;
    }


}
