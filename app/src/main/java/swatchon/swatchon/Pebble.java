package swatchon.swatchon;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.getpebble.android.kit.Constants;
import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;
import java.util.UUID;


public class Pebble extends ActionBarActivity {

    public boolean connected;
    public final static UUID SWATCHON_UUID = UUID.fromString("575bb440-09f4-42b3-be82-e5b812b6b6ff");

    private Button SApp = null;
    private Button CApp = null;
    private Button Conn = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pebble);


        SApp = (Button) findViewById(R.id.buttonS);
        CApp = (Button) findViewById(R.id.buttonC);
        Conn = (Button) findViewById(R.id.buttonConn);


        SApp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.i(getLocalClassName(), "App Launched");
                PebbleKit.startAppOnPebble(getApplicationContext(), SWATCHON_UUID);

            }
        });

        CApp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.i(getLocalClassName(), "App Closed");
                PebbleKit.closeAppOnPebble(getApplicationContext(), SWATCHON_UUID);

                CharSequence text = "Pebble App Closed";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();


            }
        });

        Conn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                connected = PebbleKit.isWatchConnected(getApplicationContext());
                Log.i(getLocalClassName(), "Pebble is " + (connected ? "connected" : "not connected"));

                CharSequence text = "Pebble is " + (connected ? "connected" : "not connected");
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();

            }
        });

        PebbleKit.registerPebbleConnectedReceiver(getApplicationContext(), new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i(getLocalClassName(), "Pebble connected!");

                CharSequence text = "Pebble connected !";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }

        });

        PebbleKit.registerPebbleDisconnectedReceiver(getApplicationContext(), new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i(getLocalClassName(), "Pebble disconnected!");

                CharSequence text = "Pebble disconnected !";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pebble, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_settings) {

            connected = PebbleKit.isWatchConnected(getApplicationContext());
            Log.i(getLocalClassName(), "Pebble is " + (connected ? "connected" : "not connected"));

        }

        return super.onOptionsItemSelected(item);
    }
}