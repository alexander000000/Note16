package av.shangin.lessons16.utils;

import android.app.Application;

public class MyApplication extends Application {

    private GlobalSettingBean mGS;

    @Override
    public void onCreate() {
        super.onCreate();

        mGS =GlobalSettingBean.getInstance();


    }

    public int getCurruntColor(){
        return mGS.getCurruntColor();
    }

}
