package org.nightschool.mapper;

import org.junit.*;
import org.nightschool.controller.DiskController;
import org.nightschool.model.CartItem;
import org.nightschool.model.Disk;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.nightschool.wrapper.MybatisWrapper.getMapper;

/**
 * Created by Administrator on 15-2-5.
 */
public class CartMapperTest {

    private String username="twer";

    @BeforeClass
    public static void setUp() throws Exception {
//        loadTestDisksToDatabase();

    }

    private static void loadTestDisksToDatabase() {
        DiskController diskController = new DiskController();
        diskController.add(new Disk(1, "小清新光盘", "../images/disk/fancy-disk.jpg", "小清新、小文艺 35元/10张", 3.5, 3.5, 100, "twer"));
        diskController.add(new Disk(2, "婚庆光盘", "../images/disk/marriage-disk.jpg", "记录你的美好瞬间 50元/10张", 5, 5, 100, "twer"));
        diskController.add(new Disk(3, "1TB大容量光盘", "../images/disk/1TB-disk.jpg", "解放你的硬盘  100元/10张", 10, 10, 100, "twer"));
    }

    @Test
    public void testAddable() {
        CartMapper mapper = getMapper(CartMapper.class);
        assertThat(mapper.add(1, 1, username), is(1));
    }

    @Test
    public void queryAllCartItemsForUser() {
        CartMapper mapper = getMapper(CartMapper.class);
//        mapper.add(1, 10, username);
//        mapper.add(2, 5, username);
        boolean notEmpty = mapper.queryAll(username).size() > 0 ? true : false;
        assertThat(notEmpty, is(true));
        Disk disk = mapper.getDisk(1);
        assertNotNull(disk);
        CartItem cartItem = mapper.queryAll(username).get(0);
        assertNotNull(cartItem);
        assertNotNull(cartItem.getDisk());
    }

    @AfterClass
    public static void afterClass() throws Exception {
//        getMapper(GlobalMapper.class).clearTable("cart");
//        getMapper(GlobalMapper.class).clearTable("disks");
    }
}
