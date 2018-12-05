package av.shangin.lessons16;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SettingsBin {
    private  boolean mIsBlackOnWhite;
    private  boolean mIsBigFont;

    public SettingsBin(boolean mIsBlackOnWhite, boolean mIsBigFont) {
        this.mIsBlackOnWhite = mIsBlackOnWhite;
        this.mIsBigFont = mIsBigFont;
    }

    public SettingsBin() {
        this.mIsBlackOnWhite = true;
        this.mIsBigFont = false;
    }

    public boolean ismIsBlackOnWhite() {
        return mIsBlackOnWhite;
    }

    public void setmIsBlackOnWhite(boolean mIsBlackOnWhite) {
        this.mIsBlackOnWhite = mIsBlackOnWhite;
    }

    public boolean ismIsBigFont() {
        return mIsBigFont;
    }

    public void setmIsBigFont(boolean mIsBigFont) {
        this.mIsBigFont = mIsBigFont;
    }

    @Override
    public String toString() {
        return "SettingsBin{" +
                "mIsBlackOnWhite=" + mIsBlackOnWhite +
                ", mIsBigFont=" + mIsBigFont +
                '}';
    }

    public static String ToJSON(SettingsBin Ob){

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String result =gson.toJson(Ob);
        //Log.i(Param.TAG, result);
        return  result;

    }

    public static SettingsBin FromJSON(String param) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        SettingsBin Ob = gson.fromJson(param, SettingsBin.class);
        //Log.i(Param.TAG, Ob.toString());
        return Ob;
    }

}
