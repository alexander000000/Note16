package av.shangin.lessons16.utils;

import android.graphics.Color;

class GlobalSettingBean {
    private static final GlobalSettingBean ourInstance = new GlobalSettingBean();

    private static final int COLOR0= Color.WHITE;
    private static final int COLOR1=Color.GRAY;

    private  boolean mBigFont=false;
    private  int mColorZero=0;

    static GlobalSettingBean getInstance() {
        return ourInstance;
    }

    private GlobalSettingBean() {

    }

    public  int getCurruntColor(){
        return mColorZero;
    }

    public  void setColorZero(){
        mColorZero=COLOR0;
    }

    public  void setColorOne(){
        mColorZero=COLOR1;
    }



}
