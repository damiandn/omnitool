package damiandn.com.omnitool.Imaging;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.Window;

import damiandn.com.omnitool.R;

/**
 * Created by Desktop on 12/17/2014.
 */
public class ImagingMain extends Activity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setExitTransition(new Fade());
        getWindow().setEnterTransition(new Fade());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.imagingmain);


    }

    @Override
    public void onClick(View v) {

    }
}
