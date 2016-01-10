package com.example.nejc.pingpong;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Main extends Activity {
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button insert=(Button) findViewById(R.id.button2);

        insert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Game_Two_Players.class);
                startActivity(intent);
            }

        });

        Button insert2=(Button) findViewById(R.id.button3);

        insert2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Options.class);
                startActivity(intent);
            }

        });

        Button insert3=(Button) findViewById(R.id.button);

        insert3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Single_Player.class);
                startActivity(intent);
            }

        });

        Button insert_2=(Button) findViewById(R.id.button4);

        insert_2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                 new AlertDialog.Builder(context)
                        .setTitle("Exit")
                        .setMessage("Are you sure you want to exit game?" )
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                                // execute
                            }
                        })
                         .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int which) {

                                 // do nothing
                            }
                        })
                                 //.setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
