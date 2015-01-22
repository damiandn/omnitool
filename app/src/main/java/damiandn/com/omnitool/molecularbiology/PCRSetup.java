package damiandn.com.omnitool.molecularbiology;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import damiandn.com.omnitool.R;

/**
 * Created by Desktop on 11/23/2014.
 */
public class PCRSetup extends Activity implements View.OnClickListener {

    TextView tvPCRTitle, tvMMNumber, tvBufferAmount, tvBuffer, tvwater, tvprimer1, tvprimer2, tvdNTP, tvtemplate, tvenzyme, tvInitialDenaturationTemp, tvInitialDenaturationTime, tvDenaturationTemp, tvDenaturationTime, tvAnnealingTime, tvExtensionTemp, tvExtensionTime, tvFinalExtensionTemp;
    Button bAddMM, bSubMM;
    Integer numberMM = 1;
    Double primer, enzyme, dNTP, Template, BufferAmount,water;
    String titleEnzyme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setExitTransition(new Fade());
        getWindow().setEnterTransition(new Fade());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setStatusBarColor(0xFF303F9F);

        setContentView(R.layout.pcrsetup_c);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);



        tvPCRTitle = (TextView) findViewById(R.id.tvSetupPCRTitle);
        tvwater = (TextView) findViewById(R.id.tvPCRWater);
        tvprimer1 = (TextView) findViewById(R.id.tvPCRPrimer1);
        tvprimer2 = (TextView) findViewById(R.id.tvPCRPrimer2);
        tvdNTP = (TextView) findViewById(R.id.tvPCRdNTP);
        tvtemplate = (TextView) findViewById(R.id.tvPCRtemplate);
        tvBufferAmount = (TextView) findViewById(R.id.tvPCRBufferAmount);
        tvBuffer = (TextView) findViewById(R.id.tvPCRBuffer);
        tvenzyme = (TextView) findViewById(R.id.tvPCREnzyme);
        tvInitialDenaturationTemp = (TextView) findViewById(R.id.tvInitialDenaturationTemp);
        tvInitialDenaturationTime = (TextView) findViewById(R.id.tvInitialDenaturationTime);
        tvDenaturationTemp = (TextView) findViewById(R.id.tvDenaturationTemp);
        tvDenaturationTime = (TextView) findViewById(R.id.tvDenaturationTime);
        tvAnnealingTime = (TextView) findViewById(R.id.tvAnnealingTime);
        tvExtensionTemp = (TextView) findViewById(R.id.tvExtensionTemp);
        tvExtensionTime = (TextView) findViewById(R.id.tvExtensionTime);
        tvFinalExtensionTemp = (TextView) findViewById(R.id.tvFinalExtensionTemp);
        bAddMM = (Button) findViewById(R.id.bPCRAddMM);
        bSubMM = (Button) findViewById(R.id.bPCRSubtractMM);
        tvMMNumber = (TextView) findViewById(R.id.tvPCRMMNumber);

        bAddMM.setOnClickListener(this);
        bSubMM.setOnClickListener(this);



        Bundle PCRparameters = getIntent().getExtras();
        primer = PCRparameters.getDouble("primer");
        enzyme = PCRparameters.getDouble("enzyme");
        dNTP = PCRparameters.getDouble("dNTP");
        double ExtensionTemp = PCRparameters.getDouble("ExtensionTemp");
        double ExtensionTimePerKB = PCRparameters.getDouble("ExtensionTimePerKB");
        double DenaturationTime= PCRparameters.getDouble("DenaturationTime");
        double DenaturationTemp= PCRparameters.getDouble("DenaturationTemp");
        double InitialDenaturationTime= PCRparameters.getDouble("InitialDenaturationTime");
        double AnnealingTime= PCRparameters.getDouble("AnnealingTime");
        double ProductSize= PCRparameters.getDouble("ProductSize");
        Template = PCRparameters.getDouble("template");
        String BufferType = PCRparameters.getString("BufferType");
        BufferAmount = PCRparameters.getDouble("Buffer");
        titleEnzyme = PCRparameters.getString("EnzymeName");


        double ProductSizeRound = Math.round(ProductSize / 1000);



         water = 50 - primer - primer - enzyme - dNTP - BufferAmount - Template;
        double extensiontime = ProductSizeRound * ExtensionTimePerKB;

        tvPCRTitle.setText("Setup " + titleEnzyme + " PCR");


        tvMMNumber.setText(Integer.toString(numberMM));             //sets the number of the Master Mix to 1
        tvBufferAmount.setText(Double.toString(BufferAmount));
        tvBuffer.setText(BufferType);
        tvprimer1.setText(Double.toString(primer));
        tvprimer2.setText(Double.toString(primer));
        tvwater.setText(Double.toString(water));
        tvdNTP.setText(Double.toString(dNTP));
        tvtemplate.setText(Double.toString(Template));
        tvenzyme.setText(Double.toString(enzyme));

        tvInitialDenaturationTemp.setText(Double.toString(DenaturationTemp));
        tvInitialDenaturationTime.setText(Double.toString(InitialDenaturationTime));

        tvDenaturationTemp.setText(Double.toString(DenaturationTemp));
        tvDenaturationTime.setText(Double.toString(DenaturationTime));

        tvAnnealingTime.setText(Double.toString(AnnealingTime));

        tvExtensionTemp.setText(Double.toString(ExtensionTemp));
        tvExtensionTime.setText(Double.toString(extensiontime));

        tvFinalExtensionTemp.setText(Double.toString(ExtensionTemp));

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bPCRAddMM:
            numberMM++;
            tvMMNumber.setText(Integer.toString(numberMM));


                tvMMNumber.setText(Integer.toString(numberMM));
                tvBufferAmount.setText(Double.toString(BufferAmount * numberMM));
                tvprimer1.setText(Double.toString(primer  * numberMM));
                tvprimer2.setText(Double.toString(primer  * numberMM));
                tvwater.setText(Double.toString(water  * numberMM));
                tvdNTP.setText(Double.toString(dNTP  * numberMM));
                tvenzyme.setText(Double.toString(enzyme * numberMM));

                if (numberMM > 1) {
                tvtemplate.setText("");
            }else{
                tvtemplate.setText(Double.toString(Template));
            }

            break;

            case R.id.bPCRSubtractMM:

                if (numberMM > 1) {

                    numberMM--;
                    tvMMNumber.setText(Integer.toString(numberMM));


                    tvMMNumber.setText(Integer.toString(numberMM));
                    tvBufferAmount.setText(Double.toString(BufferAmount * numberMM));
                    tvprimer1.setText(Double.toString(primer  * numberMM));
                    tvprimer2.setText(Double.toString(primer  * numberMM));
                    tvwater.setText(Double.toString(water  * numberMM));
                    tvdNTP.setText(Double.toString(dNTP  * numberMM));
                    tvenzyme.setText(Double.toString(enzyme * numberMM));

                    if (numberMM > 1) {
                        tvtemplate.setText("");
                    }else{
                        tvtemplate.setText(Double.toString(Template));
                    }



            }


            break;





        }

    }
}
