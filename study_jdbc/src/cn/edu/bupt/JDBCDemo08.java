package cn.edu.bupt;

import cn.edu.nupt.emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 定义一个方法，查询emp表的数据将其封装为对象，然后装载集合，返回。
 */
public class JDBCDemo08 {

    public static void main(String[] args) {
        JDBCDemo08 test = new JDBCDemo08();
        List<emp> list = test.findAll2();
        System.out.println(list);
        for (emp e: list) {
            System.out.println(e);
        }
        System.out.println(list.size());
    }

    /**
     * 查询所有emp对象
     * @return
     */
    public List<emp> findAll() {
        List<emp> list = new ArrayList<emp>();//写在try里面没法被外面获取到
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            //1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2. 获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///db3","root","111111");
            //3. 定义mysql
            String sql = "select * from emp";
            //4. 获取执行sql的对象
            stmt = conn.createStatement();
            //5. 执行sql
            rs = stmt.executeQuery(sql);
            //6.处理结果，遍历结果集，封装对象，装载集合
            emp e = null;

            while (rs.next()) {
                //获取数据
                int id = rs.getInt("id");
                String ename = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate"); //父类类型接收子类对象，sql.Date是util.Date的子类
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");
                //创建emp对象，并赋值
                e = new emp(id,ename,job_id,mgr,joindate,salary,bonus,dept_id);

                //装载集合
                list.add(e);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
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

        return list;
    }



    /**
     * 演示JDBC工具类
     * @return
     */
    public List<emp> findAll2() {
        List<emp> list = new ArrayList<emp>();//写在try里面没法被外面获取到
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            //1. 注册驱动（JDBCUtils的static块中自动执行）
            //2. 获取连接对象
            conn = JDBCUtils.getConnection();
            //3. 定义mysql
            String sql = "select * from emp";
            //4. 获取执行sql的对象
            stmt = conn.createStatement();
            //5. 执行sql
            rs = stmt.executeQuery(sql);
            //6.处理结果，遍历结果集，封装对象，装载集合
            emp e = null;

            while (rs.next()) {
                //获取数据
                int id = rs.getInt("id");
                String ename = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate"); //父类类型接收子类对象，sql.Date是util.Date的子类
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");
                //创建emp对象，并赋值
                e = new emp(id,ename,job_id,mgr,joindate,salary,bonus,dept_id);

                //装载集合
                list.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,stmt,conn);
        }

        return list;
    }
}
