package line.modules.user.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String userName;	// 
	private String password;	// 
	private int credits;		// 
	private String lastIp;		// 
	private Date lastLoginDate;		// 
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return password;
	}
	public void setPassWord(String passWord) {
		this.password = passWord;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public String getLastIP() {
		return lastIp;
	}
	public void setLastIP(String lastIP) {
		this.lastIp = lastIP;
	}
	public Date getLastVisit() {
		return lastLoginDate;
	}
	public void setLastVisit(Date lastVisit) {
		this.lastLoginDate = lastVisit;
	}
}
