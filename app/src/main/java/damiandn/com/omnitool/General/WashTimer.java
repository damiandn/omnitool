package damiandn.com.omnitool.General;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import damiandn.com.omnitool.R;

/**
 * Created by Desktop on 12/3/2014.
 */
public class WashTimer extends Activity implements View.OnClickListener {


    ArrayList<Button> buttonArray = new ArrayList<Button>();
    ArrayList<RelativeLayout> layoutList = new ArrayList<RelativeLayout>();
    ArrayList<CardView> cardList = new ArrayList<>();
    ArrayList<TextView> timerArray = new ArrayList<TextView>();
    ArrayList<Handler> handlerArray = new ArrayList<Handler>();

    Button start1, start2, start3, start4, start5, start6, clearall;
    TextView timer1, timer2, timer3, timer4, timer5, timer6;
    Integer numOfWashes, washTime, washTimeSecs, currentTimer;
    RelativeLayout rlTimer1, rlTimer2, rlTimer3, rlTimer4, rlTimer5, rlTimer6;
    CardView cvTimer1, cvTimer2, cvTimer3, cvTimer4, cvTimer5, cvTimer6;



    private Handler customHandler1 = new Handler();
    private Handler customHandler2 = new Handler();
    private Handler customHandler3 = new Handler();
    private Handler customHandler4 = new Handler();
    private Handler customHandler5 = new Handler();
    private Handler customHandler6 = new Handler();




    long timeInMillseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    private long startTime = 0L;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setStatusBarColor(0xFF00796B);


        setContentView(R.layout.washtimer_b);

        Bundle parameters = getIntent().getExtras();

        numOfWashes = parameters.getInt("numOfWashes");
        washTime = parameters.getInt("washTime");

        Log.v("washes", Integer.toString(numOfWashes));
        Log.v("time", Integer.toString(washTime));


        currentTimer = 0;


        //sets the wash time in mins and secs
        washTimeSecs = washTime * 60;
        int mins = washTimeSecs / 60;
        washTimeSecs = washTimeSecs % 60;


        RelativeLayout rlTimer1 = (RelativeLayout) findViewById(R.id.rlTimer1);
        RelativeLayout rlTimer2 = (RelativeLayout) findViewById(R.id.rlTimer2);
        RelativeLayout rlTimer3 = (RelativeLayout) findViewById(R.id.rlTimer3);
        RelativeLayout rlTimer4 = (RelativeLayout) findViewById(R.id.rlTimer4);
        RelativeLayout rlTimer5 = (RelativeLayout) findViewById(R.id.rlTimer5);
        RelativeLayout rlTimer6 = (RelativeLayout) findViewById(R.id.rlTimer6);


        CardView cvTimer1 = (CardView) findViewById(R.id.card_view_timer1);
        CardView cvTimer2 = (CardView) findViewById(R.id.card_view_timer2);
        CardView cvTimer3 = (CardView) findViewById(R.id.card_view_timer3);
        CardView cvTimer4 = (CardView) findViewById(R.id.card_view_timer4);
        CardView cvTimer5 = (CardView) findViewById(R.id.card_view_timer5);
        CardView cvTimer6 = (CardView) findViewById(R.id.card_view_timer6);

        layoutList.add(rlTimer1);
        layoutList.add(rlTimer2);
        layoutList.add(rlTimer3);
        layoutList.add(rlTimer4);
        layoutList.add(rlTimer5);
        layoutList.add(rlTimer6);

        cardList.add(cvTimer1);
        cardList.add(cvTimer2);
        cardList.add(cvTimer3);
        cardList.add(cvTimer4);
        cardList.add(cvTimer5);
        cardList.add(cvTimer6);

        for (int i = 0; i < cardList.size(); i++) {                   //Set all views to INVISIBLE

            cardList.get(i).setVisibility(View.INVISIBLE);
        }

        Button start1 = (Button) findViewById(R.id.bTime1Start);
        Button start2 = (Button) findViewById(R.id.bTime2Start);
        Button start3 = (Button) findViewById(R.id.bTime3Start);
        Button start4 = (Button) findViewById(R.id.bTime4Start);
        Button start5 = (Button) findViewById(R.id.bTime5Start);
        Button start6 = (Button) findViewById(R.id.bTime6Start);
        Button clearall= (Button) findViewById(R.id.bClearTimers);

        start1.setOnClickListener(this);
        start2.setOnClickListener(this);
        start3.setOnClickListener(this);
        start4.setOnClickListener(this);
        start5.setOnClickListener(this);
        start6.setOnClickListener(this);
        clearall.setOnClickListener(this);

        buttonArray.add(start1);
        buttonArray.add(start2);
        buttonArray.add(start3);
        buttonArray.add(start4);
        buttonArray.add(start5);
        buttonArray.add(start6);

        timer1 = (TextView) findViewById(R.id.tvTimer1Time);
        timer2 = (TextView) findViewById(R.id.tvTimer2Time);
        timer3 = (TextView) findViewById(R.id.tvTimer3Time);
        timer4 = (TextView) findViewById(R.id.tvTimer4Time);
        timer5 = (TextView) findViewById(R.id.tvTimer5Time);
        timer6 = (TextView) findViewById(R.id.tvTimer6Time);

