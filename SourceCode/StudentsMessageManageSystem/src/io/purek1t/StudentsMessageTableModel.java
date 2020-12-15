package io.purek1t;

import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

public class StudentsMessageTableModel extends AbstractTableModel{
	private String[] ColumnNames = { "���","ѧ��","����","�Ա�",
	        "����|�༶","�����","Email", "�ֻ���", "��Դ��","��ѧʱ��","�ڼ�״̬"};
	private Object[][] studentsMessage ;
	
	
	public StudentsMessageTableModel() {
		try {
			this.studentsMessage = DbUtil.getStudentsMessage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	@Override //��ȡ����
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.studentsMessage.length;
	}

	@Override  //��ȡ�е�����
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
	    return ColumnNames[column];
	}

	@Override  //��ȡ�е�����
	public Class<?> getColumnClass(int columnIndex) {
		// TODO  Auto-generated method stub
		return this.getValueAt(0, columnIndex).getClass();
	}

	@Override  //��ȡ�е�����
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.ColumnNames.length;
	}

	@Override  //ÿһ��ÿһ�ж�Ӧ��ֵ
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return studentsMessage[rowIndex][columnIndex];
	}

}
