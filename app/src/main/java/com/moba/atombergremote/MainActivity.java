package com.moba.atombergremote;

import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ConsumerIrManager irManager;

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

        powerButton.setOnClickListener(v ->
                showToast("POWER"));

        speed1Button.setOnClickListener(v ->
                showToast("SPEED 1"));

        speed2Button.setOnClickListener(v ->
                showToast("SPEED 2"));

        speed3Button.setOnClickListener(v ->
                showToast("SPEED 3"));

        speed4Button.setOnClickListener(v ->
                showToast("SPEED 4"));

        speed5Button.setOnClickListener(v ->
                showToast("SPEED 5"));

        boostButton.setOnClickListener(v ->
                showToast("BOOST"));

        timerButton.setOnClickListener(v ->
                showToast("TIMER"));

        ledButton.setOnClickListener(v ->
                showToast("LED"));

        sleepButton.setOnClickListener(v -> {

            showToast("SLEEP");

            sendIr(0x718EF300L);
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