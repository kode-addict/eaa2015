package mps.kodeaddict.eaa.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import mps.kodeaddict.eaa.R;
import mps.kodeaddict.eaa.model.CandidateModel;
import mps.kodeaddict.eaa.model.FaqModel;

/**
 * Created by root on 9/26/15.
 */
public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.ViewHolder> {

    private Context mContext;
    private List<FaqModel> faqs = new ArrayList<FaqModel>();


    public FaqAdapter(Context context, List<FaqModel> faqs) {
        this.mContext = context;
        this.faqs = faqs;
    }

    public List<FaqModel> getFaqs() {
        return faqs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_model, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        FaqModel faq = faqs.get(position);
        holder.question.setText(faq.question);
        holder.answer.setText(faq.answer);
        holder.sec_law.setText(faq.sec_law.toString());


    }

    @Override
    public int getItemCount() {
        return faqs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView question, answer, sec_law;

        public ViewHolder(View v) {
            super(v);
            question = (TextView) v.findViewById(R.id.question);
            answer = (TextView) v.findViewById(R.id.answer);
            sec_law = (TextView) v.findViewById(R.id.sec_law);


        }
    }
}
