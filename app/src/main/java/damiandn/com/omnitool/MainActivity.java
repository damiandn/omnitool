package damiandn.com.omnitool;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import damiandn.com.omnitool.General.GeneralMenu;
import damiandn.com.omnitool.Imaging.ImagingMain;
import damiandn.com.omnitool.molecularbiology.MolBioMain;

public class MainActivity extends Activity implements View.OnClickListener{

    Button startMolBio, startGeneral;
    TextView tvStartMoBio, tvStartGeneral, tvStartImaging;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().setExitTransition(new Fade());
        getWindow().setEnterTransition(new Fade());

        setContentView(R.layout.activity_main);




        tvStartMoBio = (TextView) findViewById(R.id.tvStartMoBio);
        tvStartImaging = (TextView) findViewById(R.id.tvStartImaging);
        tvStartGeneral = (TextView) findViewById(R.id.tvStartGeneral);

        final View molBioContainerView = findViewById(R.id.tvStartMoBio);
        final View imagingContainerView = findViewById(R.id.tvStartImaging);
        final View generalContainerView = findViewById(R.id.tvStartGeneral);


        molBioContainerView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent i = new Intent(MainActivity.this, MolBioMain.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, tvStartMoBio, "trans_molbio");


                startActivity(i, options.toBundle());

            }
        });

        generalContainerView.setOnClickListener(new View.OnClickListener() {


           @Override
            public void onClick(View v) {


                Intent i = new Intent(MainActivity.this, GeneralMenu.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, tvStartGeneral, "trans_general");


                startActivity(i, options.toBundle());

            }
       });


        imagingContainerView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent i = new Intent(MainActivity.this, ImagingMain.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, tvStartImaging, "trans_imaging");


                startActivity(i, options.toBundle());

            }
        });


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

    }
}
