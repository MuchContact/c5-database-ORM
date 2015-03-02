package org.nightschool.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nightschool.model.CartItem;
import org.nightschool.model.Disk;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Administrator on 15-2-14.
 */
public class CartDaoTest {
    @Before
    public void setUp() throws Exception {
//        CartMapperTest.setUp();

    }

    @Test
    public void testJoinQuery_JoinDiskFromCart() throws Exception {
        CartDao cartDao = new CartDao();
        List<CartItem> cartItemList = cartDao.cartList();
        assertNotNull(cartItemList);
        assertNotNull(cartItemList.get(0));
        Disk disk = cartItemList.get(0).getDisk();
        assertNotNull(disk.getId());
    }

    @After
    public void tearDown() throws Exception {
//        CartMapperTest.afterClass();

    }
}
