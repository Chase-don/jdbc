package cn.edu.bupt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * account表 删除一条记录
 */
public class JDBCDemo04 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //1. 注册驱动
            //省略
            //2. 获取数据库连接对象（Connection）
            conn = DriverManager.getConnection("jdbc:mysql:///db3", "root", "111111");
            //3. 定义sql
            String sql = "delete from account where id = 3";
            //4. 获取执行sql的对象
            stmt = conn.createStatement();
            //5. 执行sql
            int count = stmt.executeUpdate(sql);
            //6. 处理结果
            System.out.println(count);
            if (count > 0) {
                System.out.println("修改成功");
            } else {
                System.out.println("修改失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //7. 资源处理
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
