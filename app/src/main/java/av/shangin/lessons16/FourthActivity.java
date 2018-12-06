package av.shangin.lessons16;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FourthActivity extends AppCompatActivity {
    //Edit item edit_item_layout
    private View mItemLayout;
    private Button mButtonUpdate;
    private Button mButtonCancel;
    private Intent mMainList;

    // этот для получения
    private GetSettingsReceiver mMyBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_item_layout);

        mItemLayout= findViewById(R.id.editLayout);
        //Запуск сервиса получение настройки
        MyIntentServiceOne.startGetActionSetting(FourthActivity.this);



        mButtonUpdate = findViewById(R.id.buttonUpdate);
        mButtonCancel = findViewById(R.id.buttonCancel);

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainList = new Intent(FourthActivity.this, MainActivity.class);

                //mMainList.putExtra(Param.POS,pos);

                startActivity(mMainList);
            }
        });

        mButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainList = new Intent(FourthActivity.this, MainActivity.class);

                //TO-DO Save in DB
                /// /mMainList.putExtra(Param.POS,pos);

                startActivity(mMainList);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        mMyBroadcastReceiver = new GetSettingsReceiver(new FourthActivity.ViewCallBackSettingFourth());

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

    public class ViewCallBackSettingFourth implements ISettings  {

        public void setSetting(String param4)
        {
            Log.d(Param.TAG, "ViewCallBackSettingFourth setSetting!!!");
            if (!param4.equals("")){
                SettingsBin mSettingBin = SettingsBin.FromJSON(param4);

                if (mItemLayout!=null) mItemLayout.setBackgroundColor(mSettingBin.ismIsBlackOnWhite()==true ? Color.WHITE:Color.BLUE);

            }


        }
    }
}
