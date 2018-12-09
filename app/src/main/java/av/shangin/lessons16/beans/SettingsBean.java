package av.shangin.lessons16.beans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SettingsBean {
    private  boolean mIsBlackOnWhite;
    private  boolean mIsBigFont;

    public SettingsBean(boolean mIsBlackOnWhite, boolean mIsBigFont) {
        this.mIsBlackOnWhite = mIsBlackOnWhite;
        this.mIsBigFont = mIsBigFont;
    }

    public SettingsBean() {
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
        return "SettingsBean{" +
                "mIsBlackOnWhite=" + mIsBlackOnWhite +
                ", mIsBigFont=" + mIsBigFont +
                '}';
    }

    public static String ToJSON(SettingsBean Ob){

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String result =gson.toJson(Ob);
        //Log.i(Param.TAG, result);
        return  result;

    }

    public static SettingsBean FromJSON(String param) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        SettingsBean Ob = gson.fromJson(param, SettingsBean.class);
        //Log.i(Param.TAG, Ob.toString());
        return Ob;
    }

}
