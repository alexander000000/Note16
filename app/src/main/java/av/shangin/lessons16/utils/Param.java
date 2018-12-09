package av.shangin.lessons16.utils;

import android.graphics.Color;

public final class Param {

    //param name
    public final static String POS = "POSITION";
    //
    public final static String LOADLIST = "LOAD";
    public final static String CREATE = "CREATE";
    public final static String UPDATE = "UPDATE";
    public final static String SETTING = "SETTING";

    public final static String COLOR = "COLOR";


    //param to action
    public final static String ACTION_LOADLIST = "av.shangin.lessons16.action.LOADLIST";
    public final static String ACTION_CREATE = "av.shangin.lessons16.action.CREATE";
    public final static String ACTION_UPDATE = "av.shangin.lessons16.action.UPDATE";
    public final static String ACTION_GETSETTING = "av.shangin.lessons16.action.SETTING";
    public final static String ACTION_SETSETTING = "av.shangin.lessons16.action.SETSETTING";

    public final static String ACTION_GET_GS = "av.shangin.lessons16.action.getGS";
    public final static String ACTION_SET_GS = "av.shangin.lessons16.action.setGS";


    //param to Receiver_action
    public final static String FILTER_ACTION_LOADLIST = "av.shangin.lessons16.action.FILTER_ACTION_LOADLIST";
    public final static String FILTER_ACTION_CREATE = "av.shangin.lessons16.action.FILTER_ACTION_CREATE";
    public final static String FILTER_ACTION_UPDATE = "av.shangin.lessons16.action.FILTER_ACTION_UPDATE";
    public final static String FILTER_ACTION_GET_SETTING = "av.shangin.lessons16.action.FILTER_ACTION_GET_SETTING";
    public final static String FILTER_ACTION_UPDATE_SETTING = "av.shangin.lessons16.action.FILTER_ACTION_UPDATE_SETTING";

    public final static String FILTER_ACTION_GET_GS = "av.shangin.lessons16.action.FILTER_ACTION_GET_GS";
    public final static String FILTER_ACTION_SET_GS = "av.shangin.lessons16.action.FILTER_ACTION_SET_GS";

    //SharedPreferences
    public static final String APP_PREFERENCES = "NoteSettings";

    public static final String ISBLACKONWHITE = "IsBlackOnWhite";
    public static final String ISBIGFONT = "IsBigFont";

    public static final int COLOR_0=Color.WHITE;
    public static final int COLOR_1=Color.GRAY;

    private static boolean mBigFont=false;
    private static boolean mColorZero=false;



    public static boolean isBigFont(){
        return mBigFont;
    }

    public static void offBigFont(){
         mBigFont=false;
    }

    public static void onBigFont(){
        mBigFont=true;
    }

    public enum ActionEnum {
        List, Update, Create, GetSettings,SetSettings,Other,GetGS,SetGS
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

        if (ACTION_GET_GS.equals(action)){
            return ActionEnum.GetGS;
        }

        if (ACTION_SET_GS.equals(action)){
            return ActionEnum.SetGS;
        }

        return ActionEnum.Other;
    }

}
