package testo.pl.andannotations;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.NoTitle;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;

import java.util.Random;

import testo.pl.andannotations.okhttp.Get;

@NoTitle
@Fullscreen
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
    @ViewById
    TextView deals_info;
    @ViewById
    EditText postValue;
    @ViewById
    Spinner spinner;

    Context context;

    @AfterViews
    void afterViews() {
        context = this;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, new String[]{"Lukasz", "Adas", "Anita", "Anka"});
        spinner.setAdapter(adapter);
    }

    @Click({R.id.pull})
    void pullData() {
        String json = null;
        try {
            json = new Get().run(getResources().getString(R.string.url));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (json != null) {
                deals_info.setText(json);
            } else {
                Toast.makeText(context, "Error with connection", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Click({R.id.post})
    void postData() {
        String value = postValue.getText().toString();
        String name = (String) spinner.getSelectedItem();
        Toast.makeText(context, name + " ¿¹da od ciebie " + value + " dolanów ", Toast.LENGTH_LONG).show();
    }

}