package testo.pl.andannotations.okhttp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Lukasz Marczak on 2015-07-18.
 * Simple class which gets data from selected url
 */
public class Get {
    public static final String TAG = Get.class.getSimpleName();

    private OkHttpClient client = new OkHttpClient();

    public String pull(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public void betterPull(String url, final TextView textView, final Activity context) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            String lukaszDeal = null, adasDeal = null;

            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
                textView.setText(";__;");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "Unexpected code " + response);
//                    throw new IOException("Unexpected code " + response);
                }
//                Headers responseHeaders = response.headers();
//                for (int i = 0; i < responseHeaders.size(); i++) {
//                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                }
                final Response _response_ = response;

                final String result = response.body().string();
                Log.d(TAG, result);
                JSONObject jObject = null;
                try {
                    jObject = new JSONObject(result);
                    JSONArray contacts = jObject.getJSONArray("results");
                    String delaA = contacts.getJSONObject(0).getString("adas");
                    String delaB = contacts.getJSONObject(1).getString("lukasz");
                    
                    lukaszDeal = delaB;
                    adasDeal = delaA;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (lukaszDeal != null && adasDeal != null)
                            textView.setText("Adas: " + adasDeal + "\nLukasz: " + lukaszDeal);
                    }
                });
            }
        });
    }
}