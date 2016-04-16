/**
 * 
 */
package cn.edu.fjnu.videoappservice.service.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.fjnu.videoappservice.domain.SignInfo;
import cn.edu.fjnu.videoappservice.domain.User;
import cn.edu.fjnu.videoappservice.util.DBUtils;

/**
 * Ç©µ½·þÎñ
 *
 */
public class SignInfoService implements BaseBenService<SignInfo> {

	@Override
	public void save(SignInfo object) {
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = connection.prepareStatement("insert into sign_info(course, persons, startTime, endTime) values(?,?,?,?)");
			preparedStatement.setString(1, object.getCourse());
			preparedStatement.setInt(2, object.getPersons());
			preparedStatement.setInt(3, object.getStartTime());
			preparedStatement.setInt(4, object.getEndTime());
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		DBUtils.closeConn(preparedStatement, null, connection);
		
	}

	@Override
	public void delete(SignInfo object) {
		
	}

	@Override
	public void update(SignInfo object) {
		
	}

	@Override
	public List<SignInfo> getAll() {
		List<SignInfo> signInfos = new ArrayList<>();
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			preparedStatement = connection.prepareStatement("select * from sign_info order by startTime desc");
			resultSet = preparedStatement.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		SignInfo signInfo = null;
		try{
			while(resultSet.next()){
				signInfo = new SignInfo();
				signInfo.setId(resultSet.getInt(1));
				signInfo.setCourse(resultSet.getString(2));
				signInfo.setPersons(resultSet.getInt(3));
				signInfo.setStartTime(resultSet.getInt(4));
				signInfo.setEndTime(resultSet.getInt(5));
				signInfos.add(signInfo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		DBUtils.closeConn(preparedStatement, resultSet, connection);
	
		return signInfos;
	}

	@Override
	public SignInfo getObjectById(Object id) {
		return null;
	}

	@Override
	public boolean isExist(SignInfo object) {
		return false;
	}

	@Override
	public void saveAll(List<SignInfo> objects) {
		
	}

	@Override
	public void saveOrUpdateAll(List<SignInfo> objects) {
		
	}

}
