package datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3p0演示(参数的验证)
 */
public class C3P0Demo02 {
    public static void main(String[] args) throws SQLException {
        //1. 获取datasource -- 括号里不传任何参数则视为使用默认配置
        DataSource ds = new ComboPooledDataSource();
        //获取连接
        for (int i = 1; i <= 11; i++) {
            Connection connection = ds.getConnection();
            System.out.println(i + ":" + connection);

            if (i == 5) {
                connection.close();//归还连接到连接池中
            }
        }


        //testNamedConfig();
    }


    public static void testNamedConfig() throws SQLException {
        //1.1 获取DataSource -- 使用指定名称配置
        DataSource ds = new ComboPooledDataSource("otherc3p0");
        //获取连接
        for (int i = 1; i <= 10; i++) {
            Connection connection = ds.getConnection();
            System.out.println(i + ":" + connection);

        }
    }

}



