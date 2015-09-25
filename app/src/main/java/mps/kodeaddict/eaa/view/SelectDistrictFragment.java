package mps.kodeaddict.eaa.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.maepaysoh.maepaysohsdk.GeoAPIHelper;
import org.maepaysoh.maepaysohsdk.models.Geo;
import org.maepaysoh.maepaysohsdk.models.GeoReturnObject;

import java.util.ArrayList;
import java.util.List;

import mps.kodeaddict.eaa.R;
import mps.kodeaddict.eaa.eaa;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sanjay on 9/25/15.
 */
public class SelectDistrictFragment extends Fragment {

    ChoseLocationActivity activity;

    Spinner district;

    Button back, next;

    List<String> list;
    List<String> pcode;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.selectdistrict_fragment, container, false);

        district = (Spinner) v.findViewById(R.id.district);
        back = (Button) v.findViewById(R.id.back);
        next = (Button) v.findViewById(R.id.next);

        Bundle bundle = getArguments();
        list = bundle.getStringArrayList("district");
        pcode = bundle.getStringArrayList("pcode");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        district.setAdapter(adapter);

        activity = (ChoseLocationActivity) getActivity();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.select_state.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                activity.select_district.setAlpha((float) 0.25);
                activity.fragmentManager.popBackStack();

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = district.getSelectedItemPosition();
                activity.district_selected(district.getSelectedItem().toString(), pcode.get(i).toString());
            }
        });


        return v;
    }
}
