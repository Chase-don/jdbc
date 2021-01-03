package datasource.druid;

import datasource.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 使用新的工具类
 */
public class DruidUtilsTest {
    public static void main(String[] args) {
        /*
        完成添加的操作：给account表添加一条记录
         */
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1. 获取连接
            connection = JDBCUtils.getConnection();
            //2. 定义sql语句
            String sql = "insert into account values(null,?,?)";
            //3. 获取执行sql对象 -- PreparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //4. 设置参数
            preparedStatement.setNString(1,"wangwu");
            preparedStatement.setDouble(2,3000);
            //5. 执行sql语句
            int i = preparedStatement.executeUpdate();
            System.out.println(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //6. 释放资源
            JDBCUtils.close(null,preparedStatement,connection);
        }

    }
}
