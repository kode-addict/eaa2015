package mps.kodeaddict.eaa.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import org.maepaysoh.maepaysohsdk.CandidateAPIHelper;
import org.maepaysoh.maepaysohsdk.models.Candidate;
import org.maepaysoh.maepaysohsdk.models.CandidateListReturnObject;
import org.maepaysoh.maepaysohsdk.utils.CandidateAPIProperties;
import org.maepaysoh.maepaysohsdk.utils.CandidateAPIPropertiesMap;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mps.kodeaddict.eaa.MainActivity;
import mps.kodeaddict.eaa.R;
import mps.kodeaddict.eaa.adapter.CandidateAdapter;
import mps.kodeaddict.eaa.eaa;
import mps.kodeaddict.eaa.model.CandidateModel;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sanjay on 9/19/15.
 */
public class CandidateListActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView candidate_list;

    List<CandidateModel> candidates;
    CandidateAdapter adapter;

    ProgressDialog dialog;
    FloatingActionsMenu mfloat;
    MainActivity main;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.candidate_list);

        getSupportActionBar().hide();

        candidates = new ArrayList<CandidateModel>();

        /*for (int i = 0; i < 10; i++) {
            CandidateModel c = new CandidateModel();
            c.id = i;
            c.name = "Name";
            c.age = i;
            c.education = "Education";
            c.legislature = "Legislature";
            c.ward_village = "Village";
            c.const_name = "Constituion";
            c.party_name_en = "Party Name";
            can.add(c);

        }*/
        main = new MainActivity();
        mfloat = (FloatingActionsMenu) findViewById(R.id.mfloat);
        mfloat = main.addButton(this, mfloat);

        candidate_list = (RecyclerView) this.findViewById(R.id.candidate_list);
        mLayoutManager = new LinearLayoutManager(this);
        candidate_list.setLayoutManager(mLayoutManager);

        CandidateAPIHelper candidate_api = eaa.mps.getCandidateApiHelper();

        String dt_pcode = eaa.preference.getString("dt_pcode", "");
        CandidateAPIPropertiesMap propertiesMap = new CandidateAPIPropertiesMap();
        propertiesMap.put(CandidateAPIProperties.WITH_PARTY, true);

        dialog = ProgressDialog.show(this, null, Html.fromHtml("<font color='#21BA45'>Working...</font>"), true);
        dialog.setCancelable(true);

        candidate_api.getCandidatesAsync(dt_pcode, propertiesMap, new Callback<CandidateListReturnObject>() {
            @Override
            public void success(CandidateListReturnObject candidateListReturnObject, Response response) {

                candidates.clear();

                for (Candidate c : candidateListReturnObject.getData()) {
                    CandidateModel cm = new CandidateModel();
                    cm.id = c.getId();
                    cm.name = c.getName();
                    cm.education = c.getEducation();
                    cm.legislature = c.getLegislature();
                    cm.ward_village = c.getWardVillage();
                    cm.const_name = c.getConstituency().getName();
                    cm.photo = c.getPhotoUrl();
                    cm.occupation = c.getOccupation();
                    cm.party_name = c.getParty().getPartyName();
                    cm.ethnicity = c.getEthnicity();
                    cm.religion = c.getReligion();
                    candidates.add(cm);

                }

                adapter = new CandidateAdapter(getApplicationContext(), candidates);
                candidate_list.setAdapter(adapter);

                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Total " + adapter.getItemCount(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("Error", error.toString());
                dialog.dismiss();
                Toast.makeText(getApplication(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
