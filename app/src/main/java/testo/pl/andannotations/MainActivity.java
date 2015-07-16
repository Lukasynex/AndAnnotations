package testo.pl.andannotations;

import android.app.Activity;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.NoTitle;
import org.androidannotations.annotations.ViewById;

import java.util.Random;

@NoTitle
@Fullscreen
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
    @ViewById
    TextView first;
    @ViewById
    Button second;
    @ViewById
    EditText third;

    @AfterViews
    void initBookmarkList() {
        third.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                first.setBackgroundColor(Color.rgb(12, 100, 30 * s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Click({R.id.second})
    void buttonClicked() {
        String arr[] = new String[]{"Hej", "CZe", "Siema", "dzien dobry", "elo"};
        second.setText(arr[new Random().nextInt(arr.length)]);
    }
}