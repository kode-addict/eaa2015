package mps.kodeaddict.eaa.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import org.maepaysoh.maepaysohsdk.GeoAPIHelper;
import org.maepaysoh.maepaysohsdk.models.Geo;
import org.maepaysoh.maepaysohsdk.models.GeoReturnObject;

import java.util.ArrayList;
import java.util.List;

import mps.kodeaddict.eaa.MainActivity;
import mps.kodeaddict.eaa.R;
import mps.kodeaddict.eaa.eaa;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sanjay on 9/23/15.
 */
public class ChoseLocationActivity extends AppCompatActivity {

    SharedPreferences.Editor e;

    FragmentManager fragmentManager;
    Fragment fragment;

    TextView select_state, select_district, select_township;

    Drawable img;
    ProgressDialog dialog;

    GeoAPIHelper geoapi;

    String st, st_pcode, dt, dt_pcode;
    String fragement_state;


    public void state_selected(final String state) {
        e.putString("state", state);
        e.commit();
        st = state;

        dialog = ProgressDialog.show(this, null, Html.fromHtml("<font color='#21BA45'>Working...</font>"), true);
        dialog.setCancelable(false);

        final ArrayList<String> district = new ArrayList<String>();
        final ArrayList<String> pcode = new ArrayList<String>();

        geoapi = eaa.mps.getGeoApiHelper();
        geoapi.getLocationByRegionByAsync("", st, new Callback<GeoReturnObject>() {
            @Override
            public void success(GeoReturnObject geoReturnObject, Response response) {
                for (Geo g : geoReturnObject.getData()) {
                    district.add(g.getProperties().getDT());
                    pcode.add(g.getProperties().getDTPCODE());
                }

                dialog.dismiss();

                select_state.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
                select_district.setAlpha(1);

                fragment = new SelectDistrictFragment();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("district", district);
                bundle.putStringArrayList("pcode", pcode);
                fragment.setArguments(bundle);

                fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.enter, R.anim.exit).replace(R.id.mfragment, fragment, "district").addToBackStack("district").commit();
                fragement_state = "district";
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });


    }

    public void district_selected(String district,String dt_pcode) {
        e.putString("district", district);
        e.putString("dt_pcode", dt_pcode);
        e.commit();
        dt = district;
        this.dt_pcode = dt_pcode;

        dialog = ProgressDialog.show(this, null, Html.fromHtml("<font color='#21BA45'>Working...</font>"), true);
        dialog.setCancelable(false);

        final ArrayList<String> township = new ArrayList<String>();
        final ArrayList<String> pcode = new ArrayList<String>();
        geoapi = eaa.mps.getGeoApiHelper();
        geoapi.getLowerHouseLocationByRegion(st, dt, new Callback<GeoReturnObject>() {
            @Override
            public void success(GeoReturnObject geoReturnObject, Response response) {
                for (Geo g : geoReturnObject.getData()) {
                    township.add(g.getProperties().getTS());
                    pcode.add(g.getProperties().getTSPCODE());
                }

                dialog.dismiss();
                select_district.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);

                if (township.size() > 0) {
                    select_township.setAlpha(1);

                    fragment = new SelectTownshipFragment();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("township", township);
                    bundle.putStringArrayList("pcode", pcode);
                    fragment.setArguments(bundle);

                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.enter, R.anim.exit).replace(R.id.mfragment, fragment, "township").addToBackStack(null).commit();
                    fragement_state = "township";
                } else {
                    e.putBoolean("is_first",false);
                    e.commit();
                    startActivity(new Intent(ChoseLocationActivity.this, MainActivity.class));
                    finish();
                }


            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void township_selected(String township) {
        e.putString("township", township);
        e.commit();

        select_township.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.chooselocation_activity);

        select_state = (TextView) findViewById(R.id.select_state);
        select_district = (TextView) findViewById(R.id.select_district);
        select_township = (TextView) findViewById(R.id.select_township);

        img = getApplicationContext().getResources().getDrawable(R.drawable.mark);

        select_district.setAlpha((float) 0.25);
        select_township.setAlpha((float) 0.25);

        e = eaa.preference.edit();

        fragmentManager = getFragmentManager();

        fragment = new SelectStateFragment();
        fragmentManager.beginTransaction().replace(R.id.mfragment, fragment, "state").addToBackStack(null).commit();
        fragement_state = "state";
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (fragement_state.equals("state")) {
            /*startActivity(new Intent(ChoseLocationActivity.this, MainActivity.class));
            finish();*/
            super.onBackPressed();
        } else if (fragement_state.equals("district")) {
            select_state.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            select_district.setAlpha((float) 0.25);
            fragmentManager.popBackStack();
            fragement_state = "state";
        } else if (fragement_state.equals("township")) {
            select_district.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            select_township.setAlpha((float) 0.25);
            fragmentManager.popBackStack();
            fragement_state = "district";
        }
    }
}
