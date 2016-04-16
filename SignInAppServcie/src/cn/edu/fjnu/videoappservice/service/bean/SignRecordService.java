package cn.edu.fjnu.videoappservice.service.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.fjnu.videoappservice.domain.SignRecord;
import cn.edu.fjnu.videoappservice.domain.SignUserRecord;
import cn.edu.fjnu.videoappservice.domain.User;
import cn.edu.fjnu.videoappservice.util.DBUtils;

public class SignRecordService implements BaseBenService<SignRecord>{

	@Override
	public void save(SignRecord object) {
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = connection.prepareStatement("insert into sign_record(uid,sid,signTime) values(?,?,?)");
			preparedStatement.setInt(1, object.getUid());
			preparedStatement.setInt(2, object.getSid());
			preparedStatement.setInt(3, object.getSignTime());
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		DBUtils.closeConn(preparedStatement, null, connection);
	}

	@Override
	public void delete(SignRecord object) {
		
	}

	@Override
	public void update(SignRecord object) {
		
	}

	@Override
	public List<SignRecord> getAll() {
		return null;
	}

	@Override
	public SignRecord getObjectById(Object id) {
		return null;
	}

	@Override
	public boolean isExist(SignRecord object) {
		//这里只是根据user_name和password判断
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			preparedStatement = connection.prepareStatement("select * from sign_record where uid = ? and sid = ?");
			preparedStatement.setInt(1, object.getUid());
			preparedStatement.setInt(2, object.getSid());
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
	public void saveAll(List<SignRecord> objects) {
		
	}

	@Override
	public void saveOrUpdateAll(List<SignRecord> objects) {
		
	}
	
	/**
	 * 根据签到信息的id获取签到记录信息
	 * @param sid
	 * @return
	 */
	public List<SignRecord> getAllByISid(int sid) {
		List<SignRecord> signRecords = new ArrayList<>();
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			preparedStatement = connection.prepareStatement("select * from sign_record where sid = ?");
			preparedStatement.setInt(1, sid);
			resultSet = preparedStatement.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		SignRecord signRecord = null;
		try{
			while(resultSet.next()){
				signRecord = new SignRecord();
				signRecord.setId(resultSet.getInt(1));
				signRecord.setUid(resultSet.getInt(2));
				signRecord.setSid(resultSet.getInt(3));
				signRecord.setSignTime(resultSet.getInt(4));
				signRecords.add(signRecord);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		DBUtils.closeConn(preparedStatement, resultSet, connection);
	
		return signRecords;
	}

	/**
	 * 根据签到信息的id和用户ID获取签到记录
	 * @param sid
	 * @return
	 */
	public List<SignUserRecord> getAllByUid(int uid) {
		List<SignUserRecord> signUserRecords = new ArrayList<>();
		Connection connection = DBUtils.getConn();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			preparedStatement = connection.prepareStatement("select * from sign_record,sign_info where uid = ? and sign_record.sid = sign_info.id order by signTime desc");
			preparedStatement.setInt(1, uid);
			resultSet = preparedStatement.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		SignUserRecord signUserRecord = null;
		try{
			while(resultSet.next()){
				signUserRecord = new SignUserRecord();
				signUserRecord.setId(resultSet.getInt(1));
				signUserRecord.setUid(resultSet.getInt(2));
				signUserRecord.setSid(resultSet.getInt(3));
				signUserRecord.setSignTime(resultSet.getInt(4));
				signUserRecord.setCourse(resultSet.getString(6));
				signUserRecord.setPersons(resultSet.getInt(7));
				signUserRecord.setStartTime(resultSet.getInt(8));
				signUserRecord.setEndTime(resultSet.getInt(9));
				signUserRecords.add(signUserRecord);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		DBUtils.closeConn(preparedStatement, resultSet, connection);
	
		return signUserRecords;
	}
}
