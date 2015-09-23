package mps.kodeaddict.eaa.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mps.kodeaddict.eaa.R;

/**
 * Created by sanjay on 9/23/15.
 */
public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.changepassword_activity);

    }
}
