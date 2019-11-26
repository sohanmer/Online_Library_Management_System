package com.eLib.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eLib.beans.LibrarianBean;
import com.eLib.beans.StudentBean;
import com.eLib.dao.LibrarianDao;
import com.eLib.dao.StudentDao;
@WebServlet("/AddStudent")
public class AddStudent extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Add Librarian</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		
		String sname=request.getParameter("sname");
		String spassword=request.getParameter("spassword");
		String stumobile=request.getParameter("smobile");
		long smobile=Long.parseLong(stumobile);
		StudentBean bean=new StudentBean(sname,spassword, smobile);
		StudentDao.save(bean);
		out.print("<h4>Librarian added successfully</h4>");
		request.getRequestDispatcher("addstudentform.html").include(request, response);
		
		
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
