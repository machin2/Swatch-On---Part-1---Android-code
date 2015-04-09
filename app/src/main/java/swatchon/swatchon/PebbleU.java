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
import com.getpebble.android.kit.util.PebbleDictionary;

import java.util.UUID;


public class PebbleU extends ActionBarActivity {

    private static final int DATA_KEY = 0;

    private static final int KEY_1 = 1;
    private static final int KEY_2 = 2;
    private static final int KEY_3 = 3;
    public final static UUID SWATCHON_UUID = UUID.fromString("575bb440-09f4-42b3-be82-e5b812b6b6ff");

    PebbleDictionary data = new PebbleDictionary();

    private Button move = null;
    private Button stop = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pebble_u);

        move = (Button) findViewById(R.id.button2);
        stop = (Button) findViewById(R.id.button);

        move.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent PeToA = new Intent(PebbleU.this, MainActivity2.class);
                startActivity(PeToA);

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent stop = new Intent(PebbleU.this, MainActivity.class);
                startActivity(stop);

                Log.i(getLocalClassName(), "App Closed");
                PebbleKit.closeAppOnPebble(getApplicationContext(), SWATCHON_UUID);

                CharSequence text = "Pebble App Closed";
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

        PebbleKit.registerReceivedDataHandler(this, new PebbleKit.PebbleDataReceiver(SWATCHON_UUID) {

            @Override
            public void receiveData(final Context context, final int transactionId, final PebbleDictionary data) {

                PebbleKit.sendAckToPebble(getApplicationContext(), transactionId);

                int KeyRequest = data.getUnsignedIntegerAsLong(DATA_KEY).intValue();
                Log.i(getLocalClassName(), "Requête " + KeyRequest);


                switch (KeyRequest) {
                    case KEY_1: {
                        Requete scenario = new Requete();
                        scenario.execute(1);

                        CharSequence text = "Lamp ON for 10 sec !";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();

                        break;
                    }
                    case KEY_2: {
                        Requete scenario = new Requete();
                        scenario.execute(2);

                        CharSequence text = "Lamp ON !";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                        break;
                    }

                    case KEY_3: {
                        Requete scenario = new Requete();
                        scenario.execute(3);
                        CharSequence text = "Lamp OFF !";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                        break;
                    }

                    default:
                        break;

                }



            }
        });



        /*        Add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Add a key of 0, and a uint8_t (byte) of value 42.
                data.addUint8(0, (byte) 22);

                // Add a key of 1, and a string value.
                data.addString(1, "Swatch RPZ");

                Log.i(getLocalClassName(), "Data Added");
            }
        });

        Send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                PebbleKit.sendDataToPebble(getApplicationContext(), SWATCHON_UUID, data);
                Log.i(getLocalClassName(), "Data Sent");

            }
        });*/



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pebble_u, menu);
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
