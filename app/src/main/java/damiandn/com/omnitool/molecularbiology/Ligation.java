package damiandn.com.omnitool.molecularbiology;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import damiandn.com.omnitool.R;

/**
 * Created by Desktop on 11/25/2014.
 */
public class Ligation extends Activity implements View.OnClickListener {

    Button bCalculate;
    EditText etVectorConcentration, etInsertConcentration, etVectorSize, etInsertSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setStatusBarColor(0xFF00796B);
        getWindow().setStatusBarColor(0xFF303F9F);

        setContentView(R.layout.ligation);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);  //keeps the screen on

        bCalculate = (Button) findViewById(R.id.bLigationCalculate);

        etVectorConcentration = (EditText) findViewById(R.id.etVectorConcentration);
        etInsertConcentration = (EditText) findViewById(R.id.etInsertConcentration);
        etVectorSize = (EditText) findViewById(R.id.etVectorSize);
        etInsertSize = (EditText) findViewById(R.id.etInsertSize);

        bCalculate.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bLigationCalculate:


                if (isEmpty(etVectorConcentration) || isEmpty(etInsertConcentration) || isEmpty(etVectorSize) || isEmpty(etInsertSize)) {

                    Toast toast = Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT);
                    toast.show();
                }else {

                    double VectorConcentration = Double.parseDouble(etVectorConcentration.getText().toString());
                    double InsertConcentration = Double.parseDouble(etInsertConcentration.getText().toString());
                    double VectorSize = Double.parseDouble(etVectorSize.getText().toString());
                    double InsertSize = Double.parseDouble(etInsertSize.getText().toString());

                    double DilutionOneToOne = ((VectorConcentration * (InsertSize)) / (VectorSize)) / InsertConcentration;


                    Bundle Ligationparameters = new Bundle();
                    Ligationparameters.putDouble("ligation_dilution", DilutionOneToOne);

                    Intent i = new Intent(Ligation.this, Ligation_Result.class);
                    i.putExtras(Ligationparameters);
                    startActivity(i);

                }
            break;

        }

    }

    private boolean isEmpty(EditText etText) {                          //returns TRUE if edittext is EMPTY
        return etText.getText().toString().trim().length() == 0;
    }


    public static void hide_keyboard(Activity activity) {               //Method to hide the soft keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if(view == null) {
            view = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
