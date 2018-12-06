package av.shangin.lessons16;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

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
    public static void startActionLoadList(Context context, String param1) {
        Intent intent = new Intent(context, MyIntentServiceOne.class);
        intent.setAction(Param.ACTION_LOADLIST);
        intent.putExtra(Param.LOADLIST, param1);
        context.startService(intent);
    }

    public static void startActionCreate(Context context, NoteBin param1) {
        Intent intent = new Intent(context, MyIntentServiceOne.class);
        intent.setAction(Param.ACTION_CREATE);
        String Result ="";
        if (param1!=null) Result =param1.toString();

        intent.putExtra(Param.CREATE, Result);
        context.startService(intent);
    }

    public static void startActionUpdate(Context context, NoteBin param1) {
        Intent intent = new Intent(context, MyIntentServiceOne.class);
        intent.setAction(Param.ACTION_UPDATE);

        String Result ="";
        if (param1!=null) Result =param1.toString();

        intent.putExtra(Param.UPDATE, Result);
        context.startService(intent);
    }

    public static void startGetActionSetting(Context context) {
        Intent intent = new Intent(context, MyIntentServiceOne.class);
        intent.setAction(Param.ACTION_GETSETTING);
        context.startService(intent);
    }

    public static void startSetActionSetting(Context context, SettingsBin param1) {
        Intent intent = new Intent(context, MyIntentServiceOne.class);
        intent.setAction(Param.ACTION_SETSETTING);

        String Result ="";
        if (param1!=null) {
            Result = SettingsBin.ToJSON(param1);
        }
        // Тут выше просто провери param1 пришел или нет. Это что бы null не передавать, а пустую строку
        intent.putExtra(Param.SETTING,Result);
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

        Log.d(Param.TAG, "MyIntentServiceOne. onHandleIntent");
        if (intent != null) {
            final Param.ActionEnum action = Param.getTypeAction(intent.getAction());


            switch (action) {
                case List:
                    final String param1 = intent.getStringExtra(Param.LOADLIST);
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
                case Other:
                    Log.d(Param.TAG, "onHandleIntent Other!!!");
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */

    private void handleActionLoadList(String param1) {
        // TODO: Handle action LoadList
        Log.d(Param.TAG, "handleActionLoadList: param1="+param1);
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleActionCreate(String param1) {
        // TODO: Handle action Create
        Log.d(Param.TAG, "handleActionCreate: param1="+param1);
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleActionUpdate(String param1) {
        // TODO: Handle action Update
        Log.d(Param.TAG, "handleActionUpdate: param1="+param1);
        //throw new UnsupportedOperationException("Not yet implemented");
    }


    private void handleActionGetSetting() {
        // : Handle action Setting

        SettingsBin mSettings;
        SettingsStorage mSettingStorage = new SettingsStorage(this);

        //  типа достали из sharedPreferences
        mSettings =mSettingStorage.getSb();
        // Тут передаем результат
        Intent responseIntent = new Intent();
        responseIntent.setAction(Param.FILTER_ACTION_GET_SETTING);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT);

        responseIntent.putExtra(Param.SETTING, SettingsBin.ToJSON(mSettings));
        Log.d(Param.TAG, "handleActionGetSetting sendBroadcast");
        sendBroadcast(responseIntent);


        //throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleActionSetSetting(String param1) {
        Log.d(Param.TAG, "handleActionSetSetting: param1="+param1);

        if (!param1.equals("")){
            //сохраняем в sharedPreferences обновление
            SettingsBin mSettings;
            SettingsStorage mSettingStorage = new SettingsStorage(this);
            //Если не пусто
            mSettings = SettingsBin.FromJSON(param1);
            // То типа сохраняем в БД и все. Никому ничего не говорим.
            mSettingStorage.setSb(mSettings);
            Log.d(Param.TAG, "сохранение:  ismIsBlackOnWhite=" +mSettings.ismIsBlackOnWhite()+" ismIsBigFont="+mSettings.ismIsBigFont());

 /*           // Тут передаем результат
            Intent responseIntent = new Intent();
            responseIntent.setAction(Param.FILTER_ACTION_GET_SETTING);
            //TO-DO Подумать над категорией!!
            responseIntent.addCategory(Intent.CATEGORY_DEFAULT);

            responseIntent.putExtra(Param.SETTING, SettingsBin.ToJSON(mSettings));
            Log.d(Param.TAG, "handleActionSetting sendBroadcast");
            sendBroadcast(responseIntent);*/

        }


        //throw new UnsupportedOperationException("Not yet implemented");
    }

}
