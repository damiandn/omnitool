package damiandn.com.omnitool.General;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import damiandn.com.omnitool.R;

/**
 * Created by Desktop on 12/3/2014.
 */
public class SetupWashTimers extends Activity implements View.OnClickListener {

    Button bStartTimers;
    EditText numOfWashes, washTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupwashtimer);

        bStartTimers = (Button) findViewById(R.id.bStartWashes);
        bStartTimers.setOnClickListener(this);

        numOfWashes = (EditText) findViewById(R.id.etNumberOfWashes);
        washTime = (EditText) findViewById(R.id.etTimePerWash);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bStartWashes:

                if (isEmpty(numOfWashes) || isEmpty(washTime)) {

                    Toast toast = Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT);
                    toast.show();

                }else {

                 Bundle parameters = new Bundle();
                 parameters.putInt("numOfWashes", Integer.parseInt(numOfWashes.getText().toString()));
                 parameters.putInt("washTime", Integer.parseInt(washTime.getText().toString()));
                 Intent i = new Intent(SetupWashTimers.this, WashTimer.class);
                 i.putExtras(parameters);
                 startActivity(i);

                }

                //set up bundle to pass information
                //start activity



            break;



        }

    }


    private boolean isEmpty(EditText etText) {                          //returns TRUE if edittext is EMPTY
        return etText.getText().toString().trim().length() == 0;
    }

}
