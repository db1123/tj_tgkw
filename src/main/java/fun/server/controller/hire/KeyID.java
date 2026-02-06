package fun.server.controller.hire;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import fun.tools.MysqlDbTools;

public class KeyID {
	
	//sql server 服务器时间
	public static Timestamp getSqlDate() throws SQLException {
		Timestamp result = Timestamp.valueOf("1900-01-01 00:00:00.000000000");
		try {

			Connection con = MysqlDbTools.getConnection(); 
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT NOW() ");
				//ResultSet rs = st.executeQuery("select getdate() as srvdate");
				if (rs.next()){
					result = rs.getTimestamp(1);
					//result = rs.getTimestamp("srvdate");
				}
				rs.close();
				st.close();
			} finally {
				con.close();
			}
		} catch (Exception e) {
			
		}
		return (result);
	}
	
	
	//My Sql 服务器时间
		public static Timestamp getMySqlDate() throws SQLException {
			Timestamp result = Timestamp.valueOf("1900-01-01 00:00:00.000000000");
			try {

				Connection con = MysqlDbTools.getConnection(); 
				try {
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT NOW() ");
					//ResultSet rs = st.executeQuery("select getdate() as srvdate");
					if (rs.next()){
						result = rs.getTimestamp(1);
						//result = rs.getTimestamp("srvdate");
					}
					rs.close();
					st.close();
				} finally {
					con.close();
				}
			} catch (Exception e) {
				
			}
			return (result);
		}
	
	/**
	 * 获取主键ID
	 * @return
	 * @throws SQLException
	 */
	public static String getKeyID() throws SQLException {
		String result = "";
		try {

			 UUID uuid = UUID.randomUUID();
			 result = uuid.toString().replaceAll("-", "");
			 //System.out.println(result);
		} catch (Exception e) {

		}

		return (result);
	}
	
	
	

}
