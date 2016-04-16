package cn.edu.fjnu.videoappservice.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库操作类
 * @author GaoFei
 *
 */
public class DBUtils {
	/**
	 * 数据库连接
	 */
	public static Connection getConn(){
		try {
			 Connection connection = DataSource.getInstance().getConnection();
		     return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static Connection getNormalConn(){
		
	
		try {
			String url = "jdbc:mysql://112.74.77.24/video_control";
			Class.forName("com.mysql.jdbc.Driver");
			String userName = "root";
			String password = "gf6548914";
			Connection con = DriverManager.getConnection(url,userName,password);
		
			return con;
		} catch (Exception e) {
			
			return null;
		}
		
	}
	
	public static void closeConn(PreparedStatement preparedStatement, ResultSet resultSet, Connection connection) {
		if(connection != null)
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		if(resultSet != null){
			try {
				resultSet.close();
				resultSet = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(connection != null){
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
