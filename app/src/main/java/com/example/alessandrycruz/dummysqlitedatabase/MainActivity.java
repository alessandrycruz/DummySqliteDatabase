package com.example.alessandrycruz.dummysqlitedatabase;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.alessandrycruz.dummysqlitedatabase.database.helpers.Base_Helper;
import com.example.alessandrycruz.dummysqlitedatabase.database.utils.FeedReaderContract_Util;

public class MainActivity extends AppCompatActivity {
    private Base_Helper mBase_Helper;
    private SQLiteDatabase mSQLiteDatabase;
    private FeedReaderContract_Util mFeedReaderContract_Util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Base_Helper.DEBUG) {
            Base_Helper.createDatabasePath();
        }

        mBase_Helper = new Base_Helper(getApplicationContext());
        mFeedReaderContract_Util = new FeedReaderContract_Util();

        // mFeedReaderContract_Util.insertFeedEntry(mBase_Helper, "MyTitle", "MySubtitle");
        mFeedReaderContract_Util.secureInsertFeedEntry(mBase_Helper, "MyTitle", "MySubtitle");

        mFeedReaderContract_Util.readFeedEntry(mBase_Helper);

        // mFeedReaderContract_Util.updateFeedEntry(mBase_Helper, "MyTitle");
        mFeedReaderContract_Util.secureUpdateFeedEntry(mBase_Helper, "MyTitle");

        // mFeedReaderContract_Util.deleteFeedEntry(mBase_Helper);
        mFeedReaderContract_Util.secureDeleteFeedEntry(mBase_Helper, "MyTitle");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mBase_Helper.close();
        mSQLiteDatabase.close();
    }
}