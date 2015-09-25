package mps.kodeaddict.eaa.view;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import mps.kodeaddict.eaa.R;

/**
 * Created by sanjay on 9/25/15.
 */
public class SelectStateFragment extends Fragment {

    ChoseLocationActivity activity;

    Spinner state;
    Button next;

    List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.selectstate_fragment, container, false);

        state = (Spinner) v.findViewById(R.id.state);
        next = (Button) v.findViewById(R.id.next);

        Bundle bundle = getArguments();
        list = new ArrayList<String>();

        list.add("Mandalay");
        list.add("Magway");
        list.add("Rakhine");
        list.add("Kayah");
        list.add("Mon");
        list.add("Shan (North)");
        list.add("Bago West");
        list.add("Naypyitaw Conuncil");
        list.add("Shan (South)");
        list.add("Tanintharyi");
        list.add("Yangon");
        list.add("Bago East");
        list.add("Chin");
        list.add("Kachin");
        list.add("Shan (East)");
        list.add("Kayin");
        list.add("Sagaing");
        list.add("Ayeyarwady");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(adapter);

        activity = (ChoseLocationActivity) getActivity();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.state_selected(state.getSelectedItem().toString());
            }
        });


        return v;
    }
}
