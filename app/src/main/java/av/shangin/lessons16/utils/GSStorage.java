
package av.shangin.lessons16.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import av.shangin.lessons16.beans.SettingsBean;

public class GSStorage {

    public static final String GS_PREFERENCES = "NoteGS";
    public static final String GS_COLOR = "NoteGS";

    private Context mContext;

    public GSStorage(Context context) {
        this.mContext = context;
    }

    public int getColor() {
        SharedPreferences pref = mContext.getSharedPreferences(GS_PREFERENCES,Context.MODE_PRIVATE);

        return pref.getInt (GS_COLOR, 0);
    }

    public void setColor(int color) {


        SharedPreferences pref = mContext.getSharedPreferences(GS_PREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =pref.edit();
        try {
            editor.putInt(GS_COLOR,color );
            editor.commit();
        }
        catch (Exception e )
        {
            Log.d(Param.ERROR, "Exception GSStorage e="+e.toString());
        }

    }
}