        timerArray.add(timer1);
        timerArray.add(timer2);
        timerArray.add(timer3);
        timerArray.add(timer4);
        timerArray.add(timer5);
        timerArray.add(timer6);


        handlerArray.add(customHandler1);
        handlerArray.add(customHandler2);
        handlerArray.add(customHandler3);
        handlerArray.add(customHandler4);
        handlerArray.add(customHandler5);
        handlerArray.add(customHandler6);






        //set up the views

        for (int i = 0; i < numOfWashes; i++) {

            cardList.get(i).setVisibility(View.VISIBLE);
           // cardList.get(i).setAlpha(0.5F);
            cardList.get(i).setElevation(0);
            buttonArray.get(i).setVisibility(View.INVISIBLE);
            timerArray.get(i).setText("" + mins + ":" + String.format("%02d", washTimeSecs));

                   }

        //layoutList.get(0).setAlpha(1);
        cardList.get(0).setElevation(10);
        buttonArray.get(0).setVisibility(View.VISIBLE);







        timer1.setText("" + mins + ":" + String.format("%02d", washTimeSecs));
        timer2.setText("" + mins + ":" + String.format("%02d", washTimeSecs));
        timer3.setText("" + mins + ":" + String.format("%02d", washTimeSecs));
        timer4.setText("" + mins + ":" + String.format("%02d", washTimeSecs));
        timer5.setText("" + mins + ":" + String.format("%02d", washTimeSecs));
        timer6.setText("" + mins + ":" + String.format("%02d", washTimeSecs));

    }




    @Override
    public void onClick(View v) {



        switch (v.getId()) {

            case R.id.bTime1Start:

                startTime = SystemClock.uptimeMillis();
                handlerArray.get(0).postDelayed(updateTimerThread, 0);

            break;

            case R.id.bTime2Start:


               // handlerArray.get(0).removeCallbacksAndMessages(updateTimerThread);
                currentTimer++;
                timeInMillseconds = 0;
                startTime = SystemClock.uptimeMillis();
                handlerArray.get(currentTimer).postDelayed(updateTimerThread, 0);


                break;

            case R.id.bTime3Start:

                //handlerArray.get(0).removeCallbacksAndMessages(updateTimerThread);
                currentTimer++;
                timeInMillseconds = 0;
                startTime = SystemClock.uptimeMillis();
                handlerArray.get(currentTimer).postDelayed(updateTimerThread, 0);

                break;

            case R.id.bTime4Start:

                //handlerArray.get(0).removeCallbacksAndMessages(updateTimerThread);
                currentTimer++;
                timeInMillseconds = 0;
                startTime = SystemClock.uptimeMillis();
                handlerArray.get(currentTimer).postDelayed(updateTimerThread, 0);

                break;

            case R.id.bTime5Start:

                //handlerArray.get(0).removeCallbacksAndMessages(updateTimerThread);
                currentTimer++;
                timeInMillseconds = 0;
                startTime = SystemClock.uptimeMillis();
                handlerArray.get(currentTimer).postDelayed(updateTimerThread, 0);

                break;

            case R.id.bTime6Start:

                //handlerArray.get(0).removeCallbacksAndMessages(updateTimerThread);
                currentTimer++;
                timeInMillseconds = 0;
                startTime = SystemClock.uptimeMillis();
                handlerArray.get(currentTimer).postDelayed(updateTimerThread, 0);

                break;

            case R.id.bClearTimers:

                Toast toast = Toast.makeText(this, "Clearing Timers", Toast.LENGTH_SHORT);
                toast.show();


                handlerArray.get(currentTimer).removeCallbacksAndMessages(updateTimerThread);



                for (int i = 0; i < numOfWashes; i++) {
                    //layoutList.get(i).setAlpha(0.5F);
                    cardList.get(i).setElevation(0);
                    timerArray.get(i).setText("" + 0 + ":" + String.format("%02d", 0));
                    buttonArray.get(i).setVisibility(View.INVISIBLE);

                }

                currentTimer = numOfWashes;
                timeInMillseconds = 0;
                updatedTime = 0;
                break;

        }

    }




    private Runnable updateTimerThread = new Runnable() {


        @Override
        public void run() {



            int TimeTimer = (washTime * 60 * 1000) + (washTimeSecs * 1000);

            updatedTime = TimeTimer - timeInMillseconds;

            if (updatedTime > 0) {

                timeInMillseconds = SystemClock.uptimeMillis() - startTime;

                updatedTime = TimeTimer - timeInMillseconds;

                int secs = (int) (updatedTime / 1000);
                int mins = secs / 60;
                secs = secs % 60;

                timerArray.get(currentTimer).setText("" + mins + ":" + String.format("%02d", secs));
                handlerArray.get(currentTimer).postDelayed(this, 0);
            } else {

                buttonArray.get(currentTimer).setVisibility(View.INVISIBLE);

                if (currentTimer < numOfWashes) {
                    //cardList.get(currentTimer).setAlpha(0.5F);
                    cardList.get(currentTimer).setElevation(0);
                    //cardList.get(currentTimer + 1).setAlpha(1);
                    cardList.get(currentTimer + 1).setElevation(10);

                    buttonArray.get(currentTimer + 1).setVisibility(View.VISIBLE);
                    handlerArray.get(0).removeCallbacksAndMessages(updateTimerThread);
                }
            }




        }
    };



        }




