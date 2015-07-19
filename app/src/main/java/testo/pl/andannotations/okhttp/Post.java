package testo.pl.andannotations.okhttp;

import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Lukasz Marczak on 2015-07-18.
 * Simple class which posts data to selected url
 */
public class Post {
    public static final String TAG = Post.class.getSimpleName();
    private OkHttpClient client = new OkHttpClient();

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

    public void postString(String url, String postBody) throws Exception {
        RequestBody body = RequestBody.create(JSON, postBody);
        Request request = new Request.Builder()
                .url(url)
                .post(/**RequestBody.create(MEDIA_TYPE_MARKDOWN,*/body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e(TAG, "Response isn't succesfull: ");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d(TAG, "Response: " + response.body().string());

            }
        });
    }
}
