package mps.kodeaddict.eaa.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import mps.kodeaddict.eaa.R;

/**
 * Created by sanjay on 9/23/15.
 */
public class ChoseLocationActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.chooselocation_activity);
    }
}
