package av.shangin.lessons16;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {
    //New item new_item_layout

    private Button mButtonSave;
    private Intent mMainList;
    private ViewGroup mItemLayout;

    // этот для получения
    private GetSettingsReceiver mMyBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item_layout);

        mItemLayout= findViewById(R.id.newItem);
        //mItemLayout.setBackgroundColor(Color.GREEN);
        //Запуск сервиса получение настройки
        MyIntentServiceOne.startGetActionSetting(ThirdActivity.this);
        mButtonSave = findViewById(R.id.buttonSave);

    mButtonSave.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            mMainList = new Intent(ThirdActivity.this, MainActivity.class);


            //mMainList.putExtra(Param.POS,pos);

            startActivity(mMainList);
        }
    });

    }

    @Override
    protected void onResume() {
        super.onResume();

        mMyBroadcastReceiver = new GetSettingsReceiver(new ThirdActivity.ViewCallBackSettingThird());

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

    public class ViewCallBackSettingThird implements ISettings  {

        public void setSetting(String param4)
        {
            Log.d(Param.TAG, "ViewCallBackSettingThird setSetting!!!");
            if (!param4.equals("")){
                SettingsBin mSettingBin = SettingsBin.FromJSON(param4);

                if (mItemLayout!=null){

                    mItemLayout.setBackgroundColor(mSettingBin.ismIsBlackOnWhite()==true ? Color.WHITE:Color.BLUE);
                }


            }


        }
    }




}
