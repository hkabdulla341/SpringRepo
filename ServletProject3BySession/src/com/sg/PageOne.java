package com.sg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PageOne
 */
@WebServlet("/")
public class PageOne extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public PageOne()
    {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {	
	PrintWriter out = response.getWriter();

	response.setContentType("text/html");
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Page One</title>");
	out.println("</head>");
	out.println("<body>");
	out.println("<p><b>Your Session ID : " + request.getSession().getId() + "</b></p>");
	out.println("<form name = 'frm' action = 'PageTwo' method=Post>");
	out.println("<p>First Name : <input type = 'text' name = 'fname'/></p>");
	out.println("<p>Last Name : <input type = 'text' name = 'lname'/></p>");
	out.println("<p><input type = 'submit' value = 'Submit' name = 'btmSubmit'/></p>");
	out.println("</form>");
	out.println("</body>");
	out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	doGet(request, response);
    }

}
