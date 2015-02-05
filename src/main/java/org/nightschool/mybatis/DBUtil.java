package org.nightschool.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 15-1-10.
 */
public class DBUtil {
    public static SqlSessionFactory factory;
    public static SqlSessionFactory getFactory() throws IOException {
        if(factory==null){
            String resource = "mybatis/conf.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
            inputStream.close();
        }
        return factory;
    }

    public static SqlSession getSqlSession() throws IOException {
        return getFactory().openSession();
    }
}
