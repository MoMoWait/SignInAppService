/**
 * 
 */
package cn.edu.fjnu.videoappservice.domain;

/**
 * @author GaoFei
 * ǩ����Ϣ
 *
 */
public class SignInfo {
	private int id;
	private String course;
	private int persons;
	private int startTime;
	private int endTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public int getPersons() {
		return persons;
	}
	public void setPersons(int persons) {
		this.persons = persons;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
}
