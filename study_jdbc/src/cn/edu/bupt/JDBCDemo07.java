package cn.edu.bupt;

import java.sql.*;

public class JDBCDemo07 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1. 注册驱动
            //省略
            //2. 获取数据库连接对象（Connection）
            conn = DriverManager.getConnection("jdbc:mysql:///db3", "root", "111111");
            //3. 定义sql
            String sql = "select * from account";
            //4. 获取执行sql的对象
            stmt = conn.createStatement();
            //5. 执行sql
            rs = stmt.executeQuery(sql);
            //6. 处理结果
            //6.1 让游标向下移动一行
            //判断是否有下一行
            while (rs.next()){
                //6.2 获取数据
                int id = rs.getInt(1);
                String name = rs.getString("name");
                double balance = rs.getDouble(3);
                System.out.println(id + "---" + name + "---" + balance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //7. 资源处理
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
