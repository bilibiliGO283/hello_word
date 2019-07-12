package com.org.Exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExamDaoMpl implements ExamDao{
	@Override
	public Exam getExamByNo(String no1) {
		Connection conn= ExamUtils.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = null;
			sql = "select * from test where tst_no= ?";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, no1);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int i=0;
				String sql2 = null;
				sql2 = "select * from testpro where tst_no= ?";
				stmt = conn.prepareStatement(sql2);
				stmt.setString(1, no1);
				ResultSet rs2 = stmt.executeQuery();
				String[] sList1=new String[200];
				while(rs2.next()){
					sList1[i++]=rs2.getString("pro_no");
				}
				
				Exam s2=new Exam(rs.getString("tst_no"),rs.getString("tst_name"),rs.getString("tst_time"),rs.getString("tst_type"),rs.getString("tst_dif"),rs.getString("tst_num"),rs.getString("tst_score"),sList1);
				ExamUtils.closeResultSet(rs);
				ExamUtils.closeResultSet(rs2);
				ExamUtils.closeStatement(stmt);
				ExamUtils.closeConnection(conn);
				return s2;
			}
			else{
				System.out.println("学号不存在");
				ExamUtils.closeResultSet(rs);
				ExamUtils.closeStatement(stmt);
				ExamUtils.closeConnection(conn);
				return null;
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		} 
		return null;
	}
	@Override
	public void insertExam(Exam s1) {
		Connection conn = ExamUtils.getConnection();;
		PreparedStatement stmt = null;
		try {
			String sql = null;
			sql = "INSERT INTO test(tst_no,tst_name,tst_time,tst_type,tst_dif,tst_num,tst_score) VALUES(?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, s1.getTst_no());
			stmt.setString(2, s1.getTst_name());
			stmt.setString(3, s1.getTst_time());
			stmt.setString(4, s1.getTst_type());
			stmt.setString(5, s1.getTst_dif());
			stmt.setString(6, s1.getTst_num());
			stmt.setString(7, s1.getTst_score());

			stmt.executeUpdate();
			
			String[] list=s1.getTst_detail();
			for(int i=0;i<list.length;i++){
				if(list[i]!=null){
					sql = "INSERT INTO testpro(tst_no,pro_no) VALUES(?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, s1.getTst_no());
					stmt.setString(2, list[i]);
					stmt.executeUpdate();
				}
			}
			
			ExamUtils.closeStatement(stmt);
			ExamUtils.closeConnection(conn);
		} catch (Exception ee) {
			ee.printStackTrace();
		} 
	}
	@Override
	public void updateExam(Exam s1) {
		Exam sTest;
		sTest=getExamByNo(s1.getTst_no());
		if(sTest==null){
			System.out.println("更新失败，本示例不存在！");
		}
		else{
			Connection conn = ExamUtils.getConnection();
			PreparedStatement stmt = null;
			try {
				String sql = null;
				sql = "update test set tst_no=?,tst_name=?,tst_time=?,tst_type=?,tst_dif=?,tst_num=?,tst_score=?";
				stmt = conn.prepareStatement(sql);

				stmt.setString(1, s1.getTst_no());
				stmt.setString(2, s1.getTst_name());
				stmt.setString(3, s1.getTst_time());
				stmt.setString(4, s1.getTst_type());
				stmt.setString(5, s1.getTst_dif());
				stmt.setString(6, s1.getTst_num());
				stmt.setString(7, s1.getTst_score());

				stmt.executeUpdate();
				ExamUtils.closeStatement(stmt);
				ExamUtils.closeConnection(conn);
			} catch (Exception ee) {
				ee.printStackTrace();
			} 
		}
	}
	@Override
	public void deleteExam(String no1) {
		Exam sTest;
		sTest=getExamByNo(no1);
		if(sTest==null){
			System.out.println("删除失败，本示例不存在！");
		}
		else{
			Connection conn = ExamUtils.getConnection();
			PreparedStatement stmt = null;
			try {
				String sql = null;
				sql = "delete from test where tst_no=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,no1);
				stmt.executeUpdate();
				
				sql = "delete from testpro where tst_no=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,no1);
				stmt.executeUpdate();
				
				ExamUtils.closeStatement(stmt);
				ExamUtils.closeConnection(conn);
			} catch (Exception ee) {
				ee.printStackTrace();
			} 
		}
	}
	
	@Override
	public int getExamnum() {
		Connection conn = ExamUtils.getConnection();
		PreparedStatement stmt = null;
		int ans=0;
		try {
			String sql = null;
			sql = "select count(*) from testpro group by tst_no";
			stmt=conn.prepareStatement(sql);
			ResultSet rs2 = stmt.executeQuery();
			if(rs2==null) {
				return 0;
			}
			ans=Integer.parseInt(rs2.getString("count"));
			ExamUtils.closeStatement(stmt);
			ExamUtils.closeConnection(conn);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return ans;
	}
	@Override
	public List<String> getExamname(){
		Connection conn = ExamUtils.getConnection();
		PreparedStatement stmt = null;
		List<String> tmp =new ArrayList<String>();
		try {
			String sql = null;
			sql = "select tst_name from test";
			stmt=conn.prepareStatement(sql);
			ResultSet rs2 = stmt.executeQuery();
			while(rs2.next()) {
				tmp.add(rs2.getString("tst_name"));
			}
			ExamUtils.closeStatement(stmt);
			ExamUtils.closeConnection(conn);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return tmp;
	}
	@Override
	public List<String> getExamtst_no(){
		Connection conn = ExamUtils.getConnection();
		PreparedStatement stmt = null;
		List<String> tmp =new ArrayList<String>();
		try {
			String sql = null;
			sql = "select tst_no from test";
			stmt=conn.prepareStatement(sql);
			ResultSet rs2 = stmt.executeQuery();
			while(rs2.next()) {
				tmp.add(rs2.getString("tst_no"));
			}
			ExamUtils.closeStatement(stmt);
			ExamUtils.closeConnection(conn);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return tmp;
	}
	@Override
	public List<String> getExamname_tst_no(){
		Connection conn = ExamUtils.getConnection();
		PreparedStatement stmt = null;
		List<String> tmp =new ArrayList<String>();
		try {
			String sql = null;
			sql = "select * from test";
			stmt=conn.prepareStatement(sql);
			ResultSet rs2 = stmt.executeQuery();
			while(rs2.next()) {
				tmp.add(rs2.getString("tst_name"));
				tmp.add(rs2.getString("tst_no"));
			}
			ExamUtils.closeStatement(stmt);
			ExamUtils.closeConnection(conn);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return tmp;
	}

	@Override
	public List<String> getExampro_Choice(String tst_no){
		Connection conn = ExamUtils.getConnection();
		PreparedStatement stmt = null;
		List<String> tmp =new ArrayList<String>();
		
		try {
			String sql = null;
			sql = "select pro_detail,pro_no from problem where pro_type='choice' and pro_no in ("
					+ "select pro_no from testpro where tst_no='"+tst_no+"')";
			
			stmt=conn.prepareStatement(sql);
			ResultSet rs2 = stmt.executeQuery();
			while(rs2.next()) {
				String[] sp=rs2.getString("pro_detail").split("\\;");
				tmp.add(sp[0]);
				tmp.add(sp[1]);
				tmp.add(sp[2]);
				tmp.add(sp[3]);
				tmp.add(sp[4]);
				tmp.add(rs2.getString("pro_no"));
			}
			
			ExamUtils.closeStatement(stmt);
			ExamUtils.closeConnection(conn);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return tmp;
	}
}
