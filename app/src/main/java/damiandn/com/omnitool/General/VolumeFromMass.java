package damiandn.com.omnitool.General;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
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
public class VolumeFromMass extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    Button bCalculate;
    EditText etMolarMass, etMass, etConcentration;
    Double VolumeToAdd, MolarMass, Mass, Concentration, moles;
    String ResultUnits;
    String ConcentrationUnits;
    Spinner spMass, spConcentration;

    Integer massFactor = 1;
    Integer  ConcentrationFactor = 1;



    String[] concentrationArray = new String[] {"M", "mM", "μM"};
    String[] massArray = new String[] {"g", "mg", "μg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setExitTransition(new Fade());
        getWindow().setEnterTransition(new Fade());
        getWindow().setStatusBarColor(0xFF00796B);


        setContentView(R.layout.volumefrommass);

        bCalculate = (Button) findViewById(R.id.bCalculateVolumeFromMass);
        bCalculate.setOnClickListener(this);
        spMass = (Spinner) findViewById(R.id.spVolumeFromMass_Mass);
        spConcentration = (Spinner) findViewById(R.id.spVolumeFromMass_Concentration);

        spMass.setOnItemSelectedListener(this);
        spConcentration.setOnItemSelectedListener(this);


        etMolarMass = (EditText) findViewById(R.id.etVolumeFromMass_MolarMass);
        etMass = (EditText) findViewById(R.id.etVolumeFromMass_Mass);
        etConcentration = (EditText) findViewById(R.id.etVolumeFromMass_Concentration);


        ArrayAdapter<String> concentrationAdapater = new ArrayAdapter(this, android.R.layout.simple_spinner_item, concentrationArray);
        concentrationAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spConcentration.setAdapter(concentrationAdapater);
        spConcentration.setSelection(1);        //set deault to mM
        ConcentrationUnits = "mM";


        ArrayAdapter<String> massAdapater = new ArrayAdapter(this, android.R.layout.simple_spinner_item, massArray);
        massAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMass.setAdapter(massAdapater);
        spMass.setSelection(1);                //set default to mg
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bCalculateVolumeFromMass:

             if (isEmpty(etMass) || isEmpty(etMolarMass) || isEmpty(etConcentration)) {

                Toast toast = Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT);
                toast.show();

            }else{

                MolarMass = Double.parseDouble(etMolarMass.getText().toString());
                Mass = Double.parseDouble(etMass.getText().toString()) / massFactor;                //convert to grams
                Concentration = Double.parseDouble(etConcentration.getText().toString()) / ConcentrationFactor;            //convert to moles/L

                moles = (Mass / MolarMass);

                VolumeToAdd = (moles / Concentration) * 1000 * 1000;                                 //report in uL

                if (VolumeToAdd >= 1000) {
                    ResultUnits = "mL";
                    VolumeToAdd = VolumeToAdd / 1000;

                } else {
                    ResultUnits = "μL";
                }
            }

                String line1 = "For a final concentration of " + Double.toString(Concentration * ConcentrationFactor)+ ConcentrationUnits + ", add";
                String line2 = String.format("%.2f", VolumeToAdd);

                Intent i = new Intent(VolumeFromMass.this, MolarityResult.class);
                Bundle data = new Bundle();
                data.putString("line1", line1);
                data.putString("line2", line2);
                data.putString("units", ResultUnits);
                i.putExtras(data);
                startActivity(i);

                break;
        }


    }



    private boolean isEmpty(EditText etText) {                          //returns TRUE if edittext is EMPTY
        return etText.getText().toString().trim().length() == 0;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(parent.getId()){

                case R.id.spVolumeFromMass_Concentration:

                    switch (position) {


                        case 0:
                            ConcentrationFactor = 1;
                            ConcentrationUnits = "M";
                        break;

                        case 1:
                            ConcentrationFactor = 1000;
                            ConcentrationUnits = "mM";

                        break;

                        case 2:
                            ConcentrationUnits = "uM";
                            ConcentrationFactor = 1000000;
                        break;
                    }

                break;

                 case R.id.spVolumeFromMass_Mass:
                     switch (position) {
                        case 0:
                            massFactor = 1;
                        break;

                        case 1:
                            massFactor = 1000;
                        break;

                        case 2:
                            massFactor = 1000000;
                        break;
                }

        }




        }




    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
