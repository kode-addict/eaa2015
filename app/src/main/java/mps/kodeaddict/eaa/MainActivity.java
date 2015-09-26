package mps.kodeaddict.eaa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import mps.kodeaddict.eaa.view.CandidateListActivity;
import mps.kodeaddict.eaa.view.ChangePasswordActivity;
import mps.kodeaddict.eaa.view.ChoseLocationActivity;
import mps.kodeaddict.eaa.view.FaqListActivity;
import mps.kodeaddict.eaa.view.LoginActivity;
import mps.kodeaddict.eaa.view.PartyListActivity;
import mps.kodeaddict.eaa.view.SignupActivit;

public class MainActivity extends AppCompatActivity {

    Button candidate, party, login, signup, changepass, chose, faq;

    SharedPreferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        candidate = (Button) findViewById(R.id.candidate);
        party = (Button) findViewById(R.id.party);
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        changepass = (Button) findViewById(R.id.changepass);
        chose = (Button) findViewById(R.id.chose);
        faq=(Button) findViewById(R.id.faq);


        candidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CandidateListActivity.class));
            }
        });

        party.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PartyListActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignupActivit.class));
            }
        });

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChangePasswordActivity.class));
            }
        });

        chose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChoseLocationActivity.class));
            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, FaqListActivity.class));
            }
        });

        preference = eaa.preference;

        Boolean is_first = preference.getBoolean("is_first", true);

        if(is_first){
            startActivity(new Intent(MainActivity.this, ChoseLocationActivity.class));
        }else{
            startActivity(new Intent(MainActivity.this, CandidateListActivity.class));
        }

    }

    public FloatingActionsMenu addButton(final Activity activity, FloatingActionsMenu menu){

        FloatingActionButton login = new FloatingActionButton(activity);
        login.setTitle("Login");
        login.setColorNormalResId(R.color.white);
        login.setColorPressedResId(R.color.white_pressed);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(v.getContext(), LoginActivity.class));
                activity.finish();

            }
        });
        menu.addButton(login);

        FloatingActionButton candidate = new FloatingActionButton(menu.getContext());
        candidate.setTitle("Candidate");
        candidate.setColorNormalResId(R.color.white);
        candidate.setColorPressedResId(R.color.white_pressed);
        candidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(v.getContext(), CandidateListActivity.class));
                activity.finish();
            }
        });
        menu.addButton(candidate);

        FloatingActionButton party = new FloatingActionButton(menu.getContext());
        party.setTitle("Party");
        party.setColorNormalResId(R.color.white);
        party.setColorPressedResId(R.color.white_pressed);
        party.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(v.getContext(), PartyListActivity.class));
                activity.finish();
            }
        });
        menu.addButton(party);

        FloatingActionButton faq = new FloatingActionButton(menu.getContext());
        faq.setTitle("Faq");
        faq.setColorNormalResId(R.color.white);
        faq.setColorPressedResId(R.color.white_pressed);
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(v.getContext(), FaqListActivity.class));
                activity.finish();
            }
        });
        menu.addButton(faq);

        return menu;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
