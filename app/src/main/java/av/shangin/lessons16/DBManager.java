package av.shangin.lessons16;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private DBHelper dbHelper;

    public DBManager(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public long addNote(NoteBin nb){

        long newRowId =-1;

        SQLiteDatabase  db=null;
        try{
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            // Insert the new row, returning the primary key value of the new row
            newRowId = db.insert(NoteContract.NoteEntry.TABLE_NAME, null, getContentValues(nb));
            db.setTransactionSuccessful();
        }
        catch(Exception e) {
            Log.d(Param.DB, "Exception write in DB: e="+e.toString());
        }
        finally {
            if (db!=null){
                if(db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
        }

        return newRowId;
    }


    public long updateNote(NoteBin nb){

        long newRowId =-1;

        SQLiteDatabase  db=null;
        try{
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            // Insert the new row, returning the primary key value of the new row

            String selection = NoteContract.NoteEntry._ID + " =?";
            String Arg =Long.toString(nb.getID());
            String[] selectionArgs = {Arg};

            newRowId = db.update(
                    NoteContract.NoteEntry.TABLE_NAME,
                    getContentValuesForUpdate(nb),
                    selection,
                    selectionArgs);

            //newRowId = db.insert(NoteContract.NoteEntry.TABLE_NAME, null, getContentValues(nb));
            db.setTransactionSuccessful();
        }
        catch(Exception e) {
            Log.d(Param.DB, "Exception update in DB: e="+e.toString());
        }
        finally {
            if (db!=null){
                if(db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
        }

        return newRowId;
    }

    private ContentValues getContentValuesForUpdate(NoteBin nb) {
        ContentValues values = new ContentValues();

        values.put(NoteContract.NoteEntry.COLUMN_NAME_TITLE,nb.getHeader());
        values.put(NoteContract.NoteEntry.COLUMN_NAME_BODY, nb.getBody());
        //nb.setCurrentDate();//фиксируем дату изменения записи!! - урал, т.к. это логика должна быть в другой месте!
        values.put(NoteContract.NoteEntry.COLUMN_LASTUPDATE,nb.getDate());

        return values;
    }

    private ContentValues getContentValues(NoteBin nb) {
        ContentValues values = new ContentValues();

        values.put(NoteContract.NoteEntry.COLUMN_NAME_TITLE,nb.getHeader());
        values.put(NoteContract.NoteEntry.COLUMN_NAME_BODY, nb.getBody());
        values.put(NoteContract.NoteEntry.COLUMN_LASTUPDATE,nb.getDate());

        return values;
    }

    private ArrayList<NoteBin> parseCursor(Cursor cursor,long rowId) {
        ArrayList<NoteBin> notes= new ArrayList<NoteBin>();

        if(rowId<0) {

            int i1=cursor.getColumnIndex(NoteContract.NoteEntry._ID);
            int i2=cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_NAME_TITLE);
            int i3=cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_LASTUPDATE);

            //Log.d(Param.TAG, "befor while (cursor.moveToNext())= "+cursor.getPosition());
            while (cursor.moveToNext()) {


                long itemId = cursor.getLong(i1);
                String h =cursor.getString(i2);
                String d =cursor.getString(i3);

                NoteBin nb = new NoteBin(h,"",itemId,d);
                notes.add(nb);
                //long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(NoteContract.NoteEntry._ID));
                //String  h=cursor.getString(cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_NAME_TITLE));
                //String  b=cursor.getString(cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_NAME_BODY));
                //String d=cursor.getString(cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_LASTUPDATE));
                //notes.add(new NoteBin(h,"",itemId,ld);
                //Log.d(Param.TAG, " notes.add=="+notes.size());
            }
            //Log.d(Param.TAG, "After while (cursor.moveToNext()) notes.size=="+notes.size());
        }
        else {

            int i1=cursor.getColumnIndex(NoteContract.NoteEntry._ID);
            int i2=cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_NAME_TITLE);
            int i3=cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_LASTUPDATE);
            int i4=cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_NAME_BODY);

            if(cursor.moveToFirst()) {

                long itemId = cursor.getLong(i1);
                String h =cursor.getString(i2);
                String d =cursor.getString(i3);
                String b =cursor.getString(i4);

                NoteBin nb = new NoteBin(h,b,itemId,d);
                notes.add(nb);

                //long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(NoteContract.NoteEntry._ID));
                //String  h=cursor.getString(cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_NAME_TITLE));
                //String  b=cursor.getString(cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_NAME_BODY));
                //String d=cursor.getString(cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_LASTUPDATE));
                //notes.add(new NoteBin(h,b,itemId,d));

            }
            //Log.d(Param.TAG, "After cursor.moveToFirst()) notes.size=="+notes.size());
        }
        //Log.d(Param.TAG, "Before return notes ");
        return notes;
    }

    public ArrayList<NoteBin> getNote(long rowId) {
        ArrayList<NoteBin> notes = new ArrayList<NoteBin>();
        SQLiteDatabase db = null;

        Cursor cursor=null;

        try {
            db = dbHelper.getReadableDatabase();
            //db.beginTransaction();


            if (rowId < 0) { //return list

                // How you want the results sorted in the resulting Cursor
                String sortOrder =
                        NoteContract.NoteEntry.COLUMN_LASTUPDATE + " DESC";

                // Define a projection that specifies which columns from the database
                String[] projectionList = {
                        BaseColumns._ID,
                        NoteContract.NoteEntry.COLUMN_NAME_TITLE,
                        //NoteContract.NoteEntry.COLUMN_NAME_BODY,
                        NoteContract.NoteEntry.COLUMN_LASTUPDATE
                };

                cursor = db.query(
                        NoteContract.NoteEntry.TABLE_NAME,   // The table to query
                        projectionList,             // The array of columns to return (pass null to get all)
                        null,              // The columns for the WHERE clause
                        null,          // The values for the WHERE clause
                        null,                   // don't group the rows
                        null,                   // don't filter by row groups
                        sortOrder               // The sort order
                );
                //Log.d(Param.TAG, "getNote() rowId < 0");
            }
            else {

                // Filter results WHERE "title" = 'My Title'
                String selection = NoteContract.NoteEntry._ID + " =?";
                String Arg =Long.toString(rowId);
                String[] selectionArgs = {Arg};

                //Log.d(Param.TAG, " rowId=" + rowId+" Arg=" + Arg);
                // Define a projection that specifies which columns from the database
                String[] projection = { //return one
                        BaseColumns._ID,
                        NoteContract.NoteEntry.COLUMN_NAME_TITLE,
                        NoteContract.NoteEntry.COLUMN_NAME_BODY,
                        NoteContract.NoteEntry.COLUMN_LASTUPDATE
                };
                cursor = db.query(
                        NoteContract.NoteEntry.TABLE_NAME,   // The table to query
                        projection,             // The array of columns to return (pass null to get all)
                        selection,              // The columns for the WHERE clause
                        selectionArgs,          // The values for the WHERE clause
                        null,                   // don't group the rows
                        null,                   // don't filter by row groups
                        null               // The sort order
                );
                Log.d(Param.TAG, "getNote() rowId="+rowId+" cursor="+cursor.getCount());
            }
            //Log.d(Param.TAG, "before parseCursor cursor="+cursor.getCount());
            notes = parseCursor(cursor, rowId);
            //Log.d(Param.TAG, "after parseCursor cursor="+cursor.getCount());
            cursor.close();
            // Insert the new row, returning the primary key value of the new row
            //db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(Param.TAG, "Exception read  in DB: e=" + e.toString());
        } finally {
            if (db != null) {
                if (cursor!=null && (!cursor.isClosed()))
                {
                    cursor.close();
                }
                //if (db.inTransaction()) {
                //    db.endTransaction();
                //}
                db.close();
                //Log.d(Param.TAG, "finally!");
            }
            return notes;
        }

    }
}
