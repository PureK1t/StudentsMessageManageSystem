package io.purek1t;

import java.util.Date;

public class User {
	private int adminID;    		//����ԱID���
	private String adminName;		//����Ա����
	private String adminEmail;		//����Ա����
	private Date adminCreateDate;   //����Ա����ʱ��
	private boolean adminAcvtive;   //����Ա�ڼ�״̬
	private String userName;		//����Ա��¼�û���

	
	
	
	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Date getAdminCreateDate() {
		return adminCreateDate;
	}
	public void setAdminCreateDate(Date adminCreateDate) {
		this.adminCreateDate = adminCreateDate;
	}
	
	public boolean isAdminActive() {
		return adminAcvtive;
	}
	public void setAdminActive(boolean active) {
		this.adminAcvtive = active;
	}
	public void setAdminActive(int active) {
		this.adminAcvtive = active==1;
	}


}
