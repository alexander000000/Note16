package av.shangin.lessons16;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {
    //Edit item edit_item_layout
    private View mItemLayout;
    private Button mButtonUpdate;
    private Button mButtonCancel;
    private Intent mMainList;

    private TextView mTitle;
    private TextView mBody;
    private TextView mDate;

    private NoteBin mNoteBin; //эту запись показываем и изменяем

    private GetNotesReceiver mMyNotesReceiver;

    // этот для получения
    private GetSettingsReceiver mMyBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_item_layout);

        mItemLayout= findViewById(R.id.editLayout);
        //Запуск сервиса получение настройки
        MyIntentServiceOne.startGetActionSetting(EditActivity.this);

        //savedInstanceState.getBinder()
        Intent myIntent = getIntent();
        //Получаем ID записи из параметров Intent
        long IDs= myIntent.getLongExtra(Param.POS,-1);
        //Запуск сервиса получения одного элемента
        MyIntentServiceOne.startActionLoadList(EditActivity.this,IDs);



        mButtonUpdate = findViewById(R.id.buttonUpdate);
        mButtonCancel = findViewById(R.id.buttonCancel);

        mTitle = findViewById(R.id.editHeader);
        mBody = findViewById(R.id.editBody);
        mDate = findViewById(R.id.textViewLastUpdate);


        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainList = new Intent(EditActivity.this, ListItemActivity.class);

                //mMainList.putExtra(Param.POS,pos);

                startActivity(mMainList);
            }
        });

        mButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainList = new Intent(EditActivity.this, ListItemActivity.class);

                //TO-DO Save in DB
                /// /mMainList.putExtra(Param.POS,pos);
                //Обновляем объект перед записью
                mNoteBin.setHeader(mTitle.getText().toString());
                mNoteBin.setBody(mBody.getText().toString());
                mNoteBin.setCurrentDate();
                //Запуск сервиса update
                MyIntentServiceOne.startActionUpdate(EditActivity.this,mNoteBin);

                startActivity(mMainList);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        mMyBroadcastReceiver = new GetSettingsReceiver(new EditActivity.ViewCallBackSettingFourth());

        // регистрируем BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter(Param.FILTER_ACTION_GET_SETTING);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mMyBroadcastReceiver, intentFilter);

        mMyNotesReceiver = new GetNotesReceiver(new EditActivity.ViewCallBackNoteEdit());
        // регистрируем BroadcastReceiver
        IntentFilter intentFilterNote = new IntentFilter(Param.FILTER_ACTION_LOADLIST);
        intentFilterNote.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mMyNotesReceiver, intentFilterNote);
        //Log.d(Param.TAG, "EditActivity onResume");


    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mMyBroadcastReceiver);
        unregisterReceiver(mMyNotesReceiver);
    }

    public class ViewCallBackSettingFourth implements ISettings  {

        @Override
        public void setSetting(String param4)
        {
            //Log.d(Param.TAG, "ViewCallBackSettingFourth setSetting!!!");
            if (!param4.equals("")){
                SettingsBin mSettingBin = SettingsBin.FromJSON(param4);

                if (mItemLayout!=null) mItemLayout.setBackgroundColor(mSettingBin.ismIsBlackOnWhite()==true ? Color.WHITE:Color.BLUE);

            }


        }
    }

    public class ViewCallBackNoteEdit implements INotes  {

        @Override
        public void setNotes(ArrayList<NoteBin> notes) {

            if(notes.size()>0) {
                mNoteBin = notes.get(0);
                mTitle.setText(mNoteBin.getHeader());
                mBody.setText(mNoteBin.getBody());
                mDate.setText(mNoteBin.getHumanDate());
                //Log.d(Param.TAG, "getHeader="+mNoteBin.getHeader());
            }

        }
    }

}