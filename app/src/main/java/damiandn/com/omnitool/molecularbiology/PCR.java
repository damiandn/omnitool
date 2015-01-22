package damiandn.com.omnitool.molecularbiology;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import damiandn.com.omnitool.R;

/**
 * Created by Desktop on 11/23/2014.
 */
public class PCR extends Activity implements View.OnClickListener {


    String[] PCREnzymeList = new String[]{"TopTaq", "Pfu Ultra II", "LA Taq", "OneTaq"};
    Double productsize;
    ArrayAdapter<String> myAdapter;
    ListView lvPCREnzymeList;
    EditText PCRproductSize, TemplateAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setStatusBarColor(0xFF303F9F);
        setContentView(R.layout.pcr_enzyme_select_b);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);       //keeps the screen on

        lvPCREnzymeList = (ListView) findViewById(R.id.lvSelectPCREnzyme);
        PCRproductSize = (EditText) findViewById(R.id.etPCRProductSize);
        TemplateAmount = (EditText) findViewById(R.id.etPCRTemplateAmount);

        myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, PCREnzymeList);
        lvPCREnzymeList.setAdapter(myAdapter);

        lvPCREnzymeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

           //need to add checks to make sure the template isn't too high, and that both these editTexts contain only INTS

                switch (position) {

                    case 0:

                        Bundle PCRparameters = new Bundle();


                        if (isEmpty(PCRproductSize)) {
                            productsize = 1000.0;
                            PCRparameters.putDouble("ProductSize", 1000);
                        } else {
                            productsize = Double.parseDouble(PCRproductSize.getText().toString());
                            PCRparameters.putDouble("ProductSize", Double.parseDouble(PCRproductSize.getText().toString()));

                        }

                        PCRparameters.putDouble("primer", 0.5);
                        PCRparameters.putDouble("enzyme", 0.25);
                        PCRparameters.putDouble("dNTP", 1);
                        PCRparameters.putDouble("ExtensionTemp", 72);
                        PCRparameters.putDouble("ExtensionTimePerKB", 60);
                        PCRparameters.putDouble("DenaturationTime", 30);
                        PCRparameters.putDouble("DenaturationTemp", 94);
                        PCRparameters.putDouble("InitialDenaturationTime", 180);
                        PCRparameters.putDouble("AnnealingTime", 30);
                        PCRparameters.putString("BufferType", "Buffer (10X)");
                        PCRparameters.putDouble("Buffer", 5);
                        PCRparameters.putString("EnzymeName", lvPCREnzymeList.getItemAtPosition(position).toString());




                        if (isEmpty(TemplateAmount)) {
                            PCRparameters.putDouble("template", 1);
                        } else {

                            PCRparameters.putDouble("template", Double.parseDouble(TemplateAmount.getText().toString()));

                        }

                        Intent i = new Intent(PCR.this, PCRSetup.class);
                        i.putExtras(PCRparameters);
                        startActivity(i);

                        break;


                    case 1:

                        Bundle PCRparameters1 = new Bundle();


                        if (isEmpty(PCRproductSize)) {
                            productsize = 1000.0;
                            PCRparameters1.putDouble("ProductSize", 1000);
                        } else {
                            productsize = Double.parseDouble(PCRproductSize.getText().toString());
                            PCRparameters1.putDouble("ProductSize", Double.parseDouble(PCRproductSize.getText().toString()));

                        }


                        if (isEmpty(TemplateAmount)) {
                            PCRparameters1.putDouble("template", 1);
                        } else {

                            PCRparameters1.putDouble("template", Double.parseDouble(TemplateAmount.getText().toString()));

                        }


                        if (productsize <= 10000) {
                            PCRparameters1.putDouble("primer", 0.5);
                            PCRparameters1.putDouble("dNTP", 1.25);
                            PCRparameters1.putDouble("ExtensionTemp", 72);
                            PCRparameters1.putDouble("ExtensionTimePerKB", 15);
                            PCRparameters1.putDouble("DenaturationTemp", 95);
                            PCRparameters1.putDouble("DenaturationTime", 20);
                            PCRparameters1.putDouble("AnnealingTime", 20);


                        } else {
                            PCRparameters1.putDouble("primer", 1);
                            PCRparameters1.putDouble("dNTP", 2.5);
                            PCRparameters1.putDouble("ExtensionTemp", 68);
                            PCRparameters1.putDouble("ExtensionTimePerKB", 30);
                            PCRparameters1.putDouble("DenaturationTemp", 92);
                            PCRparameters1.putDouble("DenaturationTime", 10);
                            PCRparameters1.putDouble("AnnealingTime", 20);

                        }

                        PCRparameters1.putDouble("InitialDenaturationTime", 120);
                        PCRparameters1.putDouble("enzyme", 1);
                        PCRparameters1.putString("BufferType", "Buffer (10X)");
                        PCRparameters1.putDouble("Buffer", 5);
                        PCRparameters1.putString("EnzymeName", lvPCREnzymeList.getItemAtPosition(position).toString());


                        Intent i1 = new Intent(PCR.this, PCRSetup.class);
                        i1.putExtras(PCRparameters1);
                        startActivity(i1);

                        break;

                    case 2:

                        Bundle PCRparameters2 = new Bundle();

                        if (isEmpty(PCRproductSize)) {
                            productsize = 1000.0;
                            PCRparameters2.putDouble("ProductSize", 1000);
                        } else {
                            productsize = Double.parseDouble(PCRproductSize.getText().toString());
                            PCRparameters2.putDouble("ProductSize", Double.parseDouble(PCRproductSize.getText().toString()));

                        }

                        PCRparameters2.putDouble("primer", 0.5);
                        PCRparameters2.putDouble("enzyme", 0.5);
                        PCRparameters2.putDouble("dNTP", 2);
                        PCRparameters2.putDouble("ExtensionTemp", 72);
                        PCRparameters2.putDouble("ExtensionTimePerKB", 60);
                        PCRparameters2.putDouble("DenaturationTime", 10);
                        PCRparameters2.putDouble("DenaturationTemp", 98);
                        PCRparameters2.putDouble("InitialDenaturationTime", 60);
                        PCRparameters2.putDouble("AnnealingTime", 30);
                        PCRparameters2.putString("BufferType", "Buffer (10X)");
                        PCRparameters2.putDouble("Buffer", 5);
                        PCRparameters2.putString("EnzymeName", lvPCREnzymeList.getItemAtPosition(position).toString());


                        if (isEmpty(TemplateAmount)) {
                            PCRparameters2.putDouble("template", 1);
                        } else {

                            PCRparameters2.putDouble("template", Double.parseDouble(TemplateAmount.getText().toString()));
                        }

                        Intent i2 = new Intent(PCR.this, PCRSetup.class);
                        i2.putExtras(PCRparameters2);
                        startActivity(i2);

                        break;

                    case 3:

                        Bundle PCRparameters3 = new Bundle();

                        if (isEmpty(PCRproductSize)) {
                            productsize = 1000.0;
                            PCRparameters3.putDouble("ProductSize", 1000);
                        } else {
                            productsize = Double.parseDouble(PCRproductSize.getText().toString());
                            PCRparameters3.putDouble("ProductSize", Double.parseDouble(PCRproductSize.getText().toString()));

                        }


                        PCRparameters3.putDouble("primer", 1);
                        PCRparameters3.putDouble("enzyme", 0.25);
                        PCRparameters3.putDouble("dNTP", 1);
                        PCRparameters3.putDouble("ExtensionTemp", 68);
                        PCRparameters3.putDouble("ExtensionTimePerKB", 60);
                        PCRparameters3.putDouble("DenaturationTime", 30);
                        PCRparameters3.putDouble("DenaturationTemp", 94);
                        PCRparameters3.putDouble("InitialDenaturationTime", 30);
                        PCRparameters3.putDouble("AnnealingTime", 30);
                        PCRparameters3.putString("BufferType", "Buffer (5X)");
                        PCRparameters3.putDouble("Buffer", 10);
                        PCRparameters3.putString("EnzymeName", lvPCREnzymeList.getItemAtPosition(position).toString());


                        if (isEmpty(TemplateAmount)) {
                            PCRparameters3.putDouble("template", 1);
                        } else {

                            PCRparameters3.putDouble("template", Double.parseDouble(TemplateAmount.getText().toString()));
                        }

                        Intent i3 = new Intent(PCR.this, PCRSetup.class);
                        i3.putExtras(PCRparameters3);
                        startActivity(i3);

                        break;




            }








            }
        } );




}

    @Override
    public void onClick(View v) {

    }


    private boolean isEmpty(EditText etText) {                          //returns TRUE if edittext is EMPTY
        return etText.getText().toString().trim().length() == 0;
    }


}


