package org.nightschool.wrapper;

import ch.qos.logback.core.joran.action.ActionUtil;
import org.apache.ibatis.session.SqlSession;
import org.nightschool.mapper.GlobalMapper;
import org.nightschool.mybatis.DBUtil;

import javax.swing.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by Administrator on 15-2-3.
 */
public class MybatisWrapper implements InvocationHandler {
    private Class cls;

    public MybatisWrapper(Class cls) {
        this.cls = cls;
    }

    public static <T> T getMapper(Class<T> cls) {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new MybatisWrapper(cls));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        SqlSession session = null;
        try {
            long selectAnnotationsNum = Arrays.asList(method.getDeclaredAnnotations()).stream().filter((one) -> (one.toString().contains("Select"))).count();
//            boolean need2Commit = selectAnnotationsNum < method.getDeclaredAnnotations().length ? true : false;
            boolean need2Commit = selectAnnotationsNum > 0 ? false : true;
            session = DBUtil.getSqlSession();
            result = method.invoke(session.getMapper(cls), args);
            if (need2Commit) {
                //TODO REPLACE PRINTLN WITH LOG4J
                System.out.println("commit to db.");
                session.commit();
            }
        } finally {
            session.close();
            //TODO REPLACE PRINTLN WITH LOG4J
            System.out.println("Close Sql Session using reflection.");
        }
        return result;
    }
}
