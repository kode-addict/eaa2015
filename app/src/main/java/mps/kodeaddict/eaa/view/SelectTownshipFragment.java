package mps.kodeaddict.eaa.view;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

import mps.kodeaddict.eaa.MainActivity;
import mps.kodeaddict.eaa.R;

/**
 * Created by sanjay on 9/25/15.
 */
public class SelectTownshipFragment extends Fragment {

    ChoseLocationActivity activity;

    Spinner township;

    Button back, done;

    List<String> list;
    List<String> pcode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.selecttownship_fragment, container, false);

        township = (Spinner) v.findViewById(R.id.township);
        back = (Button) v.findViewById(R.id.back);
        done = (Button) v.findViewById(R.id.done);

        Bundle bundle = getArguments();
        list = bundle.getStringArrayList("township");
        pcode = bundle.getStringArrayList("pcode");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        township.setAdapter(adapter);

        activity = (ChoseLocationActivity) getActivity();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.select_district.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                activity.select_township.setAlpha((float) 0.25);
                activity.fragmentManager.popBackStack();
                activity.fragement_state = "district";

            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor e = activity.getPreferences(Context.MODE_PRIVATE).edit();
                e.putBoolean("is_first",false);
                e.commit();
                startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }
        });


        return v;
    }
}
