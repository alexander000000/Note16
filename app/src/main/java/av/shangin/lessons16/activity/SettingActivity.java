package av.shangin.lessons16.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import av.shangin.lessons16.beans.SettingsBean;
import av.shangin.lessons16.R;
import av.shangin.lessons16.communication.GetSettingsReceiver;
import av.shangin.lessons16.communication.MyIntentServiceOne;
import av.shangin.lessons16.communication.ISettings;
import av.shangin.lessons16.utils.Param;

public class SettingActivity extends AppCompatActivity {
    //Setting  setting_layout
    private Button mButtonОК;
    private Intent mMainList;
    private Switch mSwitchIsBlackOnWhite;//ismIsBlackOnWhite
    private Switch mSwitchIsBigFont;//ismIsBigFont
    //private SettingsBean mSettingBin;
    private  View mItemLayout;

    // этот для получения
    private GetSettingsReceiver mMyBroadcastReceiver;
    // этот для записи


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);

        mButtonОК = findViewById(R.id.buttonОК);

        mSwitchIsBlackOnWhite = findViewById(R.id.settings_switch1);
        mSwitchIsBigFont = findViewById(R.id.settings_switch2);

        mItemLayout= findViewById(R.id.SettingLayout);

        //Запуск сервиса получение настройки
        MyIntentServiceOne.startGetActionSetting(SettingActivity.this);

        //по дефолту
        //mSwitchIsBlackOnWhite.setChecked(false);
        //mSwitchIsBigFont.setChecked(false);

        mButtonОК.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainList = new Intent(SettingActivity.this, ListItemActivity.class);


                SettingsBean mSB =new SettingsBean();
                mSB.setmIsBlackOnWhite(mSwitchIsBlackOnWhite.isChecked());
                mSB.setmIsBigFont(mSwitchIsBigFont.isChecked());
                //Запуск сервиса сохранение настройки
                Log.d(Param.NOT, "setOnClickListener setmIsBlackOnWhite="+mSB.ismIsBlackOnWhite()+" setmIsBigFont="+(mSB.ismIsBigFont()));
                MyIntentServiceOne.startSetActionSetting(SettingActivity.this,mSB);


                startActivity(mMainList);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        mMyBroadcastReceiver = new GetSettingsReceiver(new ViewCallBackSettingSecond());

        // регистрируем BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter(Param.FILTER_ACTION_GET_SETTING);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mMyBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mMyBroadcastReceiver);
    }



    public class ViewCallBackSettingSecond implements ISettings {

        public void setSetting(String param4)
        {
            Log.d(Param.NOT, "ViewCallBackSettingSecond setSetting!!!");
            if (!param4.equals("")){
                SettingsBean mSettingBin = SettingsBean.FromJSON(param4);

                if (mItemLayout!=null) mItemLayout.setBackgroundColor(mSettingBin.ismIsBlackOnWhite()==true ? Color.WHITE:Color.BLUE);
                mSwitchIsBlackOnWhite.setChecked(mSettingBin.ismIsBlackOnWhite());
                mSwitchIsBigFont.setChecked(mSettingBin.ismIsBigFont());
            }


        }
    }
}
