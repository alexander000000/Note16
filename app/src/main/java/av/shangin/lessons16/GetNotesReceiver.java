package av.shangin.lessons16;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GetNotesReceiver extends BroadcastReceiver {


    private INotes mViewCallback;


    public GetNotesReceiver(INotes mViewCallback) {
        this.mViewCallback = mViewCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // : This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //Log.d(Param.TAG, "MySettingsReceiver onReceive");
        if (intent != null) {


            final String[] param4 =intent.getStringArrayExtra(Param.LOADLIST);

            //final String[] param4 = intent.getStringExtra(Param.LOADLIST);

            ArrayList<NoteBin> notes= NoteBin.FromJSONList(param4);
            //if (notes!=null) Log.d(Param.TAG, "GetNotesReceiver notes.size()="+notes.size());
            //else Log.d(Param.TAG, "GetNotesReceiver notes===null");
            this.mViewCallback.setNotes(notes);


            //throw new UnsupportedOperationException("Not yet implemented");
        }
    }

}
