package av.shangin.lessons16.communication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import av.shangin.lessons16.utils.Param;

public class GetSettingsReceiver extends BroadcastReceiver {


    private ISettings mViewCallback;


    public GetSettingsReceiver(ISettings mViewCallback) {
        this.mViewCallback = mViewCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // : This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //Log.d(Param.TAG, "MySettingsReceiver onReceive");
        if (intent != null) {

            final String param4 = intent.getStringExtra(Param.SETTING);
            //Log.d(Param.TAG, "onReceive FSettings param4 "+param4);

            this.mViewCallback.setSetting(param4);


            //throw new UnsupportedOperationException("Not yet implemented");
        }
    }


}
