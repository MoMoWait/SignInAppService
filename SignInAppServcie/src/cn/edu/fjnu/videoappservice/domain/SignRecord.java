/**
 * 
 */
package cn.edu.fjnu.videoappservice.domain;

/**
 * Ç©µ½¼ÇÂ¼±í
 *
 */
public class SignRecord {
	private int id;
	private int uid;
	private int sid;
	private int signTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getSignTime() {
		return signTime;
	}
	public void setSignTime(int signTime) {
		this.signTime = signTime;
	}
	@Override
	public String toString() {
		return "SignRecord [id=" + id + ", uid=" + uid + ", sid=" + sid
				+ ", signTime=" + signTime + "]";
	}
	
}
