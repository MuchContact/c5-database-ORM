package org.nightschool.controller;

import org.junit.*;
import org.nightschool.mapper.CartMapper;
import org.nightschool.mapper.DiskMapper;
import org.nightschool.mapper.GlobalMapper;
import org.nightschool.model.Disk;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.nightschool.wrapper.MybatisWrapper.getMapper;

/**
 * Created by Administrator on 15-2-2.
 */
public class CartControllerTest {

    private CartController cartController;
    private static String username;
    private static String token;

    private static void initDiskDataInDatabase() {
        clearTable("DISKS");
        loadDisks2Database();
    }

    private static void loadDisks2Database() {
        DiskController diskController = new DiskController();
        diskController.add(new Disk(1, "小清新光盘", "../images/disk/fancy-disk.jpg", "小清新、小文艺 35元/10张", 3.5, 3.5, 100, "twer"));
        diskController.add(new Disk(2, "婚庆光盘", "../images/disk/marriage-disk.jpg", "记录你的美好瞬间 50元/10张", 5, 5, 100, "twer"));
        diskController.add(new Disk(3, "1TB大容量光盘", "../images/disk/1TB-disk.jpg", "解放你的硬盘  100元/10张", 10, 10, 100, "twer"));
    }

    private void initCartDataInDatabase() {
        clearTable("CART");
        loadCartData2Database();
    }

    private void loadCartData2Database() {
        cartController.addToCart(1, username);
        cartController.addToCart(2, username);
        cartController.addToCart(3, username);
    }

    private static void clearTable(String table) {
        GlobalMapper mapper = getMapper(GlobalMapper.class);
        mapper.clearTable(table);
    }

    @BeforeClass
    public static void initRelatedResources() {
        username = "twer";
        token = "a1e4f";
        initDiskDataInDatabase();
    }

    @Before
    public void setUp() throws Exception {
        cartController = new CartController();
        initCartDataInDatabase();
    }

    @Test
    public void queryItemsInCart() {
        assertThat(cartController.queryAll(username, token).size(), is(3));
    }

    @Test
    public void changeItemInCart() {
        CartMapper mapper = getMapper(CartMapper.class);
        int id = mapper.queryAll(username).get(0).getId();

        assertThat(cartController.modifyQuantity(id, 20, username, token), is(1));
    }

    @Test
    public void testAddToCart() throws Exception {
        DiskMapper mapper = getMapper(DiskMapper.class);
        int id = mapper.getDisks().get(0).getId();

        assertEquals(cartController.addToCart(id, username), 1);
    }

    @Test
    public void testDeleteItemFromCart() throws Exception {
        CartMapper mapper = getMapper(CartMapper.class);
        int cartId = mapper.queryAll(username).get(0).getId();

        assertEquals(1, cartController.deleteFromCart(cartId, username, token));

    }

    @After
    public void after() {
        clearTable("CART");
    }

    @AfterClass
    public static void afterClass() {
        clearTable("disks");
    }
}
