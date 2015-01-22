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
import android.widget.TextView;
import android.widget.Toast;

import damiandn.com.omnitool.R;

/**
 * Created by Desktop on 11/26/2014.
 */
public class VolumeFromMoles extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener{



    Button bCalculate;
    TextView result;
    EditText moles, concentration;
    Spinner spMoles, spConcentration;
    Integer  concentrationFactor = 1;
    Integer moleFactor = 1;

    String ResultUnits;
    String ConcentrationUnits;

    Double VolumeToAdd, numOfMoles, concentrationTarget;

    String[] concentrationArray = new String[] {"M", "mM", "μM"};
    String[] moleArray = new String[] {"M", "mM", "μM"};

    String resultUnits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setExitTransition(new Fade());
        getWindow().setEnterTransition(new Fade());
        getWindow().setStatusBarColor(0xFF00796B);

        setContentView(R.layout.volumefrommoles);

        bCalculate = (Button) findViewById(R.id.bCalculateVolumeFromMoles);
        bCalculate.setOnClickListener(this);

        moles =(EditText) findViewById(R.id.etVolumeFromMoles_Moles);
        concentration =(EditText) findViewById(R.id.etVolumeFromMoles_Concentration);

        spMoles = (Spinner) findViewById(R.id.spVolumeFromMoles_Moles);
        spConcentration = (Spinner) findViewById(R.id.spVolumeFromMoles_Concentration);

        ArrayAdapter<String> concentrationAdapater = new ArrayAdapter(this, android.R.layout.simple_spinner_item, concentrationArray);
        concentrationAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spConcentration.setAdapter(concentrationAdapater);
        spConcentration.setSelection(1);        //set deault to mM
        ConcentrationUnits = "mM";


        ArrayAdapter<String> moleAdapater = new ArrayAdapter(this, android.R.layout.simple_spinner_item, moleArray);
        moleAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMoles.setAdapter(moleAdapater);
        spMoles.setSelection(1);                //set default to mg



    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.bCalculateVolumeFromMoles:



                if (isEmpty(moles) || isEmpty(concentration)) {

                    Toast toast = Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT);
                    toast.show();

                }else{

                    numOfMoles = Double.parseDouble(moles.getText().toString()) / moleFactor;              //convert to moles
                    concentrationTarget = Double.parseDouble(concentration.getText().toString()) / concentrationFactor;            //convert to moles/L


                    VolumeToAdd = (numOfMoles / concentrationTarget) * 1000 * 1000;                                 //report in uL



                    if (VolumeToAdd >= 1000) {
                        resultUnits = " mL";
                        VolumeToAdd = VolumeToAdd / 1000;


                    } else {
                        resultUnits = " μL";
                    }
                }



                String line1 = "For a final concentration of " + Double.toString(concentrationTarget * concentrationFactor)+ ConcentrationUnits + ", add";
                String line2 = String.format("%.2f", VolumeToAdd);


                Intent i = new Intent(VolumeFromMoles.this, MolarityResult.class);

                Bundle data = new Bundle();
                data.putString("line1", line1);
                data.putString("line2", line2);
                data.putString("units", resultUnits);
                i.putExtras(data);
                startActivity(i);


            break;

        }




    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(parent.getId()){

            case R.id.spVolumeFromMoles_Concentration:

                switch (position) {


                    case 0:

                        concentrationFactor = 1;
                        Log.v("flag", "concentration changed");
                        break;

                    case 1:
                        concentrationFactor = 1000;


                        break;

                    case 2:
                        concentrationFactor = 1000000;



                        break;
                }

                break;

            case R.id.spVolumeFromMoles_Moles:
                switch (position) {
                    case 0:
                        moleFactor = 1;
                        Log.v("flag", "moles changed");

                        break;

                    case 1:
                        moleFactor  = 1000;
                        break;

                    case 2:
                        moleFactor  = 1000000;
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
