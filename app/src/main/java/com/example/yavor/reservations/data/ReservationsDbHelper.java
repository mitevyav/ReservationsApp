package com.example.yavor.reservations.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yavor.reservations.data.ReservationsContract.ReservationEntry;

/**
 * Created by mitevyav
 */

public class ReservationsDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "reservations.db";

    private static final int DATABASE_VERSION = 1;

    public ReservationsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_RESERVATIONS_TABLE = "CREATE TABLE " + ReservationEntry.TABLE_NAME + " (" +
                ReservationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ReservationEntry.COLUMN_GUEST_NAME + " TEXT NOT NULL, " +
                ReservationEntry.COLUMN_NUMBER_OF_GUESTS + " INTEGER NOT NULL, " +
                ReservationEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ReservationEntry.COLUMN_EMAIL + " TEXT NOT NULL, " +
                ReservationEntry.COLUMN_PHONE_NUMBER + " TEXT NOT NULL, " +
                "); ";
        sqLiteDatabase.execSQL(SQL_CREATE_RESERVATIONS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
