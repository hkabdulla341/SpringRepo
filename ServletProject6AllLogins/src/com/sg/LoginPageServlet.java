package com.sg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/")
public class LoginPageServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private Map<String, LoginLog> allLoginLogs;

    public LoginPageServlet()
    {
	super();
	allLoginLogs = null;
    }

    @SuppressWarnings({ "unchecked" })
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	ServletContext context = getServletContext();

	this.allLoginLogs = (Map<String, LoginLog>) context.getAttribute("allLoginLogs");

	if (this.allLoginLogs == null)
	{
	    this.allLoginLogs = new HashMap<String, LoginLog>();
	}

	context.setAttribute("allLoginLogs", allLoginLogs);

	if (this.allLoginLogs == null)
	{
	    this.allLoginLogs = new HashMap<String, LoginLog>();
	}

	HttpSession session = request.getSession(false);
	boolean invalidLogin = false;

	if (session != null)
	{
	    // System.out.println(session.getId());

	    try
	    {
		boolean boolInvalid = (boolean) session.getAttribute("InvalidLogin");

		if (new Boolean(boolInvalid) != null)
		{
		    invalidLogin = boolInvalid;
		}
	    }
	    catch (Exception ex)
	    {
		// System.out.println("Exception Hit - " + ex.getMessage());
		// System.out.println();
	    }

	    session.invalidate();
	}

	// if (session == null)
	// {
	// System.out.println("session is null");
	// }
	// else
	// {
	// if (!request.isRequestedSessionIdValid())
	// {
	// System.out.println("session is not null but request session is not
	// valid");
	// }
	// else
	// {
	// System.out.println("session is not null && request session is
	// valid");
	// }
	// }

	response.setContentType("text/html");
	PrintWriter out = response.getWriter();

	out.println("<html>");
	out.println("<head>");
	out.println("<title>Login Page</title>");
	out.println("</head>");
	out.println("<body>");
	// out.println("<p><b>Your Session ID : " + request.getSession().getId()
	// + "</b></p>");
	out.println("<h1><b>Login Page</b></h1>");

	if (invalidLogin)
	{
	    out.println("<p><u>Invalid Username/Password!!</u></p>");
	}

	out.println("<form action='LoginPageServlet' method = 'POST'>");
	out.println("<p> Username : <input type = 'text' name='uname'/></p>");
	out.println("<p> Password : <input type = 'password' name='pword'/></p>");
	out.println(
		"<p> <a href='RegisterUserServlet'>Register Here</a> <input type = 'submit' value = 'Login' /></p>");
	out.println("</form>");
	out.println("</body>");
	out.println("</html>");

    }

    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

	HttpSession session = request.getSession(false);

	if (session == null)
	{
	    response.sendRedirect("LoginPageServlet");
	}

	ServletContext context = getServletContext();

	this.allLoginLogs = (Map<String, LoginLog>) context.getAttribute("allLoginLogs");

	if (this.allLoginLogs == null)
	{
	    response.sendRedirect("LoginPageServlet");
	}

	String uname = request.getParameter("uname");
	String pword = request.getParameter("pword");
	String hashedLogin = Utility.hashUnamePwrd(uname, pword);

	boolean validity = this.allLoginLogs.containsKey(hashedLogin);

	// System.out.println("Size of map - " + this.allLoginLogs.size());

	if (!this.allLoginLogs.containsKey("Invalid Login"))
	{
	    // System.out.println("Enter new invalid login");
	    LoginLog invalidLog = new LoginLog("Invalid Login", "Session ID : " + " - " + session.getId(),
		    "Username: " + uname, "Password: " + pword, "null", "null");

	    invalidLog.addLog(new Date());
	    this.allLoginLogs.put("Invalid Login", invalidLog);
	}
	else
	{
	    // System.out.println("Update invalid login");
	    this.allLoginLogs.get("Invalid Login").addLog(new Date());
	}

	// System.out.println("Size of map - " + this.allLoginLogs.size());

	if (validity)
	{
	    RequestDispatcher rd = request.getRequestDispatcher("DisplayServlet");
	    rd.forward(request, response);
	}
	else
	// {
	// response.setContentType("text/html");
	// PrintWriter out = response.getWriter();
	//
	// session.invalidate();
	//
	// out.println("<html>");
	// out.println("<head>");
	// out.println("<title>" + uname + " is invalid </title>");
	// out.println("</head>");
	// out.println("<body>");
	// out.println("<h1>Invalid Login user : " + uname + "</h1>");
	// out.println("<form action = 'LoginPageServlet' method = 'Get'>");
	// out.println("<p> <input type = 'submit' value = 'Go To Login Page'
	// /></p>");
	// out.println("</form>");
	// out.println("</body>");
	// out.println("</html>");
	// }
	{
	    session.setAttribute("InvalidLogin", true);
	    response.sendRedirect("LoginPageServlet");
	}
    }

}
