package testo.pl.andannotations;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.NoTitle;
import org.androidannotations.annotations.ViewById;

import java.util.Random;

@NoTitle
@Fullscreen
@EActivity(R.layout.activity_start)
public class StartActivity extends Activity {
    @ViewById
    EditText login, password;
    @ViewById
    Button loginButton;
    Activity context;

    @AfterViews
    void initViews() {
        password.setInputType(InputType.TYPE_MASK_FLAGS);
        context = this;
    }

    @Click({R.id.loginButton})
    void buttonClicked() {
        if (login.getText().toString().equals("admin"))
            if (password.getText().toString().equals("admin")) {
                Intent intent = new Intent(this, MainActivity_.class);
            } else {
                Toast.makeText(this, "Invalid login or password", Toast.LENGTH_SHORT).show();
//                Snackbar.make(context,"gg",Snackbar.LENGTH_SHORT).show();
            }
    }
}