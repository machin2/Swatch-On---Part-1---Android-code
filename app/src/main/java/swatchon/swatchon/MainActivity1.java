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

import com.getpebble.android.kit.PebbleKit;

import java.util.UUID;


public class MainActivity1 extends ActionBarActivity {

    private Button AndroidApp = null;
    private Button PebbleApp = null;
    private Button AndroidS = null;
    private Button PebbleS = null;

    public final static UUID SWATCHON_UUID = UUID.fromString("575bb440-09f4-42b3-be82-e5b812b6b6ff");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity1);

        AndroidApp = (Button) findViewById(R.id.button);
        PebbleApp = (Button) findViewById(R.id.button3);
        PebbleS = (Button) findViewById(R.id.button4);
        AndroidS = (Button) findViewById(R.id.button6);



        AndroidApp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent Start = new Intent(MainActivity1.this, MainActivity2.class);
                startActivity(Start);
            }
        });



        PebbleS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent Start = new Intent(MainActivity1.this, Pebble.class);
                startActivity(Start);

            }
        });

        PebbleApp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent Start = new Intent(MainActivity1.this, PebbleU.class);
                startActivity(Start);

            }
        });
        AndroidS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent Start = new Intent(MainActivity1.this, SettingsActivity.class);
                startActivity(Start);

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
        getMenuInflater().inflate(R.menu.menu_main_activity1, menu);
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
            Log.i(getLocalClassName(), "App Closed");
            PebbleKit.closeAppOnPebble(getApplicationContext(), SWATCHON_UUID);

            CharSequence text = "Pebble App Closed";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
