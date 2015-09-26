package mps.kodeaddict.eaa.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.maepaysoh.maepaysohsdk.FAQAPIHelper;
import org.maepaysoh.maepaysohsdk.models.FAQ;
import org.maepaysoh.maepaysohsdk.models.FAQListReturnObject;

import java.util.ArrayList;
import java.util.List;

import mps.kodeaddict.eaa.R;
import mps.kodeaddict.eaa.adapter.FaqAdapter;
import mps.kodeaddict.eaa.eaa;
import mps.kodeaddict.eaa.model.FaqModel;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by root on 9/26/15.
 */
public class FaqActivity extends AppCompatActivity{


    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView faq_list;

    List<FaqModel> faq;
    FaqAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq_list);
        getSupportActionBar().hide();

        faq=new ArrayList<>();

        faq_list=(RecyclerView) findViewById(R.id.faq_list);
        mLayoutManager=new LinearLayoutManager(this);
        faq_list.setLayoutManager(mLayoutManager);

        final FAQAPIHelper faq_api= eaa.mps.getFaqApiHelper();

        faq_api.getFaqsAsync(new Callback<FAQListReturnObject>() {
            @Override
            public void success(FAQListReturnObject faqListReturnObject, Response response) {

                faq.clear();

                for(FAQ f: faqListReturnObject.getData()){

                    FaqModel fm=new FaqModel();
                    fm.question=f.getQuestion();
                    fm.answer=f.getAnswer();
                    fm.sec_law = f.getArticle_or_section() + " / " + f.getLaw_or_source();
                    faq.add(fm);
                }

                adapter=new FaqAdapter(getApplicationContext(),faq);
                faq_list.setAdapter(adapter);

            }

            @Override
            public void failure(RetrofitError error) {

                Log.i("Error", error.toString());
                Toast.makeText(getApplication(), error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
