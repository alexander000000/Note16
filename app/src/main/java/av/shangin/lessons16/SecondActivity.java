package av.shangin.lessons16;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.AsyncListUtil;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class SecondActivity extends AppCompatActivity {
    //Setting  setting_layout
    private Button mButtonОК;
    private Intent mMainList;
    private Switch mSwitch1;//ismIsBlackOnWhite
    private Switch mSwitch2;//ismIsBigFont
    private SettingsBin mSettingBin;

    // этот для получения
    private GetSettingsReceiver mMyBroadcastReceiver;
    // этот для записи


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);

        mButtonОК = findViewById(R.id.buttonОК);

        mSwitch1= findViewById(R.id.settings_switch1);
        mSwitch2= findViewById(R.id.settings_switch2);
        //по дефолту
        mSwitch1.setChecked(false);
        mSwitch2.setChecked(false);

        mButtonОК.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainList = new Intent(SecondActivity.this, MainActivity.class);

                //Запуск сервиса настройки
                SettingsBin mSB =new SettingsBin();
                mSB.setmIsBlackOnWhite(mSwitch1.isChecked());
                mSB.setmIsBigFont(mSwitch2.isChecked());
                //Запуск сервиса настройки без параметра, что бы достать настроки
                MyIntentServiceOne.startActionSetting(SecondActivity.this,mSB);
                //Запуск сервиса для сохранения

                startActivity(mMainList);
            }
        });
        Log.d(Param.TAG, "onCreate SecondActivity ");
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



    public class ViewCallBackSettingSecond implements ISettings  {

        public void setSetting(String param4)
        {
            Log.d(Param.TAG, "SecondActivity ViewCallBackSetting setSetting!!!");
            if (!param4.equals("")){
                mSettingBin = SettingsBin.FromJSON(param4);

                mSwitch1.setChecked(mSettingBin.ismIsBlackOnWhite());
                mSwitch2.setChecked(mSettingBin.ismIsBigFont());
            }


        }
    }
}
