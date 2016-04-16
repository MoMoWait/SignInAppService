package cn.edu.fjnu.videoappservice.service.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;






import cn.edu.fjnu.videoappservice.domain.User;
import cn.edu.fjnu.videoappservice.util.DBUtils;

/**
 * @author GaoFei
 * 用户服务类
 */
public class UserService implements BaseBenService<User>{

	@Override
	public void save(User object) {
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = connection.prepareStatement("insert into user(user_name,password,mail,type,nick_name,phone_number) values(?,?,?,?,?,?)");
			preparedStatement.setString(1, object.getUser_name());
			preparedStatement.setString(2, object.getPassword());
			preparedStatement.setString(3, object.getMail());
			preparedStatement.setInt(4, object.getType());
			preparedStatement.setString(5, object.getNick_name());
			preparedStatement.setString(6, object.getPhone_number());
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		DBUtils.closeConn(preparedStatement, null, connection);
		
	}

	@Override
	public void delete(User object) {		
	}

	@Override
	public void update(User object) {
		
	}

	/**
	 * 获取所有的用户
	 */
	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			preparedStatement = connection.prepareStatement("select * from user");
			resultSet = preparedStatement.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		User user = null;
		try{
			while(resultSet.next()){
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setUser_name(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setMail(resultSet.getString(4));
				user.setType(resultSet.getInt(5));
				user.setNick_name(resultSet.getString(6));
				user.setHead_photo(resultSet.getString(7));
				user.setPhone_number(resultSet.getString(8));
				users.add(user);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		DBUtils.closeConn(preparedStatement, resultSet, connection);
	
		return users;
	}

	@Override
	public User getObjectById(Object id) {
		//List<User> users = new ArrayList<>();
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			preparedStatement = connection.prepareStatement("select * from user where id = ?");
			preparedStatement.setString(1, id.toString());
			resultSet = preparedStatement.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		User user = null;
		try{
			if(resultSet.next()){
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setUser_name(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setMail(resultSet.getString(4));
				user.setType(resultSet.getInt(5));
				user.setNick_name(resultSet.getString(6));
				user.setHead_photo(resultSet.getString(7));
				user.setPhone_number(resultSet.getString(8));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		DBUtils.closeConn(preparedStatement, resultSet, connection);
	
		return user;
	}

	/**
	 * 通过用户名获取用户信息
	 * @param user_name
	 * @return
	 */
	public User getObjectByUserName(String user_name){
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			preparedStatement = connection.prepareStatement("select * from user where user_name = ?");
			preparedStatement.setString(1, user_name);
			resultSet = preparedStatement.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		User user = null;
		try{
			if(resultSet.first()){
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setUser_name(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setMail(resultSet.getString(4));
				user.setType(resultSet.getInt(5));
				user.setNick_name(resultSet.getString(6));
				user.setHead_photo(resultSet.getString(7));
				user.setPhone_number(resultSet.getString(8));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		DBUtils.closeConn(preparedStatement, null, connection);
		return user;
	
	}
	
	
	/**
	 * 通过手机号获取用户信息
	 * @param mail
	 * @return
	 */
	public User getObjectByPhoneNumber(String phoneNumber){
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			preparedStatement = connection.prepareStatement("select * from user where phone_number = ?");
			preparedStatement.setString(1, phoneNumber);
			resultSet = preparedStatement.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		User user = null;
		try{
			if(resultSet.first()){
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setUser_name(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setMail(resultSet.getString(4));
				user.setType(resultSet.getInt(5));
				user.setNick_name(resultSet.getString(6));
				user.setHead_photo(resultSet.getString(7));
				user.setPhone_number(resultSet.getString(8));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		DBUtils.closeConn(preparedStatement, null, connection);
		return user;
	
	}
	
	
	/**
	 * 通过手机号获取用户信息
	 * @param mail
	 * @return
	 */
	public User getObjectByMail(String mail){
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			preparedStatement = connection.prepareStatement("select * from user where mail = ?");
			preparedStatement.setString(1, mail);
			resultSet = preparedStatement.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		User user = null;
		try{
			if(resultSet.first()){
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setUser_name(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setMail(resultSet.getString(4));
				user.setType(resultSet.getInt(5));
				user.setNick_name(resultSet.getString(6));
				user.setHead_photo(resultSet.getString(7));
				user.setPhone_number(resultSet.getString(8));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		DBUtils.closeConn(preparedStatement, null, connection);
		return user;
	
	}
	
	
	@Override
	public boolean isExist(User object) {
		//这里只是根据user_name和password判断
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			preparedStatement = connection.prepareStatement("select * from user where user_name = ? and password = ?");
			preparedStatement.setString(1, object.getUser_name());
			preparedStatement.setString(2, object.getPassword());
			resultSet = preparedStatement.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		boolean result = false;
		try {
			result = resultSet.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DBUtils.closeConn(preparedStatement, null, connection);
		return result;
	}

	@Override
	public void saveAll(List<User> objects) {
		
	}

	@Override
	public void saveOrUpdateAll(List<User> objects) {
		
	}
	
	/**
	 * 个人资料修改
	 */
	public void changePersonData(int id, String key, String value){
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = connection.prepareStatement("update user set " + key + " = ? where id = ?");
			//preparedStatement.setString(1, key);
			preparedStatement.setString(1, value);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		DBUtils.closeConn(preparedStatement, null, connection);
	}
	
}
