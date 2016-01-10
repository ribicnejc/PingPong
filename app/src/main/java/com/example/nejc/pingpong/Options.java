package com.example.nejc.pingpong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class Options extends Activity {
    private Button insert;
    private Button insert2;
    private Button insert3;
    private Button insert4;
    int Sound = 1;
        static boolean sound = true;
        static int Difficulty = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        insert4=(Button) findViewById(R.id.button5);         //sound
        insert4.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN: {
                        if(Sound%2 != 0)
                        {
                            insert4.setPressed(true);
                            sound = true;
                        }
                        else if (Sound%2 == 0)
                        {
                            insert4.setPressed(false);
                            sound = false;
                        }
                    }

                    break;
                    case MotionEvent.ACTION_UP: {
                       Sound += 1;
                    }
                    break;
                }
                return true;
            }
        });



        insert=(Button) findViewById(R.id.button8);         //easy
        insert.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                insert.setPressed(true);
                insert2.setPressed(false);
                insert3.setPressed(false);
                Difficulty = 0;
                return true;
            }
        });

        insert2=(Button) findViewById(R.id.button7);         //medium
        insert2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                insert.setPressed(false);
                insert2.setPressed(true);
                insert3.setPressed(false);
                Difficulty = 1;
                return true;

            }
        });

        insert3=(Button) findViewById(R.id.button6);         //hard
        insert3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                insert.setPressed(false);
                insert2.setPressed(false);
                insert3.setPressed(true);
                Difficulty = 2;
                return true;
            }
        });

        ImageView insert5=(ImageView) findViewById(R.id.imageView7);

        insert5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Main.class);
                startActivity(intent);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
}
