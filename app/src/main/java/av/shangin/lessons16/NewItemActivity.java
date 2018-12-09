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
import android.widget.TextView;

public class NewItemActivity extends AppCompatActivity {
    //New item new_item_layout

    private Button mButtonSave;
    private Intent mMainList;
    private TextView mHeader;
    private TextView mBody;
    private ViewGroup mItemLayout;

    // этот для получения
    private GetSettingsReceiver mMyBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item_layout);

        mItemLayout= findViewById(R.id.newItem);
        mHeader= findViewById(R.id.editTextHeader);
        mBody= findViewById(R.id.editTextBody);

        //Запуск сервиса получение настройки
        MyIntentServiceOne.startGetActionSetting(NewItemActivity.this);
        mButtonSave = findViewById(R.id.buttonSave);

    mButtonSave.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            mMainList = new Intent(NewItemActivity.this, ListItemActivity.class);

            //Тут заполняем объект заметку

            NoteBin nb = new NoteBin();
            nb.setHeader(mHeader.getText().toString());
            nb.setBody(mBody.getText().toString());
            nb.setCurrentDate();

            //Тут вызываем сервис для сохранения заметки
            MyIntentServiceOne.startActionCreate(NewItemActivity.this,nb);
            //mMainList.putExtra(Param.POS,pos);

            startActivity(mMainList);
        }
    });

    }

    @Override
    protected void onResume() {
        super.onResume();

        mMyBroadcastReceiver = new GetSettingsReceiver(new NewItemActivity.ViewCallBackSettingThird());

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
