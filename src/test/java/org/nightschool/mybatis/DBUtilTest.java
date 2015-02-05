package org.nightschool.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

public class DBUtilTest {

    @Test
    public void testGetFactory() throws Exception {
        SqlSessionFactory factory = DBUtil.getFactory();
        assertNotNull(factory);
    }
}