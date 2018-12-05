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

    public static void startActionSetting(Context context, SettingsBin param1) {
        Intent intent = new Intent(context, MyIntentServiceOne.class);
        intent.setAction(Param.ACTION_SETTING);

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
                case Settings:
                    final String param4 = intent.getStringExtra(Param.SETTING);
                    handleActionSetting(param4);
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

    private void handleActionSetting(String param1) {
        // TODO: Handle action Setting
        Log.d(Param.TAG, "handleActionSetting: param1="+param1);

        // Тут логика: если param1="", то просто достаем данные из БД и оповещаем
        // Иначе сохраняем в БД обновление

        SettingsBin mSettings;

        if (!param1.equals("")){
            //Если не пусто
            mSettings = SettingsBin.FromJSON(param1);
            // То типа сохраняем в БД и все. Никому ничего не говорим.
            Log.d(Param.TAG, "handleActionSetting сохранение  ismIsBlackOnWhite=" +mSettings.ismIsBlackOnWhite()+" ismIsBigFont="+mSettings.ismIsBigFont());

        }
        else {
            //Если пусто
            // то типа достали из БД
            mSettings = new SettingsBin(true,true);
            // Тут передаем результат
            Intent responseIntent = new Intent();
            responseIntent.setAction(Param.FILTER_ACTION_GET_SETTING);
            //TO-DO Подумать над категорией!!
            responseIntent.addCategory(Intent.CATEGORY_DEFAULT);

            responseIntent.putExtra(Param.SETTING, SettingsBin.ToJSON(mSettings));
            Log.d(Param.TAG, "handleActionSetting sendBroadcast");
            sendBroadcast(responseIntent);

        }

        //throw new UnsupportedOperationException("Not yet implemented");
    }

}
