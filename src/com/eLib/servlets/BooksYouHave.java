package com.eLib.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eLib.beans.BookBean;
import com.eLib.beans.LibrarianBean;
import com.eLib.dao.BookDao;
import com.eLib.dao.LibrarianDao;
@WebServlet("/BooksYouHave")
public class BooksYouHave extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Book</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navstudent.jsp").include(request, response);
		out.println("<div class='container'>");
		long smobile = (long) request.getAttribute("stumobile");
		request.setAttribute("stumobile", smobile);
		System.out.println(smobile);
		List<BookBean> list=BookDao.issuedToStudent(smobile);
		System.out.println(smobile);
		out.println("<h2>List Of Books Issued To You<h2>");
		out.println("<table class='table table-bordered table-striped'>");
		out.println("<tr><th>Callno</th><th>Name</th><th>Author</th><th>Publisher</th><th>Issued</th></tr>");
		for(BookBean bean:list){
			out.println("<tr><td>"+bean.getCallno()+"</td><td>"+bean.getName()+"</td><td>"+bean.getAuthor()+"</td><td>"+bean.getPublisher()+"</td><td>"+bean.getIssued()+"</td></tr>");
		}
		out.println("</table>");
		
		out.println("</div>");
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
}

