package com.sg;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class LoginLog
{
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String country;
    private String city;
    private ArrayList<Date> loginLog;

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public LoginLog(String firstName, String lastName, String phone, String email, String country, String city)
    {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.phone = phone;
	this.email = email;
	this.country = country;
	this.city = city;

	loginLog = new ArrayList<Date>();
    }

    public String getDateDisplay(Date dt)
    {
	return "" + sdf.format(dt);
    }

    public void addLog(Date dt)
    {
	loginLog.add(dt);
    }

    public String printAllDate()
    {
	String str = "";

	for (Date dt : loginLog)
	{
	    str += getDateDisplay(dt);
	}

	return str;
    }

    public String getFirstName()
    {
	return firstName;
    }

    public void setFirstName(String firstName)
    {
	this.firstName = firstName;
    }

    public String getLastName()
    {
	return lastName;
    }

    public void setLastName(String lastName)
    {
	this.lastName = lastName;
    }

    public ArrayList<Date> getLoginLog()
    {
	return loginLog;
    }

    public void setLoginLog(ArrayList<Date> loginLog)
    {
	this.loginLog = loginLog;
    }

    public String getFullName()
    {
	return getFirstName() + " " + getLastName();
    }

    public String getPhone()
    {
	return phone;
    }

    public void setPhone(String phone)
    {
	this.phone = phone;
    }

    public String getEmail()
    {
	return email;
    }

    public void setEmail(String email)
    {
	this.email = email;
    }

    public String getCountry()
    {
	return country;
    }

    public void setCountry(String country)
    {
	this.country = country;
    }

    public String getCity()
    {
	return city;
    }

    public void setCity(String city)
    {
	this.city = city;
    }

}
