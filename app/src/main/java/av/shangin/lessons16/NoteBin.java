package av.shangin.lessons16;

public class NoteBin {
    private String mHeader;

    private String mBody;

    private String mDate;


    private String mID;

    public NoteBin(String header, String body, String date) {
        mHeader = header;
        mBody = body;
        mDate = date;
        mID ="";
    }

    public NoteBin(String sID, String header, String body, String date) {
        mHeader = header;
        mBody = body;
        mDate = date;
        mID = sID;
    }

    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        this.mID = ID;
    }


    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getHeader() {
        return mHeader;
    }

    public void setHeader(String header) {
        mHeader = header;
    }

    @Override
    public String toString() {
        return "NoteBin{" +
                "Header='" + mHeader + '\'' +
                ", Body='" + mBody + '\'' +
                ", Date='" + mDate + '\'' +
                '}';
    }
}
