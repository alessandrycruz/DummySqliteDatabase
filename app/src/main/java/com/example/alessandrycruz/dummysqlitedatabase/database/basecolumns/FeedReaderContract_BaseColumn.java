package com.example.alessandrycruz.dummysqlitedatabase.database.basecolumns;

import android.provider.BaseColumns;

/**
 * Created by alessandry.cruz on 5/23/2017.
 */

public final class FeedReaderContract_BaseColumn {
    private FeedReaderContract_BaseColumn() {
    }

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "table_1";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }
}
