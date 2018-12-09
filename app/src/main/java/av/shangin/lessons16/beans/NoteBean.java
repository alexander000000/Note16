package av.shangin.lessons16.beans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NoteBean {
    private String mHeader;

    private String mBody;

    private String mDate;


    private long mID;

    public NoteBean() {
        mHeader = "";
        mBody = "";
        mDate ="0";
        mID =-1;
    }

    public NoteBean(String header, String body) {
        mHeader = header;
        mBody = body;
        mDate = getCurrentDateInLong();
        mID =-1;
    }
    public NoteBean(String header, String body, long itemId) {
        mHeader = header;
        mBody = body;
        mDate = getCurrentDateInLong();
        mID = itemId;
    }

    public NoteBean(String header, String body, long itemId , String date) {
        mHeader = header;
        mBody = body;
        mDate = date;
        mID = itemId;
    }

    public long getID() {
        return mID;
    }

    public void setID(long ID) {
        this.mID = ID;
    }


    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }


    public static String getCurrentDateInLong() {
        long mill =(new Date()).getTime();
        return (new Long(mill)).toString();
    }

    public String getDate() {
        return mDate;
    }

    public void setCurrentDate() {

        mDate = getCurrentDateInLong();
    }

    public String getHumanDate() {

        // DD.MM.YY HH:MM
        //Example 08.12.18 18:48:ss
        long mil = Long.valueOf(mDate);
        Date currentDate = new Date(mil);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YY  HH:mm:ss");
        return (dateFormat.format( currentDate )).toString();
    }



    public String getHeader() {
        return mHeader;
    }

    public void setHeader(String header) {
        mHeader = header;
    }

    @Override
    public String toString() {
        return "NoteBean{" +
                "Header='" + mHeader + '\'' +
                ", Body='" + mBody + '\'' +
                ", Date='" + mDate + '\'' +
                '}';
    }

    public static String ToJSON(NoteBean Ob){

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String result =gson.toJson(Ob);
        //Log.i(Param.TAG, result);
        return  result;

    }

    public static NoteBean FromJSON(String param) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        NoteBean Ob = gson.fromJson(param, NoteBean.class);
        //Log.i(Param.TAG, Ob.toString());
        return Ob;
    }

    public static String[] ToJSONList(ArrayList<NoteBean> notes){
        int lenght =notes.size();
        String[] result = new String[lenght];
        for (int i = 0; i < lenght; i++){
            result[i]= NoteBean.ToJSON(notes.get(i));
        }
        //Log.i(Param.TAG, result);
        return  result;

    }

    public static ArrayList<NoteBean> FromJSONList(String[] param) {

        ArrayList<NoteBean> notes = new ArrayList<NoteBean>();

        for (int i = 0; i < param.length; i++){
           notes.add( NoteBean.FromJSON(param[i]) );
        }
        //Log.i(Param.TAG, Ob.toString());
        return notes;
    }
}
