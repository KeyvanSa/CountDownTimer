package ir.ebookline.countdowntimer;


import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    Button controllerButton;
    Boolean counterIsActive = false;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    public void resetTimer(){
        controllerButton.setText("Go!");
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        counterIsActive = false;
    }

    public void updateTimer(int seconds){
        int mins =  seconds / 60;
        int secs =  seconds - mins * 60;

        String mins2 = String.valueOf(mins);
        String secs2 = String.valueOf(secs);

        if (secs <= 9){
            secs2 = "0" + secs2;
        }

        assert timerTextView != null;
        timerTextView.setText(mins2 +":"+ secs2);
    }

    public void controlTimer(View view){

        if (counterIsActive == false) {

            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            controllerButton.setText("Stop");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("0:00");
                }
            }.start();
        }else {
            timerTextView.setText("0:30");
            timerSeekBar.setProgress(30);
            resetTimer();
        }
    }

    private void init() {

        timerSeekBar = (SeekBar)findViewById(R.id.timerSeekBar);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        controllerButton = (Button)findViewById(R.id.controllerButton);
        assert timerSeekBar != null;
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


}