package cn.edu.bupt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 事务操作
 * 使用Connection对象来管理事务：
 *  1. 开启事务： setAutoCommit(boolean autoCommit) ---  调用该方法设置参数为false，即开启事务
 *               在执行sql之前开启事务
 *  2. 提交事务： commit()
 *               当所有sql都执行完提交事务
 *  3. 回滚事务： rollback()
 *               在catch中回滚事务
 */
public class JDBCDemo10 {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            //1. 获取连接
            connection = JDBCUtils.getConnection();
            //开启事务
            connection.setAutoCommit(false);

            //2. 定义sql
            //2.1 张三 - 500
            String sql1 = "update account set balance = balance - ? where id = ?";
            //2.2 李四 + 500
            String sql2 = "update account set balance = balance + ? where id = ?";
            //3. 获取执行sql的对象（PreparedStatement）
            preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement2 = connection.prepareStatement(sql2);
            //设置参数（给问号?）
            preparedStatement1.setDouble(1,500);
            preparedStatement1.setInt(2,1);

            preparedStatement2.setDouble(1,500);
            preparedStatement2.setInt(2,2);

            //执行sql语句
            preparedStatement1.executeUpdate();
            //手动制造异常
            int i = 3/0;

            preparedStatement2.executeUpdate();

            connection.commit();
        } catch (Exception e) {
            //事务回滚
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close(preparedStatement1,connection);
            JDBCUtils.close(preparedStatement2,null);
        }
    }
}
