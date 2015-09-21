package mps.kodeaddict.eaa.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.maepaysoh.maepaysohsdk.CandidateAPIHelper;
import org.maepaysoh.maepaysohsdk.models.Candidate;
import org.maepaysoh.maepaysohsdk.models.CandidateListReturnObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class CandidateList extends AppCompatActivity {

    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView candidate_list;

    List<CandidateModel> can;
    CandidateAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.candidate_list);

        getSupportActionBar().hide();

        can = new ArrayList<CandidateModel>();

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

        final TextView aa = (TextView) findViewById(R.id.test);

        candidate_list = (RecyclerView) this.findViewById(R.id.candidate_list);
        mLayoutManager = new LinearLayoutManager(this);
        candidate_list.setLayoutManager(mLayoutManager);

        CandidateAPIHelper can_api = eaa.mps.getCandidateApiHelper();
        can_api.getCandidatesAsync(new Callback<CandidateListReturnObject>() {
            @Override
            public void success(CandidateListReturnObject candidateListReturnObject, Response response) {

                can.clear();

                for (Candidate c : candidateListReturnObject.getData()) {
                    CandidateModel cm = new CandidateModel();
                    cm.id = c.getId();
                    cm.name = c.getName();
                    cm.education = c.getEducation();
                    cm.legislature = c.getLegislature();
                    cm.ward_village = c.getWard_village();
                    cm.const_name = c.getConstituency().getName();
                    cm.photo = c.getPhotoUrl();
                    can.add(cm);
                    Log.i("Item", cm.name);
                }

                adapter = new CandidateAdapter(getApplicationContext(), can);

                candidate_list.setAdapter(adapter);

            }

            @Override
            public void failure(RetrofitError error) {
                aa.setText(error.toString());
            }
        });

    }
}
