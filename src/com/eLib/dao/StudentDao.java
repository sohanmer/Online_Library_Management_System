package com.eLib.dao;

import com.eLib.beans.StudentBean;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDao {

	public static int save(StudentBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("insert into e_student(sname,spassword,smobile) values(?,?,?)");
			ps.setString(1,bean.getName());
			ps.setString(2,bean.getPassword());
			ps.setLong(3,bean.getMobile());
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	public static int update(StudentBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("update e_student set sname=?,spassword=?,smobile=? where sid=?");
			ps.setString(1,bean.getName());
			ps.setString(2,bean.getPassword());
			ps.setLong(3,bean.getMobile());
			ps.setInt(4,bean.getId());
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	public static List<StudentBean> view(){
		List<StudentBean> list=new ArrayList<StudentBean>();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from e_student");
			ResultSet rs=ps.executeQuery();
			System.out.println(rs.getRow());
			while(rs.next()){
				StudentBean bean=new StudentBean();
				bean.setId(rs.getInt("sid"));
				bean.setName(rs.getString("sname"));
				bean.setPassword(rs.getString("spassword"));
				bean.setMobile(rs.getLong("smobile"));
				list.add(bean);
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return list;
	}
	public static StudentBean viewById(int id){
		StudentBean bean=new StudentBean();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from e_student where sid=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				bean.setId(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setPassword(rs.getString(3));
				bean.setMobile(rs.getLong(4));
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return bean;
	}
	public static int delete(int id){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("delete from e_student where sid=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}

	public static boolean authenticate(long stumobile,String spassword){
		boolean status=false;
		int sid = 0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from e_student where smobile=? and spassword=?");
			ps.setLong(1,stumobile);
			ps.setString(2,spassword);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				status=true;
				sid = rs.getInt("sid");				
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		System.out.println(status);
		return status;
		
	}
}
