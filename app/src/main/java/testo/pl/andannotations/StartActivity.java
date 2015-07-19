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
        // password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        context = this;

    }

    @Click({R.id.loginButton})
    void buttonClicked() {
        boolean isValid = (boolean) isLoginValid()[0];
        if (isValid) {
            String user = (String)isLoginValid()[1];
            Config.currentUser = user;
            Intent intent = new Intent(this, MainActivity_.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid login or password", Toast.LENGTH_SHORT).show();
        }
    }

    private Object[] isLoginValid() {
        for (String user : Config.USERS) {
            if (login.getText().toString().equals(user)) {
                return new Object[]{true, user};
            }
        }
        return new Object[]{false, null};
    }
}