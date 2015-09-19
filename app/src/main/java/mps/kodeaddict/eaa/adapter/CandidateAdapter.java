package mps.kodeaddict.eaa.adapter;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mps.kodeaddict.eaa.R;
import mps.kodeaddict.eaa.model.CandidateModel;

/**
 * Created by sanjay on 9/19/15.
 */
public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.ViewHolder> {

    private static List<CandidateModel> candidates = new ArrayList<CandidateModel>();

    public CandidateAdapter(List<CandidateModel> candidates){
        this.candidates = candidates;
    }

    public void addCandidate(CandidateModel candidate){
        candidates.add(candidate);
        notifyItemInserted(candidates.size()-1);
    }

    public void addCandidate(int i, CandidateModel candidate){
        candidates.add(i, candidate);
        notifyItemInserted(i);
    }

    public void addCandidates(List<CandidateModel> candidate){
        candidates.addAll(candidate);
        notifyItemRangeInserted(candidates.size()-candidate.size(), candidates.size()-1);
    }

    public void removeCandidate(CandidateModel candidate){
        int i = candidates.indexOf(candidate);
        candidates.remove(candidate);
        if(i>=0){
         notifyItemRemoved(i);
        }

    }

    public void removeCandidate(int i){
        candidates.remove(i);
        notifyItemRemoved(i);
    }

    public void removeCandidate(ArrayList<CandidateModel> candidates){
        this.candidates.removeAll(candidates);
        notifyDataSetChanged();
    }

    public static List<CandidateModel> getCandidates() {
        return candidates;
    }

    @Override
    public CandidateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidate_model, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CandidateAdapter.ViewHolder holder, int position) {
        CandidateModel can = candidates.get(position);
        holder.name.setText(can.name);
        holder.age.setText(can.age+"");
        holder.education.setText(can.education);
        holder.legislature.setText(can.legislature);
        holder.party_name.setText(can.party_name_en);
        holder.ward_const.setText(can.ward_village+", "+can.const_name);

    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, age, education, legislature, party_name, ward_const;
        public ViewHolder(View v) {
            super(v);
            img = (ImageView) v.findViewById(R.id.img);
            name = (TextView) v.findViewById(R.id.name);
            age = (TextView) v.findViewById(R.id.age);
            education = (TextView) v.findViewById(R.id.education);
            legislature = (TextView) v.findViewById(R.id.legislature);
            party_name = (TextView) v.findViewById(R.id.party_name);
            ward_const = (TextView) v.findViewById(R.id.ward_const);
            img.setImageResource(R.mipmap.ic_launcher);
        }
    }
}
