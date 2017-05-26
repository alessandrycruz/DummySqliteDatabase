package com.example.alessandrycruz.dummysqlitedatabase.database.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.example.alessandrycruz.dummysqlitedatabase.database.utils.FeedReaderContract_Util;

import java.io.File;

/**
 * Created by alessandry.cruz on 5/23/2017.
 */

public class Base_Helper extends SQLiteOpenHelper {
    public static final boolean DEBUG = true;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MainDatabase.db";
    private static final String DATABASE_FOLDER = "MVV Blue";
    private static final String DATABASE_PATH = Environment.getExternalStorageDirectory() + "/" +
            DATABASE_FOLDER;

    public Base_Helper(Context context) {
        // Creates the database in sandbox or specific path
        super(context, DEBUG ? DATABASE_PATH + "/" + DATABASE_NAME : DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FeedReaderContract_Util.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(FeedReaderContract_Util.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    public static String createDatabasePath() {
        String path = DATABASE_PATH;
        File sd = new File(path);

        if (!sd.exists()) {
            sd.mkdirs();
        }

        return path;
    }
}