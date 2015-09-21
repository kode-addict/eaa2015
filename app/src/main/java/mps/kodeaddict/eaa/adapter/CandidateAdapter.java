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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mps.kodeaddict.eaa.R;
import mps.kodeaddict.eaa.model.CandidateModel;
import mps.kodeaddict.eaa.model.CircleTransform;

/**
 * Created by sanjay on 9/19/15.
 */
public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.ViewHolder> {

    private Context mContext;
    private List<CandidateModel> candidates = new ArrayList<CandidateModel>();

    public CandidateAdapter(Context context, List<CandidateModel> candidates){
        this.mContext = context;
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

    public List<CandidateModel> getCandidates() {
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
        holder.education.setText(can.education);
        holder.legislature.setText(can.legislature);
        holder.ward_const.setText(can.ward_village+" / "+can.const_name);
        Picasso.with(mContext).load(can.photo).transform(new CircleTransform()).resize(105, 105).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, age, education, legislature, ward_const;
        public ViewHolder(View v) {
            super(v);
            img = (ImageView) v.findViewById(R.id.img);
            name = (TextView) v.findViewById(R.id.name);
            education = (TextView) v.findViewById(R.id.education);
            legislature = (TextView) v.findViewById(R.id.legislature);
            ward_const = (TextView) v.findViewById(R.id.ward_const);
            img.setImageResource(R.mipmap.ic_launcher);
        }
    }
}
