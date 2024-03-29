package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connectpackage.Connectionfactory;
import Shixain.Student;
import Shixain.Teacher;
import Connectpackage.*;

public class Solution implements Way{
	//登陆判断
	public int login(String no,String password)
	{
		Connection  conn=null;
		PreparedStatement  pstmt1=null;
		PreparedStatement  pstmt2=null;
		PreparedStatement  pstmt3=null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		//从三个数据库中寻找输入账号的身份		
		try {
			    conn=Connectionfactory.getConnection();	
				String  sql1="select * from student where stu_no=? and stu_pwd=?";//从student表找密码序号
				pstmt1=conn.prepareStatement(sql1);
				pstmt1.setString(1, no);
				pstmt1.setString(2, password);
				rs1=pstmt1.executeQuery();
				String  sql2="select * from teacher where tea_no=? and tea_pwd=?";
				pstmt2=conn.prepareStatement(sql2);
				pstmt2.setString(1, no);
				pstmt2.setString(2, password);
				rs2=pstmt2.executeQuery();
				String  sql3="select * from manager where mana_no=? and mana_pwd=?";
				pstmt3=conn.prepareStatement(sql3);
				pstmt3.setString(1, no);
				pstmt3.setString(2, password);
				rs3=pstmt3.executeQuery();
				if(rs1.next())
				{
					return  1;//返回说明该账号是学生类型
				}
				if(rs2.next())
				{
					return 2;//返回说明该账号是老师类型
				}
				if(rs3.next())
				{
					return 3;//返回说明该账号是管理员类型
				}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					Connectionfactory.closeStatement(pstmt1);
					Connectionfactory.closeStatement(pstmt2);
					Connectionfactory.closeStatement(pstmt3);
					Connectionfactory.closeConnection(conn);
					Connectionfactory.closeResultSet(rs1);
					Connectionfactory.closeResultSet(rs2);
					Connectionfactory.closeResultSet(rs3);
				}
				return 0;
	}
	//学生注册
	public void zhuceofStudent(Student s)
	{
		Connection  conn=null;
		PreparedStatement  pstmt=null;
				try {
			conn=Connectionfactory.getConnection();	
				String  sql="insert  into  student(stu_no" 
						+ ",stu_pwd"
						+ ",stu_name"
						+ ",stu_discipline"
						+ ",stu_grade)  "
						+ " values(?,?,?,?,?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, s.getno());
				pstmt.setString(2, s.getpassword());
				pstmt.setString(3, s.getname());
				pstmt.setString(4, s.getdiscipline());
				pstmt.setString(5, s.getgrade());
				pstmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					Connectionfactory.closeStatement(pstmt);
					Connectionfactory.closeConnection(conn);
				}
	}
	//老师注册
	public void zhuceofTeacher(Teacher t)
	{
		Connection  conn=null;
		PreparedStatement  pstmt=null;
				try {
			conn=Connectionfactory.getConnection();	
				String  sql="insert  into  teacher(tea_no" 
						+ ",tea_pwd"
						+ ",tea_name"
						+ ",tea_discipline"
						+ ",tea_mail)  "
						+ " values(?,?,?,?,?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, t.getno());
				pstmt.setString(2, t.getpassword());
				pstmt.setString(3, t.getname());
				pstmt.setString(4, t.getdiscipline());
				pstmt.setString(5, t.getmail());
				pstmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					Connectionfactory.closeStatement(pstmt);
					Connectionfactory.closeConnection(conn);
				}
	}
	//忘记密码学生信息判断，输入账号入库查询的姓名和输入姓名是否一致
	public boolean massagestupipei(String n,String nn)
	{
		Connection  conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs = null;
				try {
				
				conn=Connectionfactory.getConnection();	
				String  sql="select stu_name from student where stu_no=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,n);
				rs=pstmt.executeQuery();
				if(rs.next()){
				  if(nn.equals(rs.getString("stu_name")))
				  {
					  return true;
				  }
				}
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					Connectionfactory.closeStatement(pstmt);
					Connectionfactory.closeConnection(conn);
					Connectionfactory.closeResultSet(rs);
				}
				return false;
	}
	//忘记密码老师信息判断，输入账号入库查询的姓名和输入姓名是否一致
	public boolean massageteapipei(String n,String nn)
	{
		Connection  conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs = null;
				try {
					conn=Connectionfactory.getConnection();	
					String  sql="select tea_name from teacher where tea_no=?";
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1,n);
					rs=pstmt.executeQuery();
					if(rs.next()){
						if(nn.equals(rs.getString("tea_name")))
						  {
							  return true;
						  }
					}
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					Connectionfactory.closeStatement(pstmt);
					Connectionfactory.closeConnection(conn);
					Connectionfactory.closeResultSet(rs);
				}
				return false;
	}
	//入库寻找学生密码并返回
	public String findstupassword(String n)
	{
		Connection  conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs = null;
		String newpass=null;
				try {
				
				conn=Connectionfactory.getConnection();	
				String  sql="select stu_pwd from student where stu_no=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,n);
				rs=pstmt.executeQuery();
				if(rs.next()){
				newpass=rs.getString("stu_pwd");
				}
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					Connectionfactory.closeStatement(pstmt);
					Connectionfactory.closeConnection(conn);
					Connectionfactory.closeResultSet(rs);
				}
				return newpass;
	}
	//入库寻找老师密码并返回
	public String findteapassword(String n)
	{
		Connection  conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs = null;
		String newpass=null;
				try {
				
					conn=Connectionfactory.getConnection();	
					String  sql="select tea_pwd from teacher where tea_no=?";
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1,n);
					rs=pstmt.executeQuery();
					if(rs.next()){
					newpass=rs.getString("tea_pwd");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					Connectionfactory.closeStatement(pstmt);
					Connectionfactory.closeConnection(conn);
					Connectionfactory.closeResultSet(rs);
				}
				return newpass;
	}
	//更改学生密码判断
	public boolean updatestupwd(String n,String op,String np)
	{
		Connection  conn=null;
		PreparedStatement  pstmt1=null;
		PreparedStatement  pstmt2=null;
		ResultSet rs1 = null;
		
				try {
				
				conn=Connectionfactory.getConnection();	
				String  sql1="select stu_pwd from student where stu_no=?";
				pstmt1=conn.prepareStatement(sql1);
				pstmt1.setString(1,n);
				rs1=pstmt1.executeQuery();
				
				if(rs1.next()){
				if(op.equals(rs1.getString("stu_pwd")))//如果查出的密码和输入密码一样
				{
					/*String  sql2="update student set stu_pwd=? where stu_no=?";
					pstmt2=conn.prepareStatement(sql2);
					pstmt2.setString(1,np);
					pstmt2.setString(2,n);*/
					return true;
				}
				}
				
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					Connectionfactory.closeStatement(pstmt1);
					Connectionfactory.closeStatement(pstmt2);
					Connectionfactory.closeConnection(conn);
					Connectionfactory.closeResultSet(rs1);
				}
				return false;
	}
	//更改学生密码
	public void updatestuwork(String n,String np)
	{
		Connection  conn=null;
		PreparedStatement  pstmt2=null;
				try {
					conn=Connectionfactory.getConnection();	
					String  sql2="update student set stu_pwd=? where stu_no=?";
					pstmt2=conn.prepareStatement(sql2);
					pstmt2.setString(1,np);
					pstmt2.setString(2,n);
					pstmt2.executeUpdate();
					
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					Connectionfactory.closeStatement(pstmt2);
					Connectionfactory.closeConnection(conn);
				}
	}
	//更改老师密码判定
	public boolean updateteapwd(String n,String op,String np)
	{
		Connection  conn=null;
		PreparedStatement  pstmt1=null;
		PreparedStatement  pstmt2=null;
		ResultSet rs1 = null;
		
				try {
				
				conn=Connectionfactory.getConnection();	
				String  sql1="select tea_pwd from teacher where tea_no=?";
				pstmt1=conn.prepareStatement(sql1);
				pstmt1.setString(1,n);
				rs1=pstmt1.executeQuery();
				if(rs1.next()){
				if(op.equals(rs1.getString("tea_pwd")))//如果查出的密码和输入密码一样
				{
					return true;
				}
				}
				
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					Connectionfactory.closeStatement(pstmt1);
					Connectionfactory.closeStatement(pstmt2);
					Connectionfactory.closeConnection(conn);
					Connectionfactory.closeResultSet(rs1);
				}
				return false;
	}
	//更改老师密码
	public void updateteawork(String no,String np)
	{
		Connection  conn=null;
		PreparedStatement  pstmt2=null;	
			try {
				
				conn=Connectionfactory.getConnection();	
				String  sql2="update teacher set tea_pwd=? where tea_no=?";
				pstmt2=conn.prepareStatement(sql2);
				pstmt2.setString(1,np);
				pstmt2.setString(2,no);
				pstmt2.executeUpdate();
				}
				
				 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{					
					Connectionfactory.closeStatement(pstmt2);
					Connectionfactory.closeConnection(conn);
				}
				
	}
	//检擦注册账号库中是否已存在
	public boolean checkzhuce(String n)
	{
		Connection  conn=null;
		PreparedStatement  pstmt=null;
		ResultSet rs = null;
				try {
				
				conn=Connectionfactory.getConnection();	
				String  sql="select * from student where stu_no=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,n);
				rs=pstmt.executeQuery();
				if(rs.next()){
				   return false;//存在返回false
				}
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					Connectionfactory.closeStatement(pstmt);
					Connectionfactory.closeConnection(conn);
					Connectionfactory.closeResultSet(rs);
				}
				return true;
	}

}
