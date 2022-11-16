package com.example.pr_20_21;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    boolean isPlaying = false;
    ImageView car1;
    ImageView car2;
    TextView result_text;
    Button btn_start;
    Button btn_go1;
    Button btn_go2;
    int status = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        car1 = (ImageView) findViewById(R.id.car1);
        car2 = (ImageView) findViewById(R.id.car2);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_go1 = (Button) findViewById(R.id.btn_go1);
        result_text = (TextView) findViewById(R.id.result_text);
        result_text.setText("");
    }

    private void ResultText(int player){
        btn_start.setEnabled(true);
        btn_go1.setEnabled(false);
        if(player == 1){
            result_text.setTextColor(Color.rgb(233,30,99));
            result_text.setText("PLAYER 1 WIN!");
            btn_start.setVisibility(View.VISIBLE);
            status = 1;
        }
    }

    public void StartButton(View view){
        result_text.setText("");
        btn_start.setEnabled(false);
        btn_go1.setEnabled(true);
        btn_start.setVisibility(View.INVISIBLE);
        status = 0;
        Thread thread = new Thread( new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int left = car2.getLeft();
                    int top = car2.getTop();
                    int down = car2.getBottom();
                    int right = car2.getRight();


                    car2.layout(left + 20, top + 0, right + 20, down + 0);
                    if (right >= 1800) {
                        result_text.post(new Runnable() {
                            @Override
                            public void run() {
                                result_text.setTextColor(Color.rgb(63, 81, 181));
                                result_text.setText("PLAYER 2 WIN!");
                                btn_start.setEnabled(true);
                                btn_go1.setEnabled(false);
                                btn_start.setVisibility(View.VISIBLE);
                            }
                        });
                        break;
                    }
                    if (status == 1) {
                        break;
                    }
                    try {
                        Thread.sleep(new Random().nextInt(1400) + 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        thread.start();
    }

    public void onClickCar1(View view){
        int left = car1.getLeft();
        int top = car1.getTop();
        int down = car1.getBottom();
        int right = car1.getRight();
        car1.layout(left + 20, top + 0, right + 20, down + 0);

        if(right >= 1800){
            ResultText(1);
        }
    }

//    public void onClickCar2(View view){
//        int left = car2.getLeft();
//        int top = car2.getTop();
//        int down = car2.getBottom();
//        int right = car2.getRight();
//        car2.layout(left + 20, top + 0, right + 20, down + 0);
//
//        if(right >= 1800){
//            ResultText(2);
//        }
//    }

}