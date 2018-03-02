package com.sg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DisplayServlet")
public class DisplayServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private Map<String, LoginLog> allLoginLogs;

    public DisplayServlet()
    {
	super();
	allLoginLogs = null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	response.getWriter().append("Served at: DisplayServletGet").append(request.getContextPath());
    }

    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	HttpSession session = request.getSession();

	if (session == null)
	{
	    response.sendRedirect("LoginPageServlet");
	}

	ServletContext context = getServletContext();
	this.allLoginLogs = (Map<String, LoginLog>) context.getAttribute("allLoginLogs");

	if (this.allLoginLogs == null || allLoginLogs.isEmpty())
	{
	    response.sendRedirect("LoginPageServlet");
	}

	String uname = request.getParameter("uname");
	String pword = request.getParameter("pword");
	String hashedString = Utility.hashUnamePwrd(uname, pword);

	LoginLog log = this.allLoginLogs.get(hashedString);
	log.addLog(new Date());
	this.allLoginLogs.put(hashedString, log);

	response.setContentType("text/html");
	PrintWriter out = response.getWriter();

	out.println("<html>");
	out.println("<head>");
	out.println("<title> Login Log </title>");
	out.println("</head>");
	out.println("<body>");
//	out.println("<p><b>Your Session ID : " + request.getSession().getId() + "</b></p>");
	out.println("<h1> Below are the login Logs : </h1>");
	out.println("<table border = '1'>");
	out.println("<tr>");
	out.println("<th>First Name</th>");
	out.println("<th>Last Name</th>");
	out.println("<th>Phone</th>");
	out.println("<th>Email</th>");
	out.println("<th>Country</th>");
	out.println("<th>City</th>");
	out.println("<th>Login Detail</th>");
	out.println("</tr>");

	for (Map.Entry<String, LoginLog> entry : this.allLoginLogs.entrySet())
	{
	    LoginLog aLog = entry.getValue();
	    ArrayList<Date> aLogsDates = aLog.getLoginLog();

	    for (Date dt : aLogsDates)
	    {
		out.println("<tr>");
		out.println("<td>" + aLog.getFirstName() + "</td>");
		out.println("<td>" + aLog.getLastName() + "</td>");
		out.println("<td>" + aLog.getPhone() + "</td>");
		out.println("<td>" + aLog.getEmail() + "</td>");
		out.println("<td>" + aLog.getCountry() + "</td>");
		out.println("<td>" + aLog.getCity() + "</td>");
		out.println("<td>" + aLog.getDateDisplay(dt) + "</td>");
		out.println("</tr>");
	    }
	}

	out.println("</table>");
	out.println("<form action = 'LoginPageServlet' method = 'Get'>");
	out.println("<p> <input type = 'submit' value = 'Go To Login Page' /></p>");
	out.println("</form>");
	out.println("</body>");
	out.println("</html>");
    }

}
