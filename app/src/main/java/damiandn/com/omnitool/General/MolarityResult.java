package damiandn.com.omnitool.General;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.Window;
import android.widget.TextView;

import damiandn.com.omnitool.R;

/**
 * Created by Desktop on 1/3/2015.
 */
public class MolarityResult extends Activity {

    TextView line1, line2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(0xFF00796B);
        getWindow().setExitTransition(new Fade());
        getWindow().setEnterTransition(new Fade());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.molarityresult);

        line1 = (TextView) findViewById(R.id.tvMolarityResultLine1);
        line2 = (TextView) findViewById(R.id.tvMolarityResultLine2);

        Bundle Results = getIntent().getExtras();
        String resultValue = Results.getString("line2");
       // String units = Results.getString("units");

        String stLine2 = resultValue; // + units;

        line1.setText(Results.getString("line1"));
        line2.setText(stLine2);







    }
}
