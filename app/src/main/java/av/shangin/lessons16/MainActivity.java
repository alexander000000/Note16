package av.shangin.lessons16;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //MainList newlistlayout



    RecyclerView recyclerView;

    private Button mNewButton;
    private Button mSettingButton;
    private Intent mNewIntent;
    private Intent mSettingIntent;
    private GetSettingsReceiver mMyBroadcastReceiver;
    private SettingsBin mSettingBin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlistlayout);



        recyclerView = findViewById(R.id.NewMainList);
        recyclerView.setAdapter(new RecycleListAdapter());

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));




        mNewButton = findViewById(R.id.buttonNew);
        mSettingButton = findViewById(R.id.buttonSettings);

        mNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to new Item activity
                mNewIntent = new Intent(MainActivity.this, ThirdActivity.class);

                //mNewIntent.putExtra(msfString,"MainActivity1");
                startActivity(mNewIntent);
            }
        });


        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Запуск сервиса настройки без параметра, что бы достать настроки
                MyIntentServiceOne.startActionSetting(MainActivity.this,null);

                //Тут переход в другой активити
                mSettingIntent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(mSettingIntent);
            }
        });
        Log.d(Param.TAG, "onCreate MainActivity ");
    }

    @Override
    protected void onResume() {
        super.onResume();

        mMyBroadcastReceiver = new GetSettingsReceiver(new ViewCallBackSettingMain());

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



    public class ViewCallBackSettingMain implements ISettings {

        public void setSetting(String param4)
        {
             if (!param4.equals("")){
                mSettingBin = SettingsBin.FromJSON(param4);
                 Log.d(Param.TAG, "ViewCallBackSettingMain setSetting  ismIsBlackOnWhite=" +mSettingBin.ismIsBlackOnWhite()+" ismIsBigFont="+mSettingBin.ismIsBigFont());

             }


        }
    }



}
