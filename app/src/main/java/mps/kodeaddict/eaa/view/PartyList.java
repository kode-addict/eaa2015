package mps.kodeaddict.eaa.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.maepaysoh.maepaysohsdk.PartyAPIHelper;
import org.maepaysoh.maepaysohsdk.models.Party;
import org.maepaysoh.maepaysohsdk.models.PartyListReturnObject;

import java.util.ArrayList;
import java.util.List;

import mps.kodeaddict.eaa.R;
import mps.kodeaddict.eaa.adapter.PartyAdapter;
import mps.kodeaddict.eaa.eaa;
import mps.kodeaddict.eaa.model.PartyModel;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sanjay on 9/21/15.
 */
public class PartyList extends AppCompatActivity {

    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView party_list;

    List<PartyModel> partys;
    PartyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.party_list);

        getSupportActionBar().hide();

        partys = new ArrayList<PartyModel>();

        party_list = (RecyclerView) this.findViewById(R.id.party_list);
        mLayoutManager = new LinearLayoutManager(this);
        party_list.setLayoutManager(mLayoutManager);

        PartyAPIHelper party_api = eaa.mps.getPartyApiHelper();

        party_api.getPartiesAsync(new Callback<PartyListReturnObject>() {
            @Override
            public void success(PartyListReturnObject partyListReturnObject, Response response) {
                partys.clear();

                for(Party p : partyListReturnObject.getData()){
                    PartyModel pm = new PartyModel();
                    pm.id = p.getPartyId();
                    pm.name = p.getPartyName();
                    pm.members = p.getMemberCount();
                    pm.leadership = "";
                    /*for(String leader: p.getLeadership()){
                        pm.leadership += leader;
                    }*/

                    for(int i = 0; i<p.getLeadership().size(); i++){
                        if(i == p.getLeadership().size()-1){
                            pm.leadership += p.getLeadership().get(i);
                        }else{
                            pm.leadership += p.getLeadership().get(i) + ", ";
                        }
                    }

                    pm.region = p.getRegion();
                    pm.headquarters = p.getHeadquarters();
                    pm.photo = p.getPartySeal();
                    partys.add(pm);

                }

                adapter = new PartyAdapter(getApplicationContext(), partys);
                party_list.setAdapter(adapter);

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplication(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
