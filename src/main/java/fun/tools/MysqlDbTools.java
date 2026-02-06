package fun.tools;

import java.sql.*;
import java.util.ResourceBundle;

public class MysqlDbTools {
    //定义接受信息的属性
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    //使用静态代码块，在类加载时就读取配置文件信息
    static {
        //使用资源绑定器获取配置文件，properties文件名为db
        ResourceBundle bundle = ResourceBundle.getBundle("db");//不要写文件后缀
        driver=bundle.getString("ms.driver-class-name").trim();
        url=bundle.getString("ms.jdbc-url").trim();
        user=bundle.getString("ms.username").trim();
        password=bundle.getString("ms.password").trim();
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);//加载MySQL驱动
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
    public static boolean closeResource(ResultSet resultSet, PreparedStatement ps, Connection conn) {
        boolean flag = true;
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException throwables) {
            flag = false;
            throwables.printStackTrace();
        }

        try {
            if (ps != null)
                ps.close();
        } catch (SQLException throwables) {
            flag = false;
            throwables.printStackTrace();
        }

        try {
            if (conn != null)
                conn.close();
        } catch (SQLException throwables) {
            flag = false;
            throwables.printStackTrace();
        }

        return flag;
    }
}
