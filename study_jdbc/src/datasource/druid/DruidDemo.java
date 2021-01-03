package datasource.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * Druid：数据库连接池实现技术，由阿里巴巴提供
 * 步骤：
 *      1. 导入jar包
 *      2. 定义配置文件
 *          * 是properties形式的
 *          * 可以叫任意名称，可以放在任意目录下
 *      3. 获取数据库连接池对象： 通过工厂类来获取   DruidDataSourceFactory
 *      4. 获取连接：getConnection
 */
public class DruidDemo {
    public static void main(String[] args) throws Exception {
        //1. 导入jar包
        //2. 定义配置文件
        //3. 加载配置文件
        Properties pro = new Properties();
        InputStream is = DruidDemo.class.getClassLoader().getResourceAsStream("druid.properties");
        pro.load(is);
        //4. 获取连接池对象
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);

        //5. 获取连接
        Connection connection = ds.getConnection();
        System.out.println(connection);


    }
}
