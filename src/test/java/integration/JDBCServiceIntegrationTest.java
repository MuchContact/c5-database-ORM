package integration;

import org.junit.Test;
import org.nightschool.dao.JDBCService;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JDBCServiceIntegrationTest
{

    @Test
    public void shouldGetDataFromDatabase() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
    {
        JDBCService service = new JDBCService();
        ResultSet rs = service.select("select * from item");
        rs.next();

        assertThat(rs.getString("name"), is("小清新光盘"));
    }
    @Test
    public void shouldAddableIntoDatabase() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        JDBCService service = new JDBCService();
        service.insert("INSERT INTO item(name, img_url, description, price, count) VALUES('测试'," +
                "'../images/disk/fancy-disk.jpg','小清新、小文艺 35元/10张',10,100);");
        ResultSet rs = service.select("select * from item where name='测试'");
        rs.next();
        rs.getFetchSize();

        assertThat(rs.getString("name"), is("测试"));
    }
}
