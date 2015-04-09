package swatchon.swatchon;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class Requete extends AsyncTask<Integer, String, String> {

    public boolean Fi;

    @Override
    protected String doInBackground(Integer... params) {
        // TODO Auto-generated method stub
        URI uri;
        try {
            uri = new URI("https://zibase.net/api/get/ZAPI.php?zibase=ZiBASE005e6d&token=3c14400a37&service=execute&target=scenario&id="+params[0]);
        }

        catch (URISyntaxException e) {
            e.printStackTrace();
            uri = null;
        }

        StringBuilder response = new StringBuilder();
        try {
            HttpGet get = new HttpGet();
            get.setURI(uri);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(get);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                Log.d("[GET REQUEST", "HTTP Get succeeded");
                HttpEntity messageEntity = httpResponse.getEntity();
                InputStream is = messageEntity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;

                Fi = true;

                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            }
        } catch (Exception e) {
            Log.e("[GET REQUEST]", e.getMessage());
        }
        Log.d("[GET REQUEST]", response.toString());

        Log.d("debug", "Lampe toggle ID :"+params[0]);
        return response.toString();
    }

}

