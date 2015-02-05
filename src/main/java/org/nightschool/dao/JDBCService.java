package org.nightschool.dao;

import java.sql.*;

public class JDBCService
{

    public ResultSet select(String sql) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException
    {
        Connection conn = getConnection();
        ResultSet rs = conn.createStatement().executeQuery(sql);
        conn.close();

        return rs;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/shopping_mall", "twer", "123456");
    }

    public boolean insert(String sql)
    {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.createStatement().execute(sql);
            conn.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
