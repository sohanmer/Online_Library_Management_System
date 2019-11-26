package com.eLib.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.eLib.beans.BookBean;
import com.eLib.beans.IssueBookBean;
import com.eLib.beans.LibrarianBean;

public class BookDao {

	public static int save(BookBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("insert into e_book values(?,?,?,?,?,?)");
			ps.setString(1,bean.getCallno());
			ps.setString(2,bean.getName());
			ps.setString(3,bean.getAuthor());
			ps.setString(4,bean.getPublisher());
			ps.setInt(5,bean.getQuantity());
			ps.setInt(6,0);
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){
			System.out.println(e);
			}
		
		return status;
	}
	public static List<BookBean> view(){
		List<BookBean> list=new ArrayList<BookBean>();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps1=con.prepareStatement("select * from e_book");
			ResultSet rs1=ps1.executeQuery();
			
			while(rs1.next()){
				BookBean bean=new BookBean();
				bean.setCallno(rs1.getString("callno"));
				bean.setName(rs1.getString("name"));
				bean.setAuthor(rs1.getString("author"));
				bean.setPublisher(rs1.getString("publisher"));
				bean.setQuantity(rs1.getInt("quantity"));
				bean.setIssued(rs1.getInt("issued"));
				
				list.add(bean);
				
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return list;
	}
	public static List<BookBean> issuedToStudent(Long smobile){
		List<BookBean> list=new ArrayList<BookBean>();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps1=con.prepareStatement("select callno from e_issuebook where studentmobile = "+ smobile);
			ResultSet rs1=ps1.executeQuery();
			
			while (rs1.next()) {
				
				PreparedStatement ps2 = con.prepareStatement("select * from e_book where callno = " + rs1.getString("callno"));
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next()){
					BookBean bean=new BookBean();
					bean.setCallno(rs2.getString("callno"));
					bean.setName(rs2.getString("name"));
					bean.setAuthor(rs2.getString("author"));
					bean.setPublisher(rs2.getString("publisher"));
					bean.setQuantity(rs2.getInt("quantity"));
					bean.setIssued(rs2.getInt("issued"));
					
					list.add(bean);
					
				}
				
			}
			
		
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return list;
	}
	public static int delete(String callno){
		int status=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("delete from e_book where callno=?");
			ps.setString(1,callno);
			status=ps.executeUpdate();
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	public static int getIssued(String callno){
		int issued=0;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from e_book where callno=?");
			ps.setString(1,callno);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				issued=rs.getInt("issued");
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return issued;
	}
	public static boolean checkIssue(String callno){
		boolean status=false;
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from e_book where callno = ? and quantity > issued");
			ps.setString(1,callno);
			ResultSet rs=ps.executeQuery();
			System.out.println(rs);
			if(rs.next()){
				status=true;
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return status;
	}
	public static int issueBook(IssueBookBean bean){
		String callno = bean.getCallno();
		boolean checkstatus=checkIssue(callno);
		System.out.println("Check status: "+checkstatus);
		if(checkstatus){
			int status=0;
			try{
				Connection con=DB.getCon();
				PreparedStatement ps=con.prepareStatement("insert into e_issuebook values(?,?,?,?,?,?)");
				ps.setString(1,bean.getCallno());
				ps.setInt(2,bean.getStudentid());
				ps.setString(3,bean.getStudentname());
				ps.setLong(4,bean.getStudentmobile());
				java.sql.Date currentDate=new java.sql.Date(System.currentTimeMillis());
				ps.setDate(5,currentDate);
				ps.setString(6,"no");
				
				status=ps.executeUpdate();
				if(status>0){
					PreparedStatement ps2=con.prepareStatement("update e_book set issued=? where callno=?");
					ps2.setInt(1,getIssued(callno)+1);
					ps2.setString(2,callno);
					status=ps2.executeUpdate();
				}
				con.close();
				
			}catch(Exception e){System.out.println(e);}
			
			return status;
		}else{
			return 0;
		}
	}
	public static int returnBook(String callno,int studentid){
		int status=0;
			try{
				Connection con=DB.getCon();
				PreparedStatement ps=con.prepareStatement("update e_issuebook set returnstatus='yes' where callno=? and studentid=?");
				ps.setString(1,callno);
				ps.setInt(2,studentid);
				
				status=ps.executeUpdate();
				if(status>0){
					PreparedStatement ps2=con.prepareStatement("update e_book set issued=? where callno=?");
					ps2.setInt(1,getIssued(callno)-1);
					ps2.setString(2,callno);
					status=ps2.executeUpdate();
				}
				con.close();
				
			}catch(Exception e){System.out.println(e);}
			
			return status;
	}
	public static List<IssueBookBean> viewIssuedBooks(){
		List<IssueBookBean> list=new ArrayList<IssueBookBean>();
		try{
			Connection con=DB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from e_issuebook order by issuedate desc");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				IssueBookBean bean=new IssueBookBean();
				bean.setCallno(rs.getString("callno"));
				bean.setStudentid(rs.getInt("studentid"));
				bean.setStudentname(rs.getString("studentname"));
				bean.setStudentmobile(rs.getLong("studentmobile"));
				bean.setIssueddate(rs.getDate("issuedate"));
				bean.setReturnstatus(rs.getString("returnstatus"));
				list.add(bean);
			}
			con.close();
			
		}catch(Exception e){System.out.println(e);}
		
		return list;
	}
}
