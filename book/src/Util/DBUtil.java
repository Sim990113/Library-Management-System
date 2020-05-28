package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.xdevapi.Statement;

public class DBUtil {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

     public static Connection getConnection(){
         try {
             return DriverManager.getConnection("jdbc:mysql://192.168.31.165:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT", "root", "kankan13");
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return null;
     }
     /** 增删改的通用方法
     * @param  String sql  要执行的sql
      * @param  Object[] obj    对象类型的数组  里面存放着 sql执行的占位符参数
      *              【student_id,name,sex,password,id】
     *                【student_id】
      *               【name，sex,password,id】
      *         Object... 可变参数
     * */
    public static boolean executeUpdate(String sql,Object... args){

        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement(sql);
            for (int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }

       /*     ps.setObject(1,s.getName());
            ps.setObject(2,s.getAge());
            ps.setObject(3,s.getId());*/

            int i = ps.executeUpdate();
            if (i>0)return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭
        }

        return false;
    }
    
    /**
     * 查询的通用方法
     * @param sql
     * @param args
     * @return
     */
    public static List<Map<String,Object>> executeQuery(String sql, Object... args) {

        try {
            /**
             * 需要将所有数据都存到List中  每一行 用一个map存放
             */
            List<Map<String, Object>> list = new ArrayList<>();
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            /**
             * 有可能有参数 查询不是所有 而是查询其中几条
             */
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            /**
             * 执行sql
             */
            ResultSet rs = ps.executeQuery();
            /**
             *获得本次查询的总列数
             */
            int count = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Map<String, Object> maps = new HashMap<>();
                for (int i = 1; i <= count; i++) {
                    /**
                     * 获得每列的字段名
                     */
                    String name = rs.getMetaData().getColumnLabel(i);
                    maps.put(name, rs.getObject(i));

                }
                /**
                 * 将每行的map存放到List中
                 */
                list.add(maps);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭的通用方法
     */
    private static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}