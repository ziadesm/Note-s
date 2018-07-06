package com.android.echomachine.mindly.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.android.echomachine.mindly.modle.NoteModle;

import java.util.ArrayList;
import java.util.List;

public class NoteDatabase extends SQLiteOpenHelper {

    private static final String TAG = "NoteDatabase";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notedatabase";

    private static final String TABLE_NOTES = "notestable";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE = "date";
    private static final String KEY_TEXT = "noteText";
    private static final String KEY_TIME = "time";

    public NoteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String mNotesTable = "CREATE TABLE " + TABLE_NOTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_TEXT + " TEXT,"
                + KEY_TIME + " INTEGER" + ")";
        sqLiteDatabase.execSQL(mNotesTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        if (oldVer >= newVer) return;

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(sqLiteDatabase);
    }

    public int addMainNote(NoteModle items) {
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_TITLE, items.getTitle());
        cv.put(KEY_DATE, items.getDate());
        cv.put(KEY_TEXT, items.getText());
        cv.put(KEY_TIME, items.getTime());

        long insert = sql.insert(TABLE_NOTES, null, cv);
        sql.close();
        return (int) insert;
    }

    public void deleteMainNote(long id) {
        SQLiteDatabase sql = this.getWritableDatabase();
        sql.delete(TABLE_NOTES, KEY_ID + "=?", new String[]{String.valueOf(id)});
        sql.close();
    }

    public int updateMainNote(NoteModle items) {
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_TITLE, items.getTitle());
        cv.put(KEY_DATE, items.getDate());
        cv.put(KEY_TEXT, items.getText());
        cv.put(KEY_TIME, items.getTime());

        return sql.update(TABLE_NOTES, cv, KEY_ID + "=?", new String[]{String.valueOf(items.getId())});
    }

    public NoteModle getNote(int id) {
        SQLiteDatabase sql = this.getReadableDatabase();
        Cursor cursor = sql.query(TABLE_NOTES, new String[]{
                        KEY_ID,
                        KEY_TITLE,
                        KEY_DATE,
                        KEY_TEXT,
                        KEY_TIME
                }, KEY_ID + "=?"
                , new String[]{String.valueOf(id)}, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        NoteModle noteModle = new NoteModle(Integer.parseInt(cursor.getString(0))
                    , cursor.getString(1)
                    , cursor.getString(2)
                    , cursor.getString(3)
                    , cursor.getString(4));

        return noteModle;
    }

    public List<NoteModle> getAllNotes() {
        List<NoteModle> noteModles = new ArrayList<>();
        String selection = "SELECT * FROM " + TABLE_NOTES;

        SQLiteDatabase sql = this.getWritableDatabase();
        Cursor cursor = sql.rawQuery(selection, null);

        if(cursor.moveToFirst()) {
            do {
                NoteModle noteModle = new NoteModle();
                noteModle.setId(Integer.parseInt(cursor.getString(0)));
                noteModle.setTitle(cursor.getString(1));
                noteModle.setDate(cursor.getString(2));
                noteModle.setText(cursor.getString(3));
                noteModle.setTime(cursor.getString(4));

                noteModles.add(noteModle);

            } while (cursor.moveToNext());
        }
        Log.i(TAG, "Get all notes " + noteModles.size());
        return noteModles;
    }


//    public int addMiniNote(NoteModle items) {
//        SQLiteDatabase sql = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(KEY_TITLE, items.getTitle());
//        cv.put(KEY_DATE, items.getDate());
//        cv.put(KEY_TEXT, items.getText());
//        cv.put(KEY_TIME, items.getTime());
//
//        long insert = sql.insert(TABLE_NOTES, null, cv);
//        return (int) insert;
//    }


}
