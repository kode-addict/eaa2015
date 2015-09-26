package mps.kodeaddict.eaa.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

import mps.kodeaddict.eaa.MainActivity;
import mps.kodeaddict.eaa.R;

/**
 * Created by sanjay on 9/21/15.
 */
public class LoginActivity extends AppCompatActivity {

    Button login, signup;
    FloatingActionsMenu mfloat;
    MainActivity main;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.login_activity);
        main = new MainActivity();

        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        mfloat = (FloatingActionsMenu) findViewById(R.id.mfloat);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivit.class));
                finish();
            }
        });
        
        mfloat = main.addButton(this, mfloat);

    }

}
