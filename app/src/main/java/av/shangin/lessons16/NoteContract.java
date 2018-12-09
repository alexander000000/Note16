package av.shangin.lessons16;

import android.provider.BaseColumns;

public final class NoteContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private NoteContract() {}

    /* Inner class that defines the table contents */
    public static class NoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "Note";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_BODY = "body";
        public static final String COLUMN_LASTUPDATE = "lastUpdate";
    }
}