package damiandn.com.omnitool.Imaging;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.util.Log;
import android.view.Window;

import damiandn.com.omnitool.R;

/**
 * Created by Desktop on 1/1/2015.
 */
public class FluorophoreChart extends Activity {

    RecyclerView fpList;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("tag", "test1");

        getWindow().setExitTransition(new Fade());
        getWindow().setEnterTransition(new Fade());
        getWindow().setStatusBarColor(0xFF388E3C);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fluorophorechart);

        fpList = (RecyclerView) findViewById(R.id.fpRecycleView);
        LinearLayoutManager llm = new LinearLayoutManager(this);            //This is required by recyclerview. It "positions item views inside the row and determines when it is time to recycle the views."
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        fpList.setLayoutManager(llm);

        FPManager.getInstance();

        adapter = new MyAdapter(FPManager.getInstance().getFPs(), R.layout.fpcardview, this);             //Need to populate this, but first need to make the array of FPs. Pass in a list by calling the getFPs method of FPmanager (getInstance is like a shortcut to skip making an object etc)
        fpList.setAdapter(adapter);



        }




}
