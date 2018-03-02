package com.sg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.net.httpserver.HttpContext;

@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private Map<String, LoginLog> allLoginLogs;

    public RegisterUserServlet()
    {
	super();
	allLoginLogs = null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	HttpSession session = request.getSession();

	if (session == null)
	{
	    response.sendRedirect("LoginPageServlet");
	}

	response.setContentType("text/html");
	PrintWriter out = response.getWriter();

	out.println("<html>");
	out.println("<head>");
	out.println("<title>Register New User</title>");
	out.println("</head>");
	out.println("<body>");
	out.println("<h1>Enter Details below : </h1>");
//	out.println("<p><b>Your Session ID : " + request.getSession().getId() + "</b></p>");
	out.println("<form action = 'RegisterUserServlet' method = 'POST'>");
	out.println("<p> New Username : <input type = 'text' name = 'newUname'/></p>");
	out.println("<p> New Password : <input type = 'password' name = 'newPwrd'/></p>");
	out.println("<p> First Name   : <input type = 'text' name = 'newFname'/></p>");
	out.println("<p> Last Name    : <input type = 'text' name = 'newLname'/></p>");
	out.println("<p> Phone 		 : <input type = 'text' name = 'newPhone'/></p>");
	out.println("<p> Email 		 : <input type = 'email' name = 'newEmail'/></p>");
	out.println("<p> Country 	 : <input type = 'text' name = 'newCountry'/></p>");
	out.println("<p> City 		 : <input type = 'text' name = 'newCity'/></p>");
	out.println("<p> <input type = 'submit' value = 'Register' /></p>");
	out.println("</form>");
	out.println("</body>");
	out.println("</html>");
    }

    @SuppressWarnings({ "unchecked" })
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	HttpSession session = request.getSession();

	if (session == null)
	{
	    response.sendRedirect("LoginPageServlet");
	}

	ServletContext context = getServletContext();
	this.allLoginLogs = (Map<String, LoginLog>) context.getAttribute("allLoginLogs");

	if (this.allLoginLogs == null)
	{
	    this.allLoginLogs = new HashMap<String, LoginLog>();
	}

	String uname = request.getParameter("newUname");
	String pword = request.getParameter("newPwrd");
	String fname = request.getParameter("newFname");
	String lname = request.getParameter("newLname");
	String phone = request.getParameter("newPhone");
	String email = request.getParameter("newEmail");
	String country = request.getParameter("newCountry");
	String city = request.getParameter("newCity");

	LoginLog log = new LoginLog(fname, lname, phone, email, country, city);
	this.allLoginLogs.put(Utility.hashUnamePwrd(uname, pword), log);

	context.setAttribute("allLoginLogs", this.allLoginLogs);
	session.invalidate();
	
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();

	out.println("<html>");
	out.println("<head>");
	out.println("<title>" + uname + " is created </title>");
	out.println("</head>");
	out.println("<body>");
//	out.println("<p><b>Your Session ID : " + request.getSession().getId() + "</b></p>");
	out.println("<h1>New user created : " + uname + "</h1>");
	out.println("<form action = 'LoginPageServlet' method = 'Get'>");
	out.println("<p> <input type = 'submit' value = 'Go To Login Page' /></p>");
	out.println("</form>");
	out.println("</body>");
	out.println("</html>");
    }

}
