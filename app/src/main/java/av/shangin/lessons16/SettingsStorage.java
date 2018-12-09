package av.shangin.lessons16;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SettingsStorage {

    private Context mContext;

    public SettingsStorage(Context context) {
        this.mContext = context;
    }

    public SettingsBin getSb() {
        SharedPreferences pref = mContext.getSharedPreferences(Param.APP_PREFERENCES,Context.MODE_PRIVATE);

        return new SettingsBin(pref.getBoolean(Param.ISBLACKONWHITE, false),pref.getBoolean(Param.ISBIGFONT, false));
    }

    public void setSb(SettingsBin sb) {


        SharedPreferences pref = mContext.getSharedPreferences(Param.APP_PREFERENCES,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =pref.edit();
        try {
            editor.putBoolean(Param.ISBLACKONWHITE, sb.ismIsBlackOnWhite());
            editor.putBoolean(Param.ISBIGFONT, sb.ismIsBigFont());
            editor.commit();
        }
        catch (Exception e )
        {
            Log.d(Param.ERROR, "Exception: e="+e.toString());
        }

    }
}
