package com.example.alessandrycruz.dummysqlitedatabase;

import com.example.alessandrycruz.dummysqlitedatabase.database.helpers.Base_Helper;
import com.example.alessandrycruz.dummysqlitedatabase.database.utils.FeedReaderContract_Util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by alessandry.cruz on 5/24/2017.
 */

@RunWith(JUnit4.class)
public class DatabaseUnitTest {
    private Base_Helper mBase_Helper;
    private FeedReaderContract_Util mFeedReaderContract_Util;

    @Test
    public void testInsertFeedEntry() throws Exception {
        // mBase_Helper = new Base_Helper(InstrumentationRegistry.getTargetContext());
        mFeedReaderContract_Util = new FeedReaderContract_Util();

        Assert.assertEquals(1, mFeedReaderContract_Util.secureInsertFeedEntry(mBase_Helper, "MyTitle", "MySubtitle"));
    }

    @Test
    public void addition_isCorrect() throws Exception {
        Assert.assertEquals(4, 2 + 2);
    }
}
