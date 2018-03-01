package com.sg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PageThree")
public class PageThree extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public PageThree()
    {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	String fName = request.getParameter("fname");
	String lName = request.getParameter("lname");
	String phone = request.getParameter("Phone");
	String email = request.getParameter("Email");

	PrintWriter out = response.getWriter();

	response.setContentType("text/html");
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Page One</title>");
	out.println("</head>");
	out.println("<body>");
	out.println("<p>First Name : " + fName + " </p>");
	out.println("<p>Last Name : " + lName + " </p>");
	out.println("<p>Phone : " + phone + " </p>");
	out.println("<p>Email : " + email + " </p>");
	String str = "fname=" + fName + "&lname=" + lName + "&Phone=" + phone + "&Email=" + email;
	out.println("<form name = 'frm3' action = 'PageFour?" + str + "' method=Post>");
	out.println("<p>Country : <input type = 'text' name = 'Country'/></p>");
	out.println("<p>City : <input type = 'text' name = 'City'/></p>");
	out.println("<p><input type = 'submit' value = 'Submit' name = 'btmSubmit3'/></p>");
	out.println("</form>");
	out.println("</body>");
	out.println("</html>");
    }

}
