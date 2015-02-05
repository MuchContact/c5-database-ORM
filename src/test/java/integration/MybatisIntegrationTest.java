package integration;

import org.junit.Test;
import org.nightschool.mapper.DiskMapper;
import org.nightschool.model.Disk;
import org.nightschool.mybatis.DBUtil;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Administrator on 15-1-10.
 */
public class MybatisIntegrationTest {
    private DiskMapper getDiskMapper() throws IOException {
        return DBUtil.getFactory().openSession().getMapper(DiskMapper.class);
    }

    @Test
    public void shouldGetAllDisks() throws IOException {
        DiskMapper dm = getDiskMapper();
        List<Disk> disks = dm.getDisks();
        assertThat(disks.size(), is(52));
    }

    @Test
    public void testInsertable() throws IOException {
        DiskMapper dm = getDiskMapper();
        dm.insert(new Disk(2, "小清新光盘", "../images/disk/fancy-disk.jpg", "小清新、小文艺 35元/10张", 3.5, 3.5, 100, "twer"));
        assertThat(dm.getDisks().size(), is(53));
    }
}
