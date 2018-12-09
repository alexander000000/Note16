package av.shangin.lessons16.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import av.shangin.lessons16.beans.NoteBean;
import av.shangin.lessons16.beans.SettingsBean;
import av.shangin.lessons16.R;
import av.shangin.lessons16.communication.GetNotesReceiver;
import av.shangin.lessons16.communication.GetSettingsReceiver;
import av.shangin.lessons16.communication.MyIntentServiceOne;
import av.shangin.lessons16.communication.INotes;
import av.shangin.lessons16.communication.ISettings;
import av.shangin.lessons16.utils.Param;

public class ListItemActivity extends AppCompatActivity {
    //MainList newlistlayout



    RecyclerView recyclerView;

    private Button mNewButton;
    private Button mSettingButton;
    private Intent mNewIntent;
    private Intent mSettingIntent;

    private GetSettingsReceiver mMyBroadcastReceiver;
    private GetNotesReceiver mMyNotesReceiver;

    private RecycleListAdapter mRecycleListAdapter;

    //private SettingsBean mSettingBin;
    //private View mItemLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlistlayout);//buttonBarar



        recyclerView = findViewById(R.id.NewMainList);


        mRecycleListAdapter =new RecycleListAdapter();


        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(ListItemActivity.this));//GridLayoutManager(this,2));

        recyclerView.setAdapter(mRecycleListAdapter);



        mNewButton = findViewById(R.id.buttonNew);
        mSettingButton = findViewById(R.id.buttonSettings);

        //Запуск сервиса получения списка
        MyIntentServiceOne.startActionLoadList(ListItemActivity.this,-1);

        //Запуск сервиса получение настройки
        // TO DO: Раскаментировать ниже
        MyIntentServiceOne.startGetActionSetting(ListItemActivity.this);


        mNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to new Item activity
                mNewIntent = new Intent(ListItemActivity.this, NewItemActivity.class);

                //mNewIntent.putExtra(msfString,"MainActivity1");
                startActivity(mNewIntent);
            }
        });


        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                //Тут переход в другой активити
                mSettingIntent = new Intent(ListItemActivity.this, SettingActivity.class);
                startActivity(mSettingIntent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        mMyBroadcastReceiver = new GetSettingsReceiver(new ViewCallBackSettingMain());

        // регистрируем BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter(Param.FILTER_ACTION_GET_SETTING);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mMyBroadcastReceiver, intentFilter);


        mMyNotesReceiver = new GetNotesReceiver(new ViewCallBackNotesMain());
        // регистрируем BroadcastReceiver
        IntentFilter intentFilterNote = new IntentFilter(Param.FILTER_ACTION_LOADLIST);
        intentFilterNote.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mMyNotesReceiver, intentFilterNote);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mMyBroadcastReceiver);
        unregisterReceiver(mMyNotesReceiver);
    }



    public class ViewCallBackSettingMain implements ISettings {

        public void setSetting(String param4)
        {
             if (!param4.equals("")){
                 SettingsBean mSettingBin = SettingsBean.FromJSON(param4);

                 //if (mItemLayout!=null) mItemLayout.setBackgroundColor(mSettingBin.ismIsBlackOnWhite()==true ? Color.WHITE:Color.BLUE);
                 //Тут надо передать в адаптер новую настройку!
                 //mRecycleListAdapter
                //Log.d(Param.TAG, "setSetting in LISTACtivity ismIsBlackOnWhite=" +mSettingBin.ismIsBlackOnWhite()+" ismIsBigFont="+mSettingBin.ismIsBigFont());

             }


        }
    }

    public class ViewCallBackNotesMain implements INotes {

        @Override
        public void setNotes(ArrayList<NoteBean> notes) {
            //Log.d(Param.TAG, "145 Before mRecycleListAdapter.LoadList(notes) notes.size="+notes.size());
            mRecycleListAdapter.AddItem(notes);
            mRecycleListAdapter.notifyDataSetChanged();//Это для обновления записей!!

        }
    }

}
