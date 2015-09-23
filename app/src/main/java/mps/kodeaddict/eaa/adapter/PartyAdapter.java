package mps.kodeaddict.eaa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mps.kodeaddict.eaa.R;
import mps.kodeaddict.eaa.model.CircleTransform;
import mps.kodeaddict.eaa.model.PartyModel;

/**
 * Created by sanjay on 9/21/15.
 */
public class PartyAdapter extends RecyclerView.Adapter<PartyAdapter.ViewHolder> {
    
    private Context mContext;
    private List<PartyModel> partys = new ArrayList<PartyModel>();
    
    public PartyAdapter(Context context, List<PartyModel> partys){
        this.mContext = context;
        this.partys = partys;
    }

    public void addParty(PartyModel party){
        partys.add(party);
        notifyItemInserted(partys.size() - 1);
    }

    public void addParty(int i, PartyModel party){
        partys.add(i, party);
        notifyItemInserted(i);
    }

    public void addPartys(List<PartyModel> party){
        partys.addAll(party);
        notifyItemRangeInserted(partys.size() - party.size(), partys.size() - 1);
    }

    public void removeParty(PartyModel party){
        int i = partys.indexOf(party);
        partys.remove(party);
        if(i>=0){
            notifyItemRemoved(i);
        }

    }

    public void removeParty(int i){
        partys.remove(i);
        notifyItemRemoved(i);
    }

    public void removeParty(ArrayList<PartyModel> partys){
        this.partys.removeAll(partys);
        notifyDataSetChanged();
    }

    public List<PartyModel> getPartys() {
        return partys;
    }


    @Override
    public PartyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.party_model, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PartyAdapter.ViewHolder holder, int position) {
        PartyModel part = partys.get(position);
        holder.name.setText(part.name);
        holder.members.setText("Members : " + part.members);
        holder.leadership.setText(part.leadership);
        holder.region_head.setText(part.region);
        Picasso.with(mContext).load(part.photo).resize(105, 105).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return partys.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name, members, leadership, region_head;

        public ViewHolder(View v) {
            super(v);
            img = (ImageView) v.findViewById(R.id.img);
            name = (TextView) v.findViewById(R.id.name);
            members = (TextView) v.findViewById(R.id.members);
            leadership = (TextView) v.findViewById(R.id.leadership);
            region_head = (TextView) v.findViewById(R.id.region_head);

        }
    }

}
