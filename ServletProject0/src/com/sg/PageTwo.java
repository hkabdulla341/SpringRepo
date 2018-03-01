package com.sg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PageTwo")
public class PageTwo extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public PageTwo()
    {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	doGet(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	String fName = request.getParameter("fname");
	String lName = request.getParameter("lname");;
	
	PrintWriter out = response.getWriter();
	
	response.setContentType("text/html");
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Page One</title>");
	out.println("</head>");
	out.println("<body>");
	out.println("<p>First Name : " + fName + " </p>");
	out.println("<p>Last Name : " + lName +  " </p>");
	out.println("<form name = 'frm2' action = 'PageThree' method=Post>");
	out.println("<p>Phone : <input type = 'text' name = 'Phone'/></p>");
	out.println("<p>Email : <input type = 'email' name = 'Email'/></p>");
	out.println("<p><input type = 'submit' value = 'Submit' name = 'btmSubmit2'/></p>");
	out.println("</form>");
	out.println("</body>");
	out.println("</html>");
    }

}
