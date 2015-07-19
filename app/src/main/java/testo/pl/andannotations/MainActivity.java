package testo.pl.andannotations;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.NoTitle;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import testo.pl.andannotations.okhttp.Get;
import testo.pl.andannotations.okhttp.Post;

@NoTitle
@Fullscreen
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @ViewById
    TextView deals_info;
    @ViewById
    EditText postValue;
    @ViewById
    Spinner spinner;

    Activity context;

    @AfterViews
    void afterViews() {
        context = this;

        List<String> otherUsers = new ArrayList<>();

        for (String user : Config.USERS) {
            if (user.equals(Config.currentUser)) {
                Log.d(TAG, "current User is " + user);
            } else {
                Log.d(TAG, "adding next user: " + user);
                otherUsers.add(user);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, otherUsers);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Choose..");
    }

    @Click({R.id.pull})
    void pullData() {
        Get getter = new Get();

        try {
            String urlName = "http://jaszczomp.cal24.pl/posts/" + Config.currentUser + ".txt";
            Log.i(TAG, urlName);
            getter.betterPull(urlName, deals_info, context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Click({R.id.post})
    void postData() {

        String value = postValue.getText().toString();
        if (postValue.length() == 0) {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "Puste pole!", Toast.LENGTH_SHORT).show();
                    postValue.setBackgroundColor(Color.RED);
                    try {
                        Log.e(TAG, "perform sleeping for 0.5 seconds ");
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Log.e(TAG, "sleeping failed");
                        e.printStackTrace();
                    }
                    postValue.setBackgroundColor(Color.LTGRAY);
                    return;
                }
            });
        }
        Log.v(TAG,"start normal");
        String name = (String) spinner.getSelectedItem();
        name = name.toLowerCase();
        String dolans = getResources().getString(R.string.dolans);
        String demand = getResources().getString(R.string.demand);
        String json = name + demand + value + dolans;
        Post poster = new Post();
        try {
            String url = "http://jaszczomp.cal24.pl/posts/" + Config.currentUser + "_post.php";
            Log.i(TAG, "selected url: " + url);

            String data = JsonBuilder.update(Config.previousData, name, value);
            poster.postString(url, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(context, name + " " + demand + " " + value + " " + dolans, Toast.LENGTH_LONG).show();
    }
}