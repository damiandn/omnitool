package damiandn.com.omnitool.General;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import damiandn.com.omnitool.R;

/**
 * Created by Desktop on 11/26/2014.
 */
public class DiluteSolution extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    EditText etV1, etV2, etC1, etC2;
    Spinner spV1, spV2, spC1, spC2;
    Button bCalculateDilution;

    String result;

    String ResultUnits;
    String concentrationUnits = "μM";
    String volumeUnits = "μL";

    Double volumeToAdd, finalConcentration, v1, c1, v2, c2;

    String[] concentrationArray = new String[] {"M", "mM", "μM"};
    String[] volumeArray = new String[] {"mL", "μL"};

    Boolean calcVol;                    //true if calculating volume, false if calculating concentration

    Integer volumeFactorInitial = 1000;
    Integer  concentrationFactorInitial = 1000;
    Integer volumeFactorFinal = 1000;
    Integer  concentrationFactorFinal = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(0xFF00796B);
        getWindow().setExitTransition(new Fade());
        getWindow().setEnterTransition(new Fade());
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.diluteasolution);

        etV1 = (EditText) findViewById(R.id.etDiluteInitialVolume);
        etV2 = (EditText) findViewById(R.id.etDiluteFinalVolume);
        etC1 = (EditText) findViewById(R.id.etDiluteInitialConcentration);
        etC2 = (EditText) findViewById(R.id.etDiluteFinalConcentration);

        spV1 = (Spinner) findViewById(R.id.spDiluteInitialVolume);
        spV2 = (Spinner) findViewById(R.id.spDiluteFinalVolume);
        spC1 = (Spinner) findViewById(R.id.spDiluteInitialConcentration);
        spC2 = (Spinner) findViewById(R.id.spDiluteFinalConcentration);

        bCalculateDilution = (Button) findViewById(R.id.bDiluteCalculate);

        spV1.setOnItemSelectedListener(this);
        spV2.setOnItemSelectedListener(this);
        spC1.setOnItemSelectedListener(this);
        spC2.setOnItemSelectedListener(this);

        bCalculateDilution.setOnClickListener(this);

        ArrayAdapter<String> initialVolumeAdapater = new ArrayAdapter(this, android.R.layout.simple_spinner_item, volumeArray);
        initialVolumeAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spV1.setAdapter(initialVolumeAdapater);
        spV1.setSelection(1);               //set to uL

        ArrayAdapter<String> finalVolumeAdapater = new ArrayAdapter(this, android.R.layout.simple_spinner_item, volumeArray);
        finalVolumeAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spV2.setAdapter(finalVolumeAdapater);
        spV2.setSelection(1);

        ArrayAdapter<String> initialConcentrationAdapater = new ArrayAdapter(this, android.R.layout.simple_spinner_item, concentrationArray);
        initialConcentrationAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spC1.setAdapter(initialConcentrationAdapater);
        spC1.setSelection(1);               //set to mM

        ArrayAdapter<String> finalConcentrationAdapater = new ArrayAdapter(this, android.R.layout.simple_spinner_item, concentrationArray);
        finalConcentrationAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spC2.setAdapter(finalConcentrationAdapater);
        spC2.setSelection(1);



    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bDiluteCalculate:

                if (isEmpty(etV1) || isEmpty(etC1)) {
                    Toast toast = Toast.makeText(this, "Enter initial volume and concentration", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (isEmpty(etC2) && isEmpty(etV2)) {
                    Toast toast = Toast.makeText(this, "Enter either final volume or concentration", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (!isEmpty(etC2) && !isEmpty(etV2)) {
                    Toast toast = Toast.makeText(this, "Enter only final volume OR final concentration", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    v1 = Double.parseDouble(etV1.getText().toString());
                    c1 = Double.parseDouble(etC1.getText().toString());

                    Double v1s = v1 / volumeFactorInitial;                  //set to mL
                    Double c1s = c1 / concentrationFactorInitial;           //set to M

                if (!isEmpty(etV2)) {                                       //if we have a volume value, and want to calculate concentration
                        calcVol = false;
                        v2 = Double.parseDouble(etV2.getText().toString());
                        Double v2s = v2 / volumeFactorFinal;                    //set to mL

                        finalConcentration = (v1s * c1s) / v2s;
                        finalConcentration = finalConcentration * concentrationFactorFinal;

                        if (finalConcentration >=0) {
                            result = Double.toString(finalConcentration);


                            String line1 = "A final volume of " + Double.toString(v2)+ volumeUnits + " will equal";
                            String line2 = String.format(result + concentrationUnits);

                            Intent i = new Intent(DiluteSolution.this, MolarityResult.class);
                            Bundle data = new Bundle();
                            data.putString("line1", line1);
                            data.putString("line2", line2);
                           // data.putString("units", ResultUnits);
                            i.putExtras(data);
                            startActivity(i);

                        }else {
                            Toast toast = Toast.makeText(this, "Negative values not allowed", Toast.LENGTH_SHORT);
                            toast.show();
                        }


                    } else if (!isEmpty(etC2)) {                                  //if we have a concentration value, and want to calculate volume

                        c2 = Double.parseDouble(etC2.getText().toString());
                        Log.v("c2", Double.toString(c2));
                        Double c2s = c2 / concentrationFactorFinal;

                        volumeToAdd = ((v1s * c1s) / c2s) - v1s;
                        volumeToAdd = volumeToAdd * volumeFactorFinal;

                        if (volumeToAdd >=0) {
                            result = String.format("%.2f", volumeToAdd);

                            String line1 = "For a final concentration of " + Double.toString(c2)+ concentrationUnits + ", add";
                            String line2 = String.format(result + volumeUnits);

                            Intent i = new Intent(DiluteSolution.this, MolarityResult.class);
                            Bundle data = new Bundle();
                            data.putString("line1", line1);
                            data.putString("line2", line2);
                            //data.putString("units", ResultUnits);
                            i.putExtras(data);
                            startActivity(i);



                        }else {
                            Toast toast = Toast.makeText(this, "Negative values not allowed", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    }



                }
                break;
                }
        }






    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(parent.getId()){

            case R.id.spDiluteInitialConcentration:
                switch (position) {
                    case 0:
                        concentrationFactorInitial = 1;
                        break;
                    case 1:
                        concentrationFactorInitial = 1000;
                        break;
                    case 2:
                        concentrationFactorInitial = 1000000;
                        break;
                }
                break;


            case R.id.spDiluteFinalConcentration:
                switch (position) {
                    case 0:
                        concentrationFactorFinal = 1;
                        concentrationUnits = "M";
                        break;
                    case 1:
                        concentrationFactorFinal = 1000;
                        concentrationUnits = "mM";
                        break;
                    case 2:
                        concentrationUnits = "uM";
                        concentrationFactorFinal = 1000000;
                        break;
                }
                break;

            case R.id.spDiluteInitialVolume:
                switch (position) {
                    case 0:
                        volumeFactorInitial = 1;
                        break;
                    case 1:
                        volumeFactorInitial = 1000;
                        break;
                }

            case R.id.spDiluteFinalVolume:
                switch (position) {
                    case 0:
                        volumeFactorFinal = 1;
                        break;
                    case 1:
                        volumeFactorFinal = 1000;
                        break;
                }

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private boolean isEmpty(EditText etText) {                          //returns TRUE if edittext is EMPTY
        return etText.getText().toString().trim().length() == 0;
    }

}
