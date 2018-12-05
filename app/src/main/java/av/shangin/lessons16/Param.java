package av.shangin.lessons16;

public final class Param {

    //param name
    public final static String POS = "POSITION";
    public final static String LOADLIST = "LOAD";
    public final static String CREATE = "CREATE";
    public final static String UPDATE = "UPDATE";
    public final static String SETTING = "SETTING";


    //param to action
    public final static String ACTION_LOADLIST = "av.shangin.lessons16.action.LOADLIST";
    public final static String ACTION_CREATE = "av.shangin.lessons16.action.CREATE";
    public final static String ACTION_UPDATE = "av.shangin.lessons16.action.UPDATE";
    public final static String ACTION_SETTING = "av.shangin.lessons16.action.SETTING";


    //param to Receiver_action
    public final static String FILTER_ACTION_LOADLIST = "av.shangin.lessons16.action.FILTER_ACTION_LOADLIST";
    public final static String FILTER_ACTION_CREATE = "av.shangin.lessons16.action.FILTER_ACTION_CREATE";
    public final static String FILTER_ACTION_UPDATE = "av.shangin.lessons16.action.FILTER_ACTION_UPDATE";
    public final static String FILTER_ACTION_GET_SETTING = "av.shangin.lessons16.action.FILTER_ACTION_GET_SETTING";
    public final static String FILTER_ACTION_UPDATE_SETTING = "av.shangin.lessons16.action.FILTER_ACTION_UPDATE_SETTING";

    public enum ActionEnum {
        List, Update, Create, Settings,Other
    }

    // help name
    public final static String IntentService = "MyIntentServiceOne";
    public final static String TAG = "NOTE";

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

        if (ACTION_SETTING.equals(action)){
            return ActionEnum.Settings;
        }

        return ActionEnum.Other;
    }

}
