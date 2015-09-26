package mps.kodeaddict.eaa.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import mps.kodeaddict.eaa.R;

/**
 * Created by sanjay on 9/21/15.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.login_activity);

    }

}
