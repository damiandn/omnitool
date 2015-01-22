package damiandn.com.omnitool.Imaging;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import damiandn.com.omnitool.R;

/**
 * Created by Desktop on 12/17/2014.
 */
public class ImagingMain extends Activity implements View.OnClickListener{

    String[] listitems = new String[]{"Nyquist Calculator", "Fluorophore Chart"};
    ListView imagingItems;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setExitTransition(new Fade());
        getWindow().setEnterTransition(new Fade());
        getWindow().setStatusBarColor(0xFF388E3C);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.imagingmain);


        imagingItems = (ListView) findViewById(R.id.lvImagingMain);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listitems);
        //adapter = new ArrayAdapter<String>(this, R.layout.listitem, listitems);

        imagingItems.setAdapter(adapter);

        imagingItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0:
                        Intent nyq = new Intent(ImagingMain.this, NyquistCalc.class);
                        startActivity(nyq);
                        break;


                    case 1:
                        Intent fp = new Intent(ImagingMain.this, FluorophoreChart.class);
                        startActivity(fp);
                        break;

                }


            }

        });
    }

    @Override
    public void onClick(View v) {

    }
}
