package com.eLib.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eLib.dao.StudentDao;
@WebServlet("/StudentLogin")
public class StudentLogin extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
				
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Student Section</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		String smobile=request.getParameter("smobile");
		long stumobile=Long.parseLong(smobile);
		String spassword=request.getParameter("spassword");
		if(StudentDao.authenticate(stumobile, spassword)){
			HttpSession session=request.getSession();
			session.setAttribute("stumobile",stumobile);
			request.setAttribute("stumobile", stumobile);
						
			request.getRequestDispatcher("navstudent.jsp").include(request, response);
			request.getRequestDispatcher("librariancarousel.html").include(request, response);
			
					
		}
		else{
			request.getRequestDispatcher("navhome.html").include(request, response);
			out.println("<div class='container'>");
			out.println("<h3>Username or password error</h3>");
			request.getRequestDispatcher("studentloginform.html").include(request, response);
			out.println("</div>");
		}
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
