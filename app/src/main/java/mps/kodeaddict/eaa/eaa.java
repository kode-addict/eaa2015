package mps.kodeaddict.eaa;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.maepaysoh.maepaysohsdk.MaePaySohApiWrapper;
import org.maepaysoh.maepaysohsdk.PartyAPIHelper;

/**
 * Created by sanjay on 9/20/15.
 */
public class eaa extends Application {

    public static Context sContext;
    public static MaePaySohApiWrapper mps;
    public static SharedPreferences preference;

//    public static MaePaySohApiWrapper getmps() {
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        eaa.sContext = getApplicationContext();
        mps = new MaePaySohApiWrapper(sContext);
        mps.setTokenKey("3a68187f-fb28-530b-982e-25af77d18d21");
        mps.setFont(MaePaySohApiWrapper.FONT.zawgyi);
        preference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


    }

}
