package com.moba.atombergremote;

import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.view.HapticFeedbackConstants;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ConsumerIrManager irManager;
    private static final long POWER  = 0x6E91F300L;
    private static final long SPEED1 = 0x748BF300L;
    private static final long SPEED2 = 0x6F90F300L;
    private static final long SPEED3 = 0x758AF300L;
    private static final long SPEED4 = 0x6C93F300L;
    private static final long SPEED5 = 0x7788F300L;
    private static final long BOOST  = 0x708FF300L;
    private static final long TIMER  = 0x6996F300L;
    private static final long SLEEP  = 0x718EF300L;

    Button powerButton;
    Button speed1Button;
    Button speed2Button;
    Button speed3Button;
    Button speed4Button;
    Button speed5Button;
    Button boostButton;
    Button timerButton;
    Button ledButton;
    Button sleepButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // IR BLASTER DETECTION

        irManager = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);

        if (irManager == null) {
            Toast.makeText(this,
                    "IR Blaster NOT detected",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,
                    "IR Blaster detected",
                    Toast.LENGTH_LONG).show();
        }

        // CONNECT BUTTONS

        powerButton = findViewById(R.id.powerButton);
        speed1Button = findViewById(R.id.speed1Button);
        speed2Button = findViewById(R.id.speed2Button);
        speed3Button = findViewById(R.id.speed3Button);
        speed4Button = findViewById(R.id.speed4Button);
        speed5Button = findViewById(R.id.speed5Button);

        boostButton = findViewById(R.id.boostButton);

        timerButton = findViewById(R.id.timerButton);
        ledButton = findViewById(R.id.ledButton);
        sleepButton = findViewById(R.id.sleepButton);

        // TEST CLICK LISTENERS

        powerButton.setOnClickListener(v -> {

            v.performHapticFeedback(
                    HapticFeedbackConstants.VIRTUAL_KEY);

            sendIr(POWER);
        });

        speed1Button.setOnClickListener(v -> {

            v.performHapticFeedback(
                    HapticFeedbackConstants.VIRTUAL_KEY);

            sendIr(SPEED1);
        });

        speed2Button.setOnClickListener(v -> {

            v.performHapticFeedback(
                    HapticFeedbackConstants.VIRTUAL_KEY);

            sendIr(SPEED2);
        });

        speed3Button.setOnClickListener(v -> {

            v.performHapticFeedback(
                    HapticFeedbackConstants.VIRTUAL_KEY);

            sendIr(SPEED3);
        });

        speed4Button.setOnClickListener(v -> {

            v.performHapticFeedback(
                    HapticFeedbackConstants.VIRTUAL_KEY);

            sendIr(SPEED4);
        });

        speed5Button.setOnClickListener(v -> {

            v.performHapticFeedback(
                    HapticFeedbackConstants.VIRTUAL_KEY);

            sendIr(SPEED5);
        });

        boostButton.setOnClickListener(v -> {

            v.performHapticFeedback(
                    HapticFeedbackConstants.VIRTUAL_KEY);

            sendIr(BOOST);
        });

        timerButton.setOnClickListener(v -> {

            v.performHapticFeedback(
                    HapticFeedbackConstants.VIRTUAL_KEY);

            sendIr(TIMER);
        });

        ledButton.setOnClickListener(v -> {

            v.performHapticFeedback(
                    HapticFeedbackConstants.VIRTUAL_KEY);

            // LED command not discovered yet
        });

        sleepButton.setOnClickListener(v -> {

            v.performHapticFeedback(
                    HapticFeedbackConstants.VIRTUAL_KEY);

            sendIr(SLEEP);
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private int[] buildNecSignal(long code) {

        int[] pattern = new int[67];

        pattern[0] = 9000;
        pattern[1] = 4500;

        for (int i = 0; i < 32; i++) {

            pattern[2 + (i * 2)] = 560;

            if (((code >> i) & 1) == 1) {
                pattern[3 + (i * 2)] = 1690;
            } else {
                pattern[3 + (i * 2)] = 560;
            }
        }

        pattern[66] = 560;

        return pattern;
    }

    private void sendIr(long code) {

        if (irManager == null) {
            Toast.makeText(this,
                    "No IR Blaster",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        irManager.transmit(
                38000,
                buildNecSignal(code)
        );
    }

}