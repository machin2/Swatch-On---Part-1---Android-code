package swatchon.swatchon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.getpebble.android.kit.PebbleKit;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity2 extends ActionBarActivity {

    private Button Button5 = null;
    public ToggleButton ToggleButton = null;




    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        Button5 = (Button) findViewById(R.id.button5);
        ToggleButton = (ToggleButton) findViewById(R.id.togglebutton);


        Button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Requete scenario = new Requete();
                scenario.execute(1);

                CharSequence text = "Lamp ON for 10 sec !";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();

                sendAlertToPebble(1);

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

  public void onToggleClicked(View view) {
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();

        if (on) {
            Requete scenario = new Requete();
            scenario.execute(2);
            CharSequence text = "Lamp ON !";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();

            sendAlertToPebble(2);

        } else {
            Requete scenario = new Requete();
            scenario.execute(3);
            CharSequence text = "Lampe OFF !";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
            toast.show();

            sendAlertToPebble(3);
        }
    }


    public void sendAlertToPebble(int scneario) {
        final Intent i = new Intent("com.getpebble.action.SEND_NOTIFICATION");
        String text = null;
        switch(scneario)
        {
            case 1 : text = "Lamp ON for 10 sec !";
                break;
            case 2 : text = "Lamp ON !";
                break;
            case 3 : text = "Lamp OFF !";
                break;
            default :
              break;
        }

        final Map data = new HashMap();
        data.put("title", "SWATCH'ON");
        data.put("body", text);
        final JSONObject jsonData = new JSONObject(data);
        final String notificationData = new JSONArray().put(jsonData).toString();

        i.putExtra("messageType", "PEBBLE_ALERT");
        i.putExtra("sender", "MyAndroidApp");
        i.putExtra("notificationData", notificationData);

        Log.i(getLocalClassName(), "About to send a modal alert to Pebble: " + notificationData);
        sendBroadcast(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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

        }

        return super.onOptionsItemSelected(item);
    }


}


