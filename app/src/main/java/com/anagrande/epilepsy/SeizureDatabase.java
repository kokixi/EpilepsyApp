package com.anagrande.epilepsy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anagrandeescobedo on 10/18/15.
 */
public class SeizureDatabase extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "seizure.db";
    public static final String TABLE_SEIZURES = "seizures";


    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TYPE = "typeSeizure";
    public static final String COLUMN_DESCRIPTION = "description";



    public SeizureDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ATTACKS_TABLE = "CREATE TABLE " +
                TABLE_SEIZURES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_DURATION
                + " INTEGER," + COLUMN_DATE + " TEXT" + COLUMN_TYPE + " TEXT" +
                COLUMN_DESCRIPTION + " TEXT" + ")";
        db.execSQL(CREATE_ATTACKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEIZURES);
        onCreate(db);
    }
}
