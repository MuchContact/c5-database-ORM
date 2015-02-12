package org.nightschool.mapper;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.nightschool.wrapper.MybatisWrapper.getMapper;

/**
 * Created by Administrator on 15-2-5.
 */
public class CartMapperTest {
    @Test
    public void testAddable() {
        CartMapper mapper = getMapper(CartMapper.class);
        assertThat(mapper.add(1, 1, "twer"), is(1));
        getMapper(GlobalMapper.class).clearTable("cart");
    }

    @Test
    public void queryAllCartItemsForUser() {
        CartMapper mapper = getMapper(CartMapper.class);
        mapper.add(1, 10, "twer");
        mapper.add(2, 5, "twer");
        boolean notEmpty = mapper.queryAll("twer").size() > 0 ? true : false;
        assertThat(notEmpty, is(true));
        getMapper(GlobalMapper.class).clearTable("cart");
    }
}
