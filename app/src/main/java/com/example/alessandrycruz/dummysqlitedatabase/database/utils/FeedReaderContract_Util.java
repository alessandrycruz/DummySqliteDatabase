package com.example.alessandrycruz.dummysqlitedatabase.database.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;

import com.example.alessandrycruz.dummysqlitedatabase.database.basecolumns.FeedReaderContract_BaseColumn;
import com.example.alessandrycruz.dummysqlitedatabase.database.helpers.Base_Helper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alessandry.cruz on 5/23/2017.
 */

public class FeedReaderContract_Util {
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract_BaseColumn.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract_BaseColumn.FeedEntry._ID + " INTEGER PRIMARY KEY, " +
                    FeedReaderContract_BaseColumn.FeedEntry.COLUMN_NAME_TITLE + " TEXT, " +
                    FeedReaderContract_BaseColumn.FeedEntry.COLUMN_NAME_SUBTITLE + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract_BaseColumn.FeedEntry.TABLE_NAME;

    public long insertFeedEntry(Base_Helper mBase_Helper, String title, String subtitle) {
        SQLiteDatabase db = mBase_Helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_BaseColumn.FeedEntry.COLUMN_NAME_TITLE, title);
        values.put(FeedReaderContract_BaseColumn.FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);

        long newRowId = db.insert(FeedReaderContract_BaseColumn.FeedEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    public long secureInsertFeedEntry(Base_Helper mBase_Helper, String title, String subtitle) {
        final String sql = "INSERT INTO " + FeedReaderContract_BaseColumn.FeedEntry.TABLE_NAME + "(" +
                FeedReaderContract_BaseColumn.FeedEntry.COLUMN_NAME_TITLE + ", " +
                FeedReaderContract_BaseColumn.FeedEntry.COLUMN_NAME_SUBTITLE + ") " +
                "VALUES (?1, ?2)";

        long newRowId = -1;

        SQLiteDatabase db = mBase_Helper.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);

        db.beginTransaction();

        try {
            statement.bindString(1, title);
            statement.bindString(2, subtitle);
            newRowId = statement.executeInsert();
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

        return newRowId;
    }

    public List<Long> readFeedEntry(Base_Helper mBase_Helper) {
        SQLiteDatabase db = mBase_Helper.getReadableDatabase();

        String[] projection = {
                FeedReaderContract_BaseColumn.FeedEntry._ID,
                FeedReaderContract_BaseColumn.FeedEntry.COLUMN_NAME_TITLE,
                FeedReaderContract_BaseColumn.FeedEntry.COLUMN_NAME_SUBTITLE
        };

        String selection = FeedReaderContract_BaseColumn.FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = {"MyTitle"};
        String sortOrder = FeedReaderContract_BaseColumn.FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(FeedReaderContract_BaseColumn.FeedEntry.TABLE_NAME,
                projection, selection, selectionArgs, null, null, sortOrder);

        List<Long> itemsIds = new ArrayList<>();

        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract_BaseColumn.FeedEntry._ID));
            itemsIds.add(itemId);
        }

        cursor.close();

        return itemsIds;
    }

    public int deleteFeedEntry(Base_Helper mBase_Helper) {
        SQLiteDatabase db = mBase_Helper.getWritableDatabase();
        String selection = FeedReaderContract_BaseColumn.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = {"MyTitle"};

        int count = db.delete(FeedReaderContract_BaseColumn.FeedEntry.TABLE_NAME, selection, selectionArgs);

        return count;
    }

    public int secureDeleteFeedEntry(Base_Helper mBase_Helper, String selection) {
        final String sql = "DELETE FROM " + FeedReaderContract_BaseColumn.FeedEntry.TABLE_NAME +
                " WHERE " + FeedReaderContract_BaseColumn.FeedEntry.COLUMN_NAME_TITLE + " = ?1";
        // final String selection = "MyTitle";

        int count = -1;

        SQLiteDatabase db = mBase_Helper.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);

        db.beginTransaction();

        try {
            statement.bindString(1, selection);
            count = statement.executeUpdateDelete();
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

        return count;
    }

    public int updateFeedEntry(Base_Helper mBase_Helper, String title) {
        SQLiteDatabase db = mBase_Helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract_BaseColumn.FeedEntry.COLUMN_NAME_TITLE, title);

        String selection = FeedReaderContract_BaseColumn.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = {"MyTitle"};

        int count = db.update(FeedReaderContract_BaseColumn.FeedEntry.TABLE_NAME,
                values, selection, selectionArgs);

        return count;
    }

    public int secureUpdateFeedEntry(Base_Helper mBase_Helper, String title) {
        final String sql = "UPDATE " + FeedReaderContract_BaseColumn.FeedEntry.TABLE_NAME +
                " SET " + FeedReaderContract_BaseColumn.FeedEntry.COLUMN_NAME_TITLE + " = ?1" +
                " WHERE " + FeedReaderContract_BaseColumn.FeedEntry.COLUMN_NAME_TITLE + " LIKE ?2";
        final String selection = "MyTitle";

        int count = -1;

        SQLiteDatabase db = mBase_Helper.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);

        db.beginTransaction();

        try {
            statement.bindString(1, title);
            statement.bindString(2, selection);
            count = statement.executeUpdateDelete();
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

        return count;
    }
}