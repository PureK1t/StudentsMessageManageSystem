package io.purek1t;

import java.sql.*;



public class DbUtil {
	private static final String url = "jdbc:mysql://localhost/students?serverTimezone=PRC";
	private static final String user = "root";
	private static final String password = "root";
	
	//��ȡ���ݿ����ӵķ���
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,user,password);
	}
	
	//�ر����ݿ����ӵķ���
	public static void Close(Connection conn) throws SQLException {
		if (conn!=null) {
			conn.close();
		}
	}
	
	//��ȡstudentsMessage������
	public static Object[][] getStudentsMessage() throws SQLException {
		Connection conn = DbUtil.getConnection();
		//����sql��ѯ��䣬��ȡ��ѯ���
		String sql =  "select  id,students_id, name, sex, "
				+ " department, room_id, email, "
				+ " phone_id, address, start_school,"
				+ " active from students_message";
		PreparedStatement pstmt = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = pstmt.executeQuery();//Ԥ�����ѯ���
		//��ȡ��ǰ���ϵ����������������С
		rs.last();
		Object[][] studentsMessage = new Object[rs.getRow()][];
		rs.beforeFirst();
		int row = 0;
		while(rs.next()) {
			Object[] stu = new Object[] {
					rs.getInt("id"),
					rs.getString("students_id"),
					rs.getString("name"),
					rs.getString("sex"),
					rs.getString("department"),
					rs.getString("room_id"),
					rs.getString("email"),
					rs.getString("phone_id"),
					rs.getString("address"),
					rs.getDate("start_school"),
					rs.getInt("active")==1?true:false
			};
			//ÿ��ȡһ�� ++
			studentsMessage[row++] = stu;
		}
		//�ر����ݿ�
		DbUtil.Close(conn);
		return studentsMessage;
	}
	
	// �����ݿ��ȡһ��ѧ����Ϣ
	public static StudentsMessage getStudentsMessage(int id) throws SQLException {
		Connection conn = DbUtil.getConnection();
		String sql = "select id ,students_id,name, sex,"
				+ " department, room_id, email, "
				+ " phone_id, address,  start_school,active from students_message"
				+ " where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setInt(1,id);
		ResultSet rs = pstmt.executeQuery();
		StudentsMessage stu = null;
		if (rs.next()) {
			stu = new StudentsMessage();
			stu.setId(rs.getInt("id"));
			stu.setStudentsID(rs.getString("students_id"));
			stu.setName(rs.getString("name"));
			stu.setSex(rs.getString("sex"));
			stu.setDepartment(rs.getString("department"));
			stu.setRoomID(rs.getString("room_id"));
			stu.setEmail(rs.getString("email"));
			stu.setPhoneID(rs.getString("phone_id"));
			stu.setAddress(rs.getString("address"));
			stu.setStartSchoolDate(rs.getDate("start_school"));
			stu.setActive(rs.getInt("active"));
		}
		DbUtil.Close(conn);
		return stu;
	}
	
	
	//���� �޸� �������ݿ�
	public  static boolean updateStudentsMessage(StudentsMessage s) throws SQLException {
		//�������ݿ�����
		Connection conn = DbUtil.getConnection();
		//������� update
		String sql = "update students_message set  students_id=?,"
				+ " name=?, sex=?, department=?, room_id=?,"
				+ " email=?, phone_id=?, address=?, active=?"
				+ " where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, s.getStudentsID());
		pstmt.setString(2, s.getName());
		pstmt.setString(3, s.getSex());
		pstmt.setString(4, s.getDepartment());
		pstmt.setString(5, s.getRoomID());
		pstmt.setString(6, s.getEmail());
		pstmt.setString(7, s.getPhoneID());
		pstmt.setString(8, s.getAddress());
		pstmt.setBoolean(9,s.isActive());
		pstmt.setInt(10, s.getId());
		//�������ݱ�
		int sucess = pstmt.executeUpdate();
		//�ر����ݿ�
		DbUtil.Close(conn);
		//�жϸ����Ƿ�ɹ�
		return sucess>0;
	}
	
	
	//����ѧ����Ϣ��¼
	public static boolean insertStudentsMessage(StudentsMessage s) throws SQLException {
		Connection conn = DbUtil.getConnection();
		String sql = "insert into students_message(students_id,name,sex,"
				+ " department,room_id,email, phone_id,address, active)"
				+ " values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, s.getStudentsID());
		pstmt.setString(2, s.getName());
		pstmt.setString(3, s.getSex());
		pstmt.setString(4, s.getDepartment());
		pstmt.setString(5, s.getRoomID());
		pstmt.setString(6, s.getEmail());
		pstmt.setString(7, s.getPhoneID());
		pstmt.setString(8, s.getAddress());
		pstmt.setInt(9, s.isActive()?1:0);
		int success = pstmt.executeUpdate();
		DbUtil.Close(conn);
		return success>0;
	}
	
	
	//ɾ��ѧ��ѧ�� �ڼ�״̬
	public static boolean updateSutdentsMessageActive(int id, boolean  isActive) 
			throws SQLException {
		Connection conn = getConnection();
		String sql = "update students_message set active=? "
				+ " where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setBoolean(1, isActive);
		pstmt.setInt(2, id);
		//�������ݱ�
		int success = pstmt.executeUpdate();
		//�ر����ݿ�
		conn.close();
		//�жϸ����Ƿ�ɹ����ҷ���
		return success>0;
	}
	
	//�жϸ����û����������ܷ��¼
	//�ܵ�¼����User���� ���ܵ�¼����null
	public static User getLoginUser(String username, String password)
			throws SQLException {
		User user = null;
		Connection conn = getConnection();
		String sql = "select admin_id, admin_name, admin_email, create_date,"
				+ " admin_active, username from admin"
				+ " where username=? and `password` = sha(?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			user = new User();
			user.setAdminID(rs.getInt("admin_id"));
			user.setAdminName(rs.getString("admin_name"));
			user.setAdminEmail(rs.getString("admin_email"));
			user.setAdminCreateDate(rs.getDate("create_date"));
			user.setAdminActive(rs.getInt("admin_active"));
			user.setUserName(rs.getString("username"));
		}
		//�ر����ݿ�����
		conn.close();
		return user;
 	}
	
//	//�޸Ĺ���Ա����ȶ� ���ݿ���û���
//	public static User selAdminUserName(String username , String OldPassword) 
//			throws SQLException {
//		User user = null;
//		Connection conn = getConnection();
//		String sql = "select username ,password from admin"
//				+ " where username=? and 'password'=sha1(?) ";
//		PreparedStatement pstmt	=conn.prepareStatement(sql);
//		pstmt.setString(1, username);
//		pstmt.setString(2, OldPassword);
//		ResultSet rs = pstmt.executeQuery();
//		if(rs.next()) {
//			user = new User();
//			user.setUserName(rs.getString("username"));
//			user.setPassword(rs.getString("password"));
//		}
//		conn.close();
//		return user;
//		
//	}
	
	
	//����Ա�޸�����
	public  static boolean updateAdminPassword(String username, String newPassword) 
			throws SQLException {
		
		Connection conn = getConnection();
		String sql = "update admin set `password`=sha(?) "
				+ " where username=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,newPassword);
		pstmt.setString(2, username);
	
		int success = pstmt.executeUpdate();
		conn.close();
		return success>0;
	}
	
}
