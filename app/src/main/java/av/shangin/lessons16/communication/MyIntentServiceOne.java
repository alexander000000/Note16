package av.shangin.lessons16.communication;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;

import av.shangin.lessons16.beans.NoteBean;
import av.shangin.lessons16.beans.SettingsBean;
import av.shangin.lessons16.utils.DBManager;
import av.shangin.lessons16.utils.GSStorage;
import av.shangin.lessons16.utils.Param;
import av.shangin.lessons16.utils.SettingsStorage;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 *
 * helper methods.
 */
public class MyIntentServiceOne extends IntentService {
    //
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    //private SettingsStorage mSettingStorage;

    public MyIntentServiceOne() {
        super(Param.IntentService);
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    //
    public static void startActionLoadList(Context context, long paramL) {
        Intent intent = new Intent(context, MyIntentServiceOne.class);
        intent.setAction(Param.ACTION_LOADLIST);

        intent.putExtra(Param.LOADLIST, paramL);
        //Log.d(Param.TAG, "startActionLoadList paramL="+paramL);
        context.startService(intent);
    }

    public static void startActionCreate(Context context, NoteBean param1) {
        Intent intent = new Intent(context, MyIntentServiceOne.class);
        intent.setAction(Param.ACTION_CREATE);

        //Если параметр null не передаем его!!!
        if (param1!=null) {

            String Result = NoteBean.ToJSON(param1);
            //Log.d(Param.TAG2, "NoteBean.ToJSON(param1) "+Result);

            intent.putExtra(Param.CREATE, Result);

        }

        context.startService(intent);
    }

    public static void startActionUpdate(Context context, NoteBean param1) {
        Intent intent = new Intent(context, MyIntentServiceOne.class);
        intent.setAction(Param.ACTION_UPDATE);

        String Result ="";
        if (param1!=null) Result = NoteBean.ToJSON(param1);

        intent.putExtra(Param.UPDATE, Result);
        context.startService(intent);
    }

    public static void startGetActionSetting(Context context) {
        Intent intent = new Intent(context, MyIntentServiceOne.class);
        intent.setAction(Param.ACTION_GETSETTING);
        context.startService(intent);
    }

    public static void startSetActionSetting(Context context, SettingsBean param1) {
        Intent intent = new Intent(context, MyIntentServiceOne.class);
        intent.setAction(Param.ACTION_SETSETTING);

        String Result ="";
        if (param1!=null) {
            Result = SettingsBean.ToJSON(param1);
        }
        // Тут выше просто провери param1 пришел или нет. Это что бы null не передавать, а пустую строку
        intent.putExtra(Param.SETTING,Result);
        context.startService(intent);
    }



    public static void startGetGS(Context context) {
        Intent intent = new Intent(context, MyIntentServiceOne.class);
        intent.setAction(Param.ACTION_GET_GS);
        context.startService(intent);
    }

    public static void startSetGS(Context context, int color) {
        Intent intent = new Intent(context, MyIntentServiceOne.class);
        intent.putExtra(Param.COLOR,color);
        intent.setAction(Param.ACTION_SET_GS);
        context.startService(intent);
    }
    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */

    //тут логика
    @Override
    protected void onHandleIntent(Intent intent) {

        //Log.d(Param.NOT, "MyIntentServiceOne. onHandleIntent");
        if (intent != null) {
            final Param.ActionEnum action = Param.getTypeAction(intent.getAction());


            switch (action) {
                case List:
                    final long param1 = intent.getLongExtra(Param.LOADLIST,-1);
                    handleActionLoadList(param1);
                    break;
                case Create:
                    final String param2 = intent.getStringExtra(Param.CREATE);
                    handleActionCreate(param2);
                    break;

                case Update:
                    final String param3 = intent.getStringExtra(Param.UPDATE);
                    handleActionUpdate(param3);
                    break;
                case GetSettings:
                    handleActionGetSetting();
                    break;
                case SetSettings:
                    final String param4 = intent.getStringExtra(Param.SETTING);
                    handleActionSetSetting(param4);
                    break;
                case GetGS:
                    handleActionGetGS();
                    break;
                case SetGS:
                    final int param0 = intent.getIntExtra(Param.COLOR,0);
                    handleActionSetGS(param0);
                    break;
                case Other:
                    Log.d(Param.TAG, "onHandleIntent Other!!!");
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */

    private void handleActionLoadList(long param1) {
        //: Handle action LoadList
        ArrayList<NoteBean> notes;// = new ArrayList<NoteBean>();

        DBManager dbManager = new DBManager(this);
        // получаю список из БД
        //Log.d(Param.TAG, "Before  getNote  param1="+param1);
        notes =dbManager.getNote(param1);
/*        if (notes!=null) {
            Log.d(Param.TAG, "handleActionLoadList notes.size()="+notes.size());
            //Log.d(Param.TAG, "get(0).getHeader()="+notes.get(0).getHeader());
        }
        else {
            Log.d(Param.TAG, "handleActionLoadList notes is NULL!");
        }*/
        Intent responseIntent = new Intent();
        responseIntent.setAction(Param.FILTER_ACTION_LOADLIST);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT);

        responseIntent.putExtra(Param.LOADLIST, NoteBean.ToJSONList(notes));

        sendBroadcast(responseIntent);


    }

    private void handleActionCreate(String param1) {
        //  Handle action Create
        NoteBean nb = NoteBean.FromJSON(param1);

        DBManager dbManager = new DBManager(this);

        dbManager.addNote(nb);

        //Log.d(Param.TAG2, "handleActionCreate: nb="+nb.toString());
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleActionUpdate(String param1) {
        //
        NoteBean nb = NoteBean.FromJSON(param1);

        DBManager dbManager = new DBManager(this);

        dbManager.updateNote(nb);
        Log.d(Param.TAG, "handleActionUpdate: nb.getHeader="+nb.getHeader());
        //throw new UnsupportedOperationException("Not yet implemented");
    }


    private void handleActionGetSetting() {
        // : Handle action Setting

        SettingsBean mSettings;
        SettingsStorage mSettingStorage = new SettingsStorage(this);

        //  типа достали из sharedPreferences
        mSettings =mSettingStorage.getSb();
        // Тут передаем результат
        Intent responseIntent = new Intent();
        responseIntent.setAction(Param.FILTER_ACTION_GET_SETTING);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT);

        responseIntent.putExtra(Param.SETTING, SettingsBean.ToJSON(mSettings));
        //Log.d(Param.NOT, "handleActionGetSetting sendBroadcast");
        sendBroadcast(responseIntent);


        //throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleActionSetSetting(String param1) {
        //Log.d(Param.NOT, "handleActionSetSetting: param1="+param1);

        if (!param1.equals("")){
            //сохраняем в sharedPreferences обновление
            SettingsBean mSettings;
            SettingsStorage mSettingStorage = new SettingsStorage(this);
            //Если не пусто
            mSettings = SettingsBean.FromJSON(param1);
            // То типа сохраняем в БД и все. Никому ничего не говорим.
            mSettingStorage.setSb(mSettings);
            //Log.d(Param.NOT, "сохранение:  ismIsBlackOnWhite=" +mSettings.ismIsBlackOnWhite()+" ismIsBigFont="+mSettings.ismIsBigFont());


        }
    }

    private void handleActionGetGS() {
        // : Handle action Setting


        GSStorage mGSStorage = new GSStorage(this);

        //  типа достали из sharedPreferences
        // Тут передаем результат
        Intent responseIntent = new Intent();
        responseIntent.setAction(Param.FILTER_ACTION_GET_GS);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT);

        responseIntent.putExtra(Param.COLOR, mGSStorage.getColor());
        //Log.d(Param.NOT, "handleActionGetSetting sendBroadcast");
        sendBroadcast(responseIntent);


        //throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleActionSetGS(int param1) {
        //Log.d(Param.NOT, "handleActionSetSetting: param1="+param1);


            //сохраняем в sharedPreferences обновление
            GSStorage mGSStorage = new GSStorage(this);
            mGSStorage.setColor(param1);

            Intent responseIntent = new Intent();
            // вызываем все равно get - что бы обновился
            responseIntent.setAction(Param.FILTER_ACTION_GET_GS);
            responseIntent.addCategory(Intent.CATEGORY_DEFAULT);

            responseIntent.putExtra(Param.COLOR, mGSStorage.getColor());
            //Log.d(Param.NOT, "handleActionGetSetting sendBroadcast");
            sendBroadcast(responseIntent);
            //Log.d(Param.NOT, "сохранение:  ismIsBlackOnWhite=" +mSettings.ismIsBlackOnWhite()+" ismIsBigFont="+mSettings.ismIsBigFont());



    }

}
