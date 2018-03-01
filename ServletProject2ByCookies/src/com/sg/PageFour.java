package com.sg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PageFour")
public class PageFour extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public PageFour()
    {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	Cookie [] userData = request.getCookies();
	
	String fName = userData[0].getValue();
	String lName = userData[1].getValue();
	String phone = userData[2].getValue();
	String email = userData[3].getValue();
	String country = request.getParameter("Country");
	String city = request.getParameter("City");
		
	Cookie countryData = new Cookie("Country", country);
	Cookie cityData = new Cookie("City", city);

	response.addCookie(countryData);
	response.addCookie(cityData);
	
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
	out.println("<p>Country : " + country + " </p>");
	out.println("<p>City : " + city + " </p>");
	out.println("<form name = 'frm4' action = 'PageOne' method=Post>");
	out.println("<p><input type = 'submit' value = 'Start Over' name = 'btmSubmit4'/></p>");
	out.println("</form>");
	out.println("</body>");
	out.println("</html>");
    }

}
