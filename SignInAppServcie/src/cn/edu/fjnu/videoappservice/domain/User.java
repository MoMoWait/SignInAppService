package cn.edu.fjnu.videoappservice.domain;
/**
 * 对应于数据库表user
 * @author GaoFei
 *
 */
public class User {
	private int id;
	private String user_name;
	private String password;
	private String mail;
	private int type;
	private String nick_name;
	private String head_photo;
	private String phone_number;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	
	public String getHead_photo() {
		return head_photo;
	}
	
	public void setHead_photo(String head_photo) {
		this.head_photo = head_photo;
	}
	
	public String getPhone_number() {
		return phone_number;
	}
	
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
}
