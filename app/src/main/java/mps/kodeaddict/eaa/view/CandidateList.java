package mps.kodeaddict.eaa.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import mps.kodeaddict.eaa.R;
import mps.kodeaddict.eaa.adapter.CandidateAdapter;
import mps.kodeaddict.eaa.model.CandidateModel;

/**
 * Created by sanjay on 9/19/15.
 */
public class CandidateList extends AppCompatActivity {

    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView candidate_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candidate_list);
        final List<CandidateModel> can = new ArrayList<CandidateModel>();

        for (int i = 0; i < 10; i++) {
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

        }

        candidate_list = (RecyclerView) this.findViewById(R.id.candidate_list);
        mLayoutManager = new LinearLayoutManager(this);
        candidate_list.setLayoutManager(mLayoutManager);

        final CandidateAdapter adapter = new CandidateAdapter(can);

        candidate_list.setAdapter(adapter);
    }
}
