package damiandn.com.omnitool.molecularbiology;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
    TextView tv1to1result, tv1to2result, tv1to4result, tv1to8result;
    EditText etVectorConcentration, etInsertConcentration, etVectorSize, etInsertSize;
    RelativeLayout resultRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ligation);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);  //keeps the screen on

        bCalculate = (Button) findViewById(R.id.bLigationCalculate);
        tv1to1result = (TextView) findViewById(R.id.tv1to1result);
        tv1to2result = (TextView) findViewById(R.id.tv1to2result);
        tv1to4result = (TextView) findViewById(R.id.tv1to4result);
        tv1to8result = (TextView) findViewById(R.id.tv1to8result);
        etVectorConcentration = (EditText) findViewById(R.id.etVectorConcentration);
        etInsertConcentration = (EditText) findViewById(R.id.etInsertConcentration);
        etVectorSize = (EditText) findViewById(R.id.etVectorSize);
        etInsertSize = (EditText) findViewById(R.id.etInsertSize);
        resultRelativeLayout = (RelativeLayout) findViewById(R.id.ligation_result_layout);

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


                    resultRelativeLayout.setVisibility(View.VISIBLE);                               //make the text visible

                    hide_keyboard(this);

                    tv1to1result.setText(String.format("%.2f", DilutionOneToOne));                  //round all down to 2 decimal places
                    tv1to2result.setText(String.format("%.2f", (DilutionOneToOne * 2)));
                    tv1to4result.setText(String.format("%.2f", (DilutionOneToOne * 4)));
                    tv1to8result.setText(String.format("%.2f", (DilutionOneToOne * 8)));


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