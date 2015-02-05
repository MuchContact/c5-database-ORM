package org.nightschool.mapper;

import org.apache.ibatis.session.SqlSession;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.nightschool.controller.DiskController;
import org.nightschool.model.Disk;
import org.nightschool.mybatis.DBUtil;
import org.nightschool.wrapper.MybatisWrapper;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.nightschool.wrapper.MybatisWrapper.getMapper;

/**
 * Created by Administrator on 15-2-3.
 */
public class GlobalMapperTest {

    @Test
    public void clearTable(){
        String table="disks";
        GlobalMapper mapper = getMapper(GlobalMapper.class);
        mapper.clearTable(table);
        assertThat(mapper.count(table), is(0));
    }
    @Test
    public void testCountable(){
        String table="disks";
        GlobalMapper globalMapper = getMapper(GlobalMapper.class);
        globalMapper.clearTable(table);

        DiskMapper mapper = getMapper(DiskMapper.class);
        mapper.insert(new Disk(1, "小清新光盘", "../images/disk/fancy-disk.jpg", "小清新、小文艺 35元/10张", 3.5, 3.5, 100, "twer"));
        mapper.insert(new Disk(2, "小清新光盘", "../images/disk/fancy-disk.jpg", "小清新、小文艺 35元/10张", 3.5, 3.5, 100, "twer"));

        assertThat(globalMapper.count(table), is(2));

    }
}
