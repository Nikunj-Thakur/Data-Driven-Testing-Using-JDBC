package com.nik.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SetConnection {
	
	Connection con;
	Statement s;
	ResultSet rs;

	public  void connecttoDB() throws SQLException {
		 con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/dvdrental", "postgres", "password");
		 s=con.createStatement();
	}
	
	public String getFirstName() throws SQLException {
		String firstname = null;
		rs=s.executeQuery("SELECT * FROM customer LIMIT 1");
		while(rs.next())
		{
			firstname=rs.getString("first_name");
		}
		return firstname;

	}
	
	public String getLastName() throws SQLException {
		String lastname = null;
		rs=s.executeQuery("SELECT * FROM customer LIMIT 1");
		while(rs.next())
		{
			lastname=rs.getString("last_name");
		}
		return lastname;

	}

}
