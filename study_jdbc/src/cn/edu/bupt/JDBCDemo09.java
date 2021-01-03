package cn.edu.bupt;

import java.sql.*;
import java.util.Scanner;

/**
 * 练习：
 *      需求：
 *          1. 通过键盘录入用户名和密码
 *          2. 判断用户是否登录成功
 */
public class JDBCDemo09 {
    public static void main(String[] args) {
        //1. 键盘录入，接收用户名和密码
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String username = scanner.next();
        System.out.println("请输入密码:");
        String pwd = scanner.next();
        //2. 调用方法
        JDBCDemo09 test = new JDBCDemo09();
        boolean login = test.login2(username, pwd);
        //3. 判断结果，输出不同语句
        if (login) {
            //登陆成功
            System.out.println("登陆成功");
        } else {
            System.out.println("用户名活密码错误");
        }
    }



    /**
     * 登录方法
     */
    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        //连接数据库判断是否登陆成功
        try {
            //1. 获取连接
            connection = JDBCUtils.getConnection();
            //2. 定义sql   -------------   这里要注意sql注入的问题！！！
            String sql = "select * from user where username = '" + username + "' and password = '" + password + "' ";
            System.out.println(sql);
            //3. 获取执行sql的对象
            statement = connection.createStatement();
            //4. 执行sql
            rs = statement.executeQuery(sql);
            //5.判断
            return rs.next(); //如果有下一行，则返回true
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,statement,connection);
        }

        return false;
    }


    /**
     * 登录方法,使用PreparedStatement实现
     *
     * 注意： 后期都会使用PreparedStatement来完成增删改查的所有操作
     *      1. 可以防止sql注入
     *      2. 效率更高
     */
    public boolean login2(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        //连接数据库判断是否登陆成功
        try {
            //1. 获取连接
            connection = JDBCUtils.getConnection();
            //2. 定义sql   -------------   这里要注意sql注入的问题！！！
            String sql = "select * from user where username = ? and password = ? ";
            //3. 获取执行sql的对象
            preparedStatement = connection.prepareStatement(sql);
            //给问号?赋值
            preparedStatement.setNString(1,username);
            preparedStatement.setNString(2,password);
            //4. 执行sql
            rs = preparedStatement.executeQuery();
            //5.判断
            return rs.next(); //如果有下一行，则返回true
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,preparedStatement,connection);
        }

        return false;

    }
}
