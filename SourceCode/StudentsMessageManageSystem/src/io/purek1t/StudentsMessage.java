package io.purek1t;

import java.util.Date;

public class StudentsMessage {
		private int id;    				//���
		private String studentsID;		//ѧ��
		private String name;			//����
		private String sex;				//�Ա�
		private String department;      //�༶
		private String roomID;			//�����
		private String email;			//����
	    private String phoneID;			//�ֻ���
	    private String address;			//��ַ
	    private Date startSchoolDate;	//��ѧʱ��
		private boolean active;			//�ڼ�״̬
	    
	    
		// ���ɸ��ֶζ�Ӧ��get���Ժ�set����
	    public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
	    
		public String getStudentsID() {
			return studentsID;
		}
		public void setStudentsID(String studentsID) {
			this.studentsID = studentsID;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getRoomID() {
			return roomID;
		}
		public void setRoomID(String roomID) {
			this.roomID = roomID;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhoneID() {
			return phoneID;
		}
		public void setPhoneID(String phoneID) {
			this.phoneID = phoneID;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public boolean isActive() {
			return active;
		}
		public void setActive(int active) {
			if (active==1) this.active = true;
			else this.active = false;
		}
		public void setActive(boolean isActive) {
			this.active = isActive;
		}
		public Date getStartSchoolDate() {
				return startSchoolDate;
			}
		public void setStartSchoolDate(Date startSchoolDate) {
				this.startSchoolDate = startSchoolDate;
			}
		
		
}
