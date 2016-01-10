package com.example.nejc.pingpong;

import android.app.Activity;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;


public class Single_Player extends Activity {
    public boolean playing = true;
    //private TextView resultView3;
    //private TextView resultView2;
    // private TextView resultView;
    public int time = 40000000;
    public int speed = 50;
    //private Thread mSplashThread;
    boolean moving = false;
    float xPos, yPos, yMove = 0;
    float x,y;
    int vel = 0;
    float velX = 10, velY = 0;
    boolean moving_ball = false;
    int direction = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single__player);


        TextView ourText = (TextView) findViewById(R.id.textView2);
        Typeface tf = Typeface.createFromAsset(getAssets(), "homespun.ttf");
        ourText.setTypeface(tf);
        StrictMode.enableDefaults();
        ourText.setText("0");
        final ImageView top = (ImageView) findViewById(R.id.imageView4);
        final ImageView bottom = (ImageView) findViewById(R.id.imageView5);


        final ImageView racquet = (ImageView) findViewById(R.id.imageView);
        racquet.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        moving = true;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (moving) {
                            //x = event.getRawX() - balls.getWidth()/ 2;
                            y = event.getRawY() - racquet.getHeight();// * 3 / 2;
                            //balls.setX(x);
                            racquet.setY(y);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        moving = false;
                        break;
                    default:
                        break;
                }
                return true;
            }
        });


        final ImageView racquet_2 = (ImageView) findViewById(R.id.imageView3);    // big racquet
        /*racquet_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        moving = true;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (moving) {
                            //x = event.getRawX() - balls.getWidth()/ 2;
                            y = event.getRawY() - racquet_2.getHeight();// * 3/2;
                            //balls.setX(x);
                            racquet_2.setY(y);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        moving = false;
                        break;
                    default:
                        break;
                }
                return true;
            }
        });*/

        final ImageView ball = (ImageView) findViewById(R.id.imageView2);
        xPos = ball.getX();
        yPos = ball.getY();
        Random a = new Random();
        int i = a.nextInt(11);
        if (i <= 5)
            moving_ball = true;
        else
            moving_ball = false;
        new CountDownTimer(time,speed) {

            @Override
            public void onTick(long millisUntilFinished) {
                if(moving_ball) // proti desni
                {
                    if(ball.getX() < (racquet_2.getX() - 19)) {           //pozicija žoge manjša od maxX
                        xPos = ball.getX();                        // premik po X osi
                        xPos = xPos + (velX + vel);
                        ball.setX(xPos);

                        yPos = ball.getY();
                        if (yPos <= top.getY() || yPos >= bottom.getY())
                        {
                            direction = -1;
                        }
                        velY = yMove * 15 * -1;
                        if(velY < 0)
                        {
                            yPos = yPos - (velY - vel) * direction;
                        }
                        else if (velY == 0)
                        {
                            yPos = yPos - velY * direction;
                        }
                        else
                        {
                            yPos = yPos - (velY + vel) * direction;
                        }
                        ball.setY(yPos);



                    }
                    else{
                        if((ball.getY() < racquet_2.getY() + 1100) && (ball.getY() > racquet_2.getY() - 1000 ) ) {//nova slika, pa nièlo zbriš iz kode
                            MediaPlayer pong = MediaPlayer.create(Single_Player.this, R.drawable.pong);
                            pong.start();
                            yMove = yMove;//(ball.getY() - racquet_2.getY() + 1)/(166) - 0.5f; // 0 naravnost
                            moving_ball = false;
                            playing = true;
                            direction = 1;
                            //speed = speed / 2;
                            vel += 1;
                            //klic spremenljivke iz class Options za doloèanje zvoka

                        }
                        else
                        {
                            while(playing)
                            {
                                /*
                                String fontPath = "homespun.ttf";
                                TextView ourText2 = (TextView) findViewById(R.id.textView2);
                                Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
                                ourText2.setTypeface(tf);
                                StrictMode.enableDefaults();
                                int counter = Integer.parseInt(String.valueOf(ourText2.getText()));
                                counter += 1;
                                String string = String.valueOf(Integer.valueOf(counter));
                                ourText2.setText(string);
                                */
                                MediaPlayer over = MediaPlayer.create(Single_Player.this, R.drawable.game_over);
                                over.start();
                                vel = 0;
                                playing = false;
                                ball.setX(bottom.getX());
                                ball.setY(bottom.getY()/2);

                            }
                            //finish();
                            onFinish();
                        }
                    }    // ko je pozicija žoge veèja od maxX
                }
                if(!moving_ball) // proti levi
                {
                    if(ball.getX() > (racquet.getX() + 19)) {           //pozicija žoge manjša od maxX
                        xPos = ball.getX();                        // premik po X osi
                        xPos = xPos - (velX + vel); // velX + 0 vel se veca
                        ball.setX(xPos);

                        yPos = ball.getY();
                        if (yPos <= top.getY() || yPos >= bottom.getY())
                        {
                            direction = -1;
                        }

                        velY = yMove * 15 * -1;
                        if(velY < 0)
                        {
                            yPos = yPos - (velY - vel) * direction;
                        }
                        else if (velY == 0)
                        {
                            yPos = yPos - velY * direction;
                        }
                        else
                        {
                            yPos = yPos - (velY + vel) * direction;
                        }
                        ball.setY(yPos);
                    }
                    else{
                        if((ball.getY() < racquet.getY() + 166) && (ball.getY() > racquet.getY() - 10) ) {
                            MediaPlayer pong = MediaPlayer.create(Single_Player.this, R.drawable.pong);
                            pong.start();
                            yMove = (ball.getY() - racquet.getY() + 1)/(166) - 0.5f; // 0 naravnost
                            moving_ball = true;
                            playing = true;
                            direction = 1;
                            //speed = speed / 2;
                            vel += 1;

                            String fontPath = "homespun.ttf";
                            TextView ourText3 = (TextView) findViewById(R.id.textView2);
                            Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
                            ourText3.setTypeface(tf);
                            StrictMode.enableDefaults();
                            int counter = Integer.parseInt(String.valueOf(ourText3.getText()));
                            counter += 1;
                            String string = String.valueOf(Integer.valueOf(counter));
                            ourText3.setText(string);

                        }
                        else
                        {
                            while(playing)
                            {
                                /*
                                String fontPath = "homespun.ttf";
                                TextView ourText3 = (TextView) findViewById(R.id.textView2);
                                Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
                                ourText3.setTypeface(tf);
                                StrictMode.enableDefaults();
                                int counter = Integer.parseInt(String.valueOf(ourText3.getText()));
                                counter += 1;
                                String string = String.valueOf(Integer.valueOf(counter));
                                ourText3.setText(string);
                                */
                                MediaPlayer over = MediaPlayer.create(Single_Player.this, R.drawable.game_over);
                                over.start();
                                playing = false;
                                vel = 0;
                                ball.setX(bottom.getX());
                                ball.setY(bottom.getY()/2);
                            }

                            //finish();
                            onFinish();

                        }
                    }

                }
                //else onFinish();
            }

            @Override
            public void onFinish() {
                // do something end times 5s
            }

        }.start();

        //}


        //http://developer.android.com/guide/topics/sensors/sensors_overview.html
        //http://stackoverflow.com/questions/4513902/android-bouncing-ball

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game__two__players, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {

            finish();
            System.exit(1);
        }
        if ((keyCode == KeyEvent.KEYCODE_HOME))
        {
            finish();
            System.exit(1);
        }
        return super.onKeyDown(keyCode, event);
    }

    /*@Override
    public void onBackPressed() {
        MediaPlayer over = MediaPlayer.create(Game_Two_Players.this, R.drawable.game_over);
        over.release();
        over.stop();
        finish(); //quit activity
           // super.onBackPressed(); //quit application

    }*/

}

