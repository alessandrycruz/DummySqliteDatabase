package com.example.alessandrycruz.dummysqlitedatabase;

import android.support.test.InstrumentationRegistry;

import com.example.alessandrycruz.dummysqlitedatabase.database.helpers.Base_Helper;
import com.example.alessandrycruz.dummysqlitedatabase.database.utils.FeedReaderContract_Util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;

/**
 * Created by alessandry.cruz on 5/24/2017.
 */

public class DatabaseUnitTest {
    private Base_Helper mBase_Helper;
    private FeedReaderContract_Util mFeedReaderContract_Util;

    @Before
    public void setUp() throws Exception {
        mBase_Helper = new Base_Helper(InstrumentationRegistry.getTargetContext());
        mFeedReaderContract_Util = new FeedReaderContract_Util();
    }

    @Test
    public void testSecureInsertFeedEntry() throws Exception {
        long newRowId = mFeedReaderContract_Util.secureInsertFeedEntry(mBase_Helper, "MyTitle", "MySubtitle");
        List<Long> lastRowId = mFeedReaderContract_Util.readFeedEntry(mBase_Helper);

        Assert.assertEquals(lastRowId.size(), newRowId);
    }

    @Test
    public void testSecureUpdateFeedEntry() throws Exception {
        long count = mFeedReaderContract_Util.secureUpdateFeedEntry(mBase_Helper, "MyTitle");

        Assert.assertThat(count, is(1L));
    }

    @Test
    public void testSecureDeleteFeedEntry() throws Exception {
        long count = mFeedReaderContract_Util.secureDeleteFeedEntry(mBase_Helper, "MyTitle");

        Assert.assertThat(count, is(1L));
    }

    @After
    public void tearDown() {
        mBase_Helper.close();
    }
}