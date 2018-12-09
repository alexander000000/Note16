package av.shangin.lessons16.utils;

public final class Param {

    //param name
    public final static String POS = "POSITION";
    //
    public final static String LOADLIST = "LOAD";
    public final static String CREATE = "CREATE";
    public final static String UPDATE = "UPDATE";
    public final static String SETTING = "SETTING";


    //param to action
    public final static String ACTION_LOADLIST = "av.shangin.lessons16.action.LOADLIST";
    public final static String ACTION_CREATE = "av.shangin.lessons16.action.CREATE";
    public final static String ACTION_UPDATE = "av.shangin.lessons16.action.UPDATE";
    public final static String ACTION_GETSETTING = "av.shangin.lessons16.action.SETTING";
    public final static String ACTION_SETSETTING = "av.shangin.lessons16.action.SETSETTING";


    //param to Receiver_action
    public final static String FILTER_ACTION_LOADLIST = "av.shangin.lessons16.action.FILTER_ACTION_LOADLIST";
    public final static String FILTER_ACTION_CREATE = "av.shangin.lessons16.action.FILTER_ACTION_CREATE";
    public final static String FILTER_ACTION_UPDATE = "av.shangin.lessons16.action.FILTER_ACTION_UPDATE";
    public final static String FILTER_ACTION_GET_SETTING = "av.shangin.lessons16.action.FILTER_ACTION_GET_SETTING";
    public final static String FILTER_ACTION_UPDATE_SETTING = "av.shangin.lessons16.action.FILTER_ACTION_UPDATE_SETTING";

    //SharedPreferences
    public static final String APP_PREFERENCES = "NoteSettings";
    public static final String ISBLACKONWHITE = "IsBlackOnWhite";
    public static final String ISBIGFONT = "IsBigFont";


    public enum ActionEnum {
        List, Update, Create, GetSettings,SetSettings,Other
    }

    // help name
    public final static String IntentService = "MyIntentServiceOne";
    public final static String TAG = "NOTE";
    public final static String NOT = "NOT";
    public final static String TAG2 = "NOTE2";
    public final static String DB = "NOTE_DB";
    public final static String ERROR = "NOTE_ERROR";

    public static ActionEnum getTypeAction(String action){

        if (ACTION_LOADLIST.equals(action)){
            return ActionEnum.List;
        }

        if (ACTION_CREATE.equals(action)){
            return ActionEnum.Create;
        }

        if (ACTION_UPDATE.equals(action)){
            return ActionEnum.Update;
        }

        if (ACTION_GETSETTING.equals(action)){
            return ActionEnum.GetSettings;
        }

        if (ACTION_SETSETTING.equals(action)){
            return ActionEnum.SetSettings;
        }

        return ActionEnum.Other;
    }

}
